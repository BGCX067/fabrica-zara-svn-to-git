package session;

import helpers.XMLSerializationHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.EspecificacionMateriaPrimaDTO;
import dto.MateriaPrimaUtilizadaDTO;
import dto.PedidoProveedorDTO;
import dto.ProduccionDTO;
import entities.Articulo;
import entities.EspecificacionMateriaPrima;
import entities.MateriaPrima;
import entities.PedidoProveedor;
import entities.Produccion;
import entities.StockMateriaPrima;
import facade.AdministradorProduccion;

@Stateless
public class AdministradorProduccionBean implements AdministradorProduccion {

	@PersistenceContext(unitName = "fabrica")
	private EntityManager em;

	public void fabricar() {

		List<Articulo> list = obtenerArticulosParaFabricar();
		if (!list.isEmpty()) {
			Map<String, EspecificacionMateriaPrima> materiaPrimaNecesaria = new HashMap<String, EspecificacionMateriaPrima>();
			Map<String, EspecificacionMateriaPrimaDTO> materiaPrimaUtilizada = new HashMap<String, EspecificacionMateriaPrimaDTO>();

			for (MateriaPrima m : getAllMateriaPrima()) {
				EspecificacionMateriaPrima eNecesaria = new EspecificacionMateriaPrima();
				eNecesaria.setCantidad(0);
				eNecesaria.setMateriaPrima(m);
				materiaPrimaNecesaria.put(m.getReferencia(), eNecesaria);
				EspecificacionMateriaPrimaDTO e = new EspecificacionMateriaPrimaDTO();
				e.setReferencia(m.getReferencia());
				e.setCantidad(0);
				materiaPrimaUtilizada.put(m.getReferencia(), e);
			}

			Produccion produccion = new Produccion();
			produccion.setFechaComienzo(new GregorianCalendar());
			produccion.setArticulos(new HashSet<Articulo>());
			em.persist(produccion);

			for (Articulo a : list) {
				List<EspecificacionMateriaPrima> materias = sePuedeFabricar(a);
				if (materias != null) {
					utilizarMateriaPrima(a);
					for (EspecificacionMateriaPrima e : materias) {
						EspecificacionMateriaPrimaDTO especificacionMateriaPrimaUtilizada = materiaPrimaUtilizada
								.get(e.getMateriaPrima().getReferencia());
						especificacionMateriaPrimaUtilizada
								.setCantidad(especificacionMateriaPrimaUtilizada
										.getCantidad() + e.getCantidad());
					}
					a.setProduccion(produccion);
					produccion.getArticulos().add(a);
					GregorianCalendar fin=new GregorianCalendar();
					fin.add(Calendar.DATE, a.getEspecificacionArticulo().getTiempoFabricacion());
					a.setFechaFin(fin);
				} else {
					for (EspecificacionMateriaPrima e : a
							.getEspecificacionArticulo().getMateriaPrimas()) {
						EspecificacionMateriaPrima especificacionMateriaPrimaNecesaria = materiaPrimaNecesaria
								.get(e.getMateriaPrima().getReferencia());
						especificacionMateriaPrimaNecesaria
								.setCantidad(especificacionMateriaPrimaNecesaria
										.getCantidad() + e.getCantidad());
					}
				}
			}

			List<EspecificacionMateriaPrimaDTO> especificacionMateriaPrimaUtilizadaDTO = new ArrayList<EspecificacionMateriaPrimaDTO>();
			
			for(EspecificacionMateriaPrimaDTO e: materiaPrimaUtilizada.values()){
				if(e.getCantidad()>0)
					especificacionMateriaPrimaUtilizadaDTO.add(e);
			}
			
			if (!especificacionMateriaPrimaUtilizadaDTO.isEmpty()) {
				MateriaPrimaUtilizadaDTO materiaPrimaUtilizadaDTO = new MateriaPrimaUtilizadaDTO();
				materiaPrimaUtilizadaDTO
						.setMateriaPrimas(especificacionMateriaPrimaUtilizadaDTO);
				try {
					XMLSerializationHelper.Serializing(
							materiaPrimaUtilizadaDTO, "MatUt.xml");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<EspecificacionMateriaPrima> especificacionMateriaPrimaNecesaria = new ArrayList<EspecificacionMateriaPrima>();

			for(EspecificacionMateriaPrima e:materiaPrimaNecesaria.values()){
				if(e.getCantidad()>0)
					especificacionMateriaPrimaNecesaria.add(e);
			}

			if (!especificacionMateriaPrimaNecesaria.isEmpty()) {
				PedidoProveedor p = registrarPCProv(especificacionMateriaPrimaNecesaria);

				try {
					XMLSerializationHelper.Serializing(toPedidoProveedorDTO(p),
							"PcProv.xml");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	private PedidoProveedorDTO toPedidoProveedorDTO(PedidoProveedor p) {
		PedidoProveedorDTO pedidoProveedorDTO = new PedidoProveedorDTO();
		pedidoProveedorDTO
				.setMateriaPrimas(new ArrayList<EspecificacionMateriaPrimaDTO>());
		for (EspecificacionMateriaPrima e : p.getEspecificacionMateriaPrima()) {

			EspecificacionMateriaPrimaDTO es = new EspecificacionMateriaPrimaDTO();
			es.setReferencia(e.getMateriaPrima().getReferencia());
			es.setCantidad(e.getCantidad());
			pedidoProveedorDTO.getMateriaPrimas().add(es);

		}
		return pedidoProveedorDTO;
	}

	private PedidoProveedor registrarPCProv(
			List<EspecificacionMateriaPrima> materiaPrimaNecesaria) {
		PedidoProveedor p = generarPedidoProveedor(materiaPrimaNecesaria);
		em.persist(p);
		return p;
	}

	private PedidoProveedor generarPedidoProveedor(
			List<EspecificacionMateriaPrima> materiaPrimaNecesaria) {
		PedidoProveedor p = new PedidoProveedor();
		p.setFecha(new GregorianCalendar());
		p.setEspecificacionMateriaPrima(new HashSet<EspecificacionMateriaPrima>());
		for (EspecificacionMateriaPrima e : materiaPrimaNecesaria) {
			if (e.getCantidad() > 0) {
				em.persist(e);
				p.getEspecificacionMateriaPrima().add(e);
			}
		}
		return p;
	}

	// Descuenta la materia prima del stock
	private void utilizarMateriaPrima(Articulo a) {
		for (EspecificacionMateriaPrima e : a.getEspecificacionArticulo()
				.getMateriaPrimas()) {
			StockMateriaPrima stockMateriaPrima = obtenerStockMateriaPrima(e
					.getMateriaPrima());
			stockMateriaPrima.setCantidad(stockMateriaPrima.getCantidad()
					- e.getCantidad());
		}
	}

	private List<EspecificacionMateriaPrima> sePuedeFabricar(Articulo a) {
		for (EspecificacionMateriaPrima e : a.getEspecificacionArticulo()
				.getMateriaPrimas()) {
			if (!materiaPrimaSuficiente(e))
				return null;
		}
		List<EspecificacionMateriaPrima> list = new ArrayList<EspecificacionMateriaPrima>(
				a.getEspecificacionArticulo().getMateriaPrimas());

		return list;
	}

	private boolean materiaPrimaSuficiente(EspecificacionMateriaPrima e) {
		StockMateriaPrima stockMateriaPrima = obtenerStockMateriaPrima(e
				.getMateriaPrima());
		return stockMateriaPrima.getCantidad() >= e.getCantidad();
	}

	private StockMateriaPrima obtenerStockMateriaPrima(MateriaPrima m) {
		Query q = em
				.createQuery("select s from StockMateriaPrima s where s.materiaPrima=?1");
		q.setParameter(1, m);
		return (StockMateriaPrima) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	private List<MateriaPrima> getAllMateriaPrima() {
		Query q = em.createQuery("select m from MateriaPrima m");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	private List<Articulo> obtenerArticulosParaFabricar() {

		Query q = em
				.createQuery("select a from Articulo a where a.produccion=null");
		return (q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<ProduccionDTO> obtnerArticulosFabricacionPendiente() {

		Query q = em
				.createQuery("select a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion,count(a.especificacionArticulo.referencia)"
						+ "from Articulo a where a.produccion=null group by a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion");

		return generateProduccionDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<ProduccionDTO> obtnerArticulosFabricacionComenzada() {

		Query q = em
				.createQuery("select a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion,count(a.especificacionArticulo.referencia)"
						+ "from Articulo a where a.produccion!=null and a.reposicionFabrica=null group by a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion");

		return generateProduccionDTOList(q.getResultList());
	}

	private List<ProduccionDTO> generateProduccionDTOList(List<Object> list) {
		List<ProduccionDTO> produccionDTOs = new ArrayList<ProduccionDTO>();
		for (Object o : list) {
			Object[] array = (Object[]) o;
			ProduccionDTO produccionDTO = new ProduccionDTO();
			produccionDTO.setReferencia((String) array[0]);
			produccionDTO.setNombre((String) array[1]);
			produccionDTO.setCantidad(array[2].toString());
			produccionDTOs.add(produccionDTO);
		}
		return produccionDTOs;
	}
}
