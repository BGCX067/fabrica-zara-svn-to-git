package session;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dto.ArticuloFabricaDTO;
import dto.SolicitudFabricacionDTO;
import entities.Articulo;
import entities.EspecificacionArticulo;
import entities.SolicitudFabricacion;
import facade.AdministradorArticulos;
import facade.AdministradorCentroDistribucion;
import facade.AdministradorSolicitudFabricacion;

@Stateless
public class AdministradorSolicitudFabricacionBean implements AdministradorSolicitudFabricacion {

	@EJB
	private AdministradorCentroDistribucion administradorCentroDistribucion;
	
	@EJB
	private AdministradorArticulos administradorArticulos;
	
	@PersistenceContext(unitName="fabrica")
    private EntityManager em;
	
	public void registrarSolicitudFabricacion(SolicitudFabricacionDTO solicitudFabricacionDTO) {
		
		SolicitudFabricacion solicitudFabricacion = new SolicitudFabricacion();
		solicitudFabricacion.setFechaSolicitud(new GregorianCalendar());
	
		solicitudFabricacion.setCentroDistribucion(administradorCentroDistribucion.getCentroDistribucion(solicitudFabricacionDTO.getIdCentro()));
		
		Set<Articulo> articulos=new HashSet<Articulo>();
		
		solicitudFabricacion.setNumSolFab(solicitudFabricacionDTO.getNumSolFab());
		
		em.persist(solicitudFabricacion);
		
		for(ArticuloFabricaDTO a:solicitudFabricacionDTO.getArticulos()){
			EspecificacionArticulo especificacionArticulo=administradorArticulos.getEspecificacionArticuloByReferencia(a.getRef());
			for(int i=0;i<Integer.parseInt(a.getCant());i++){
				Articulo articulo= new Articulo();
				articulo.setEspecificacionArticulo(especificacionArticulo);
				articulo.setSolicitudFabricacion(solicitudFabricacion);
				em.persist(articulo);
				articulos.add(articulo);
			}
		} 
		solicitudFabricacion.setArticulos(articulos);	
	}
	
//	private CentroDistribucion getCentroDistribucion(String idCentro){
//		Query q=em.createQuery("select c from CentroDistribucion c where c.idCentro=?1");
//		q.setParameter(1, idCentro);
//		return (CentroDistribucion)q.getSingleResult();
//	}
	
//	private EspecificacionArticulo getEspecificacionArticulo(String referencia){
//		Query q=em.createQuery("select e from EspecificacionArticulo e where e.referencia=?1");
//		q.setParameter(1, referencia);
//		return (EspecificacionArticulo)q.getSingleResult();
//	}

}
