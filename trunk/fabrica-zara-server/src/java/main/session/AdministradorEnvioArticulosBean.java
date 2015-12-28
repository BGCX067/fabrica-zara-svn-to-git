package session;

import helpers.XMLSerializationHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import dto.ArticuloFabricaDTO;
import dto.EnvioCentroDTO;
import dto.ReposicionFabricaDTO;
import entities.Articulo;
import entities.CentroDistribucion;
import entities.ReposicionFabrica;
import entities.SolicitudFabricacion;
import facade.AdministradorCentroDistribucion;
import facade.AdministradorEnvioArticulos;

@Stateless
public class AdministradorEnvioArticulosBean implements AdministradorEnvioArticulos {

	@EJB
	private AdministradorCentroDistribucion administradorCentroDistribucion;
	@PersistenceContext(unitName = "fabrica")
	private EntityManager em;
	//TODO cambiar lo que evuelve a List<EnvioCentroDTO>
	public List<EnvioCentroDTO> generarReposicionFabrica() {
		//List<ReposicionFabricaDTO> reposicionFabricaDTOList = new ArrayList<ReposicionFabricaDTO>();
		//List<ReposicionFabrica> reposicionFabricaList = new ArrayList<ReposicionFabrica>();
		List<EnvioCentroDTO> envioCentroDTOs=new ArrayList<EnvioCentroDTO>();
		
		for (CentroDistribucion c : administradorCentroDistribucion.getAllCentroDistribucion()) {
			
			Set<Articulo> articulos = new HashSet<Articulo>();
			articulos.addAll(obtenerArticulosParaEnviarPorCentro(c));
			if (!articulos.isEmpty()) {
				ReposicionFabrica reposicionFabrica = new ReposicionFabrica();
				reposicionFabrica.setCentroDistribucion(c);
				reposicionFabrica.setFechaEnvio(new GregorianCalendar());			
				reposicionFabrica.setArticulos(articulos);
				em.persist(reposicionFabrica);
				
				for(Articulo a: articulos){
					a.setReposicionFabrica(reposicionFabrica);
				}
				
				envioCentroDTOs.addAll(obtenerArticulosEnviadosPorCentro(reposicionFabrica));
				

				ReposicionFabricaDTO reposicionFabricaDTO = new ReposicionFabricaDTO();
				reposicionFabricaDTO.setIdCentro(c.getIdCentro());
				reposicionFabricaDTO.setNumRepAF(reposicionFabrica.getId().toString());
				reposicionFabricaDTO.setArticulos(new ArrayList<ArticuloFabricaDTO>());
				reposicionFabricaDTO.setIdFabrica("1");
				
				//Estupido con este modelo de xml(deberian ser muchos solfaben caso de tener cierta trazabilidad);
				Query q=em.createQuery("select s from SolicitudFabricacion s where s.centroDistribucion=?1 and fechaSolicitud = (select max(s.fechaSolicitud) from s where s.centroDistribucion=?1)" );
				q.setParameter(1,c);
				//reposicionFabricaDTO.setNumSolFab("111");
				@SuppressWarnings("unchecked")
				List<SolicitudFabricacion> solicitudFabricacionList=q.getResultList();
				if(!solicitudFabricacionList.isEmpty())
					reposicionFabricaDTO.setNumSolFab(solicitudFabricacionList.get(0).getNumSolFab());
				else
					reposicionFabricaDTO.setNumSolFab("777");
				//Bloque Estupido

				List<Object> objects=obtenerCantidadArticulosPorReposicionFabrica(reposicionFabrica);
				objects.size();
				for (Object o : obtenerCantidadArticulosPorReposicionFabrica(reposicionFabrica)) {
					Object[] array = (Object[]) o;
					ArticuloFabricaDTO a = new ArticuloFabricaDTO();
					a.setRef((String) array[0]);
					a.setCant(array[1].toString());
					reposicionFabricaDTO.getArticulos().add(a);
				}
				try {
					XMLSerializationHelper.Serializing(reposicionFabricaDTO,
							"RepAF" + c.getIdCentro() + ".xml");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger logger=Logger.getLogger(AdministradorEnvioArticulosBean.class);
					logger.error(e);
					throw new RuntimeException(e);
				}
				//reposicionFabricaList.add(reposicionFabrica);
				//reposicionFabricaDTOList.add(reposicionFabricaDTO);
			}
		}

		//return reposicionFabricaDTOList;
		return envioCentroDTOs;
	}

	@SuppressWarnings("unchecked")
	private List<Articulo> obtenerArticulosParaEnviarPorCentro(
			CentroDistribucion centroDistribucion) {
		GregorianCalendar today=new GregorianCalendar();
		Query q = em.createQuery("select a from Articulo a where a.produccion!=null and a.reposicionFabrica=null and a.solicitudFabricacion.centroDistribucion=?1 and a.fechaFin < ?2");
		q.setParameter(1, centroDistribucion);
		q.setParameter(2, today);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	private List<Object> obtenerCantidadArticulosPorReposicionFabrica(
			ReposicionFabrica reposicionFabrica) {
		Query q = em
				.createQuery("select a.especificacionArticulo.referencia, count(a.especificacionArticulo.referencia) from Articulo a where a.reposicionFabrica=?1 group by a.especificacionArticulo.referencia");
		q.setParameter(1, reposicionFabrica);
		return q.getResultList();
	}
	
	private List<EnvioCentroDTO> obtenerArticulosEnviadosPorCentro(
			ReposicionFabrica reposicionFabrica) {
		List<EnvioCentroDTO> envioCentroDTOs=new ArrayList<EnvioCentroDTO>();
		Query q = em
				.createQuery("select a.solicitudFabricacion.centroDistribucion.nombre, a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion" +
						", count(a.especificacionArticulo.referencia) from Articulo a where a.reposicionFabrica=?1 group by " +
						"a.solicitudFabricacion.centroDistribucion.nombre, a.especificacionArticulo.referencia,a.especificacionArticulo.descripcion");
		q.setParameter(1, reposicionFabrica);
		for (Object o : q.getResultList()) {
			Object[] array = (Object[]) o;
			EnvioCentroDTO envioCentroDTO=new EnvioCentroDTO();
			envioCentroDTO.setNombreCentro((String) array[0]);
			envioCentroDTO.setRefArt(array[1].toString());
			envioCentroDTO.setNombreArt(array[2].toString());
			envioCentroDTO.setCantidad(array[3].toString());
			envioCentroDTOs.add(envioCentroDTO);
		}
		return envioCentroDTOs;
	}

}
