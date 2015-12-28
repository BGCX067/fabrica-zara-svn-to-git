package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.CentroDistribucion;
import facade.AdministradorCentroDistribucion;

@Stateless
public class AdministradorCentroDistribucionBean implements AdministradorCentroDistribucion {

	@PersistenceContext(unitName = "fabrica")
	private EntityManager em;
	
	public CentroDistribucion getCentroDistribucion(String idCentro){
		Query q=em.createQuery("select c from CentroDistribucion c where c.idCentro=?1");
		q.setParameter(1, idCentro);
		return (CentroDistribucion)q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CentroDistribucion> getAllCentroDistribucion() {
		Query q = em.createQuery("select c from CentroDistribucion c");
		return q.getResultList();
	}
}
