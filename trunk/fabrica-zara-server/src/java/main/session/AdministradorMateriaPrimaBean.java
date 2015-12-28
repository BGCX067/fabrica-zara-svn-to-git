package session;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import dto.EnvProvDTO;
import dto.EnvioProveedorDTO;
import dto.EspecificacionMateriaPrimaDTO;
import dto.MateriaPrimaDTO;
import dto.StockMateriaPrimaDTO;
import entities.EnvioProveedor;
import entities.EspecificacionArticulo;
import entities.EspecificacionMateriaPrima;
import entities.MateriaPrima;
import entities.StockMateriaPrima;
import facade.AdministradorMateriaPrima;

@Stateless
public class AdministradorMateriaPrimaBean implements AdministradorMateriaPrima{
	
	@PersistenceContext(unitName="fabrica")
    private EntityManager em;

	public List<EnvProvDTO> procesarEnvioProveedor(EnvioProveedorDTO envioProveedorDTO) {
		EnvioProveedor envioProveedor=new EnvioProveedor();
		List<EnvProvDTO> envProvDTOs=new ArrayList<EnvProvDTO>();
		envioProveedor.setEspecificacionMateriaPrima(new HashSet<EspecificacionMateriaPrima>());
		for(EspecificacionMateriaPrimaDTO e:envioProveedorDTO.getMateriaPrimas()){
			EspecificacionMateriaPrima especificacionMateriaPrima=new EspecificacionMateriaPrima();
			MateriaPrima materiaPrima=obtenerMateriaPrima(e.getReferencia());
			especificacionMateriaPrima.setMateriaPrima(materiaPrima);
			especificacionMateriaPrima.setCantidad(e.getCantidad());
			em.persist(especificacionMateriaPrima);
			envioProveedor.getEspecificacionMateriaPrima().add(especificacionMateriaPrima);
			reponerMateriaPrima(e);
			StockMateriaPrima stockMateriaPrima=obtenerStockMateriaPrima(materiaPrima);
			EnvProvDTO envProvDTO=new EnvProvDTO();
			envProvDTO.setNombre(materiaPrima.getNombre());
			envProvDTO.setRecibido(e.getCantidad());
			envProvDTO.setReferencia(materiaPrima.getReferencia());
			envProvDTO.setCantidad(stockMateriaPrima.getCantidad());
			envProvDTOs.add(envProvDTO);
		}
		envioProveedor.setFecha(new GregorianCalendar());
		em.persist(envioProveedor);
		return envProvDTOs;
	}

	public List<StockMateriaPrimaDTO> obtenerStockMateriaPrima() {
		List<StockMateriaPrimaDTO> stockMateriaPrimaDTOList=new ArrayList<StockMateriaPrimaDTO>();
		for(StockMateriaPrima s : getAllStockMateriaPrima()){
			StockMateriaPrimaDTO stockMateriaPrimaDTO=new StockMateriaPrimaDTO();
			stockMateriaPrimaDTO.setCantidad(s.getCantidad());
			stockMateriaPrimaDTO.setNombre(s.getMateriaPrima().getNombre());
			stockMateriaPrimaDTO.setReferencia(s.getMateriaPrima().getReferencia());
			stockMateriaPrimaDTOList.add(stockMateriaPrimaDTO);
		}
		return stockMateriaPrimaDTOList;
	}
	
	private StockMateriaPrima obtenerStockMateriaPrima(MateriaPrima materiaPrima) {
		Query q=em.createQuery("select s from StockMateriaPrima s where s.materiaPrima=?1");
		q.setParameter(1, materiaPrima);
		return (StockMateriaPrima) q.getSingleResult(); 
	}
	
	private void reponerMateriaPrima(EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO){
		Query q=em.createQuery("select s from StockMateriaPrima s where s.materiaPrima.referencia=?1");
		q.setParameter(1, especificacionMateriaPrimaDTO.getReferencia());
		StockMateriaPrima stock=((StockMateriaPrima)q.getSingleResult());
		stock.setCantidad(stock.getCantidad()+especificacionMateriaPrimaDTO.getCantidad());
	}
	
	@SuppressWarnings("unchecked")
	public List<MateriaPrimaDTO> obtenerMateriaPrimas(){
		List<MateriaPrimaDTO> materiaPrimaDTOs=new ArrayList<MateriaPrimaDTO>(); 
		Mapper mapper = new DozerBeanMapper();
		Query q=em.createQuery("select m from MateriaPrima m");
		for(MateriaPrima m:(List<MateriaPrima>)q.getResultList()){
			materiaPrimaDTOs.add(mapper.map(m, MateriaPrimaDTO.class));
		}
		return materiaPrimaDTOs;
	}
	
	public MateriaPrima obtenerMateriaPrima(String referencia){
		Query q=em.createQuery("select m from MateriaPrima m where m.referencia=?1");
		q.setParameter(1, referencia);
		return (MateriaPrima)q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	private List<StockMateriaPrima> getAllStockMateriaPrima(){
		Query q=em.createQuery("select s from StockMateriaPrima s");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MateriaPrimaDTO> obtenerMateriaPrimaParaAgregar(long idEspecificacionArticulo){
		Mapper mapper = new DozerBeanMapper();
		List<MateriaPrimaDTO> materiaPrimaDTOs=new ArrayList<MateriaPrimaDTO>();
		EspecificacionArticulo especificacionArticulo=em.find(EspecificacionArticulo.class, idEspecificacionArticulo);		
		List<EspecificacionMateriaPrima> especificacionMateriaPrimaArticuloList=new ArrayList<EspecificacionMateriaPrima>(especificacionArticulo.getMateriaPrimas());
		List<MateriaPrima> materiaPrimas=new ArrayList<MateriaPrima>();
		for(EspecificacionMateriaPrima e:especificacionMateriaPrimaArticuloList){
			materiaPrimas.add(e.getMateriaPrima());
		}
		
		Query q=em.createQuery("select m from MateriaPrima m");
		List<MateriaPrima> materiaPrimaParaAgregar=q.getResultList();
		for(MateriaPrima m:materiaPrimas){
			materiaPrimaParaAgregar.remove(m);
		}
		for(MateriaPrima m:materiaPrimaParaAgregar){
			materiaPrimaDTOs.add(mapper.map(m, MateriaPrimaDTO.class));
		}
		return materiaPrimaDTOs;
	}

}
