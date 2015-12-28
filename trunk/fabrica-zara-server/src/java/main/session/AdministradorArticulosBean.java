package session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import dto.ArticuloNuevoDTO;
import dto.EspecificacionAccesorioDTO;
import dto.EspecificacionArticuloDTO;
import dto.EspecificacionMateriaPrimaDTO;
import dto.EspecificacionRopaDTO;
import entities.EspecificacionAccesorio;
import entities.EspecificacionArticulo;
import entities.EspecificacionMateriaPrima;
import entities.EspecificacionRopa;
import entities.MateriaPrima;
import facade.AdministradorArticulos;
import facade.AdministradorMateriaPrima;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos {

	@EJB
	private AdministradorMateriaPrima administradorMateriaPrima;
	@PersistenceContext(unitName = "fabrica")
	private EntityManager em;

	public void registrarArticulosNuevos(ArticuloNuevoDTO articuloNuevoDTO) {
		Mapper mapper = new DozerBeanMapper();

		if (articuloNuevoDTO.getEspecificacionRopaDTOs() != null)
			for (EspecificacionRopaDTO e : articuloNuevoDTO.getEspecificacionRopaDTOs()) {
				EspecificacionRopa especificacionRopa = mapper.map(e,EspecificacionRopa.class);
				especificacionRopa.setTiempoFabricacion(1);
				especificacionRopa.setNuevo(true);
				em.persist(especificacionRopa);
			}
		else
			for (EspecificacionAccesorioDTO e : articuloNuevoDTO.getEspecificacionAccesorioDTOs()) {
				EspecificacionAccesorio especificacionAccesorio = mapper.map(e,EspecificacionAccesorio.class);
				especificacionAccesorio.setTiempoFabricacion(1);
				especificacionAccesorio.setNuevo(true);
				em.persist(especificacionAccesorio);
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<EspecificacionArticuloDTO> obtenerEspecificacionArticulos(){
		Query q=em.createQuery("Select e from EspecificacionArticulo e");
		Mapper mapper = new DozerBeanMapper();
		List<EspecificacionArticuloDTO> especificacionArticuloDTOs=new ArrayList<EspecificacionArticuloDTO>();
		for(EspecificacionArticulo e: (List<EspecificacionArticulo>)q.getResultList()){
			if(e.getClass().equals(EspecificacionRopa.class))
				especificacionArticuloDTOs.add(mapper.map(e, EspecificacionRopaDTO.class));
			else
				especificacionArticuloDTOs.add(mapper.map(e, EspecificacionAccesorioDTO.class));
		}
		return especificacionArticuloDTOs;
	}
	
	public EspecificacionArticuloDTO getEspecificacionArticuloById(long id){
		EspecificacionArticulo e=em.find(EspecificacionArticulo.class, id);
		Mapper mapper = new DozerBeanMapper();
		EspecificacionArticuloDTO especificacionArticuloDTO;
		if(e instanceof EspecificacionAccesorio)
			especificacionArticuloDTO=mapper.map(e,EspecificacionAccesorioDTO.class);
		else
			especificacionArticuloDTO=mapper.map(e,EspecificacionRopaDTO.class);
			
		especificacionArticuloDTO.getMateriaPrimas().clear();
		for(EspecificacionMateriaPrima m:e.getMateriaPrimas()){
			EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO=new EspecificacionMateriaPrimaDTO();
			especificacionMateriaPrimaDTO.setReferencia(m.getMateriaPrima().getReferencia());
			especificacionMateriaPrimaDTO.setCantidad(m.getCantidad());
			especificacionMateriaPrimaDTO.setNombre(m.getMateriaPrima().getNombre());
			especificacionArticuloDTO.getMateriaPrimas().add(especificacionMateriaPrimaDTO);
		}
		return especificacionArticuloDTO;	
	}
	
	public void guardarTiemporFabricacion(long idEspecificacionArticulo, int tiempoFabricacion){
		em.find(EspecificacionArticulo.class, idEspecificacionArticulo).setTiempoFabricacion(tiempoFabricacion);
		
	}

	public void agregarMateriaPrima(long idEspecificacionArticulo,
			EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO) {
		
		MateriaPrima materiaPrima=administradorMateriaPrima.obtenerMateriaPrima(especificacionMateriaPrimaDTO.getReferencia());
		EspecificacionMateriaPrima e=new EspecificacionMateriaPrima();
		e.setMateriaPrima(materiaPrima);
		e.setCantidad(especificacionMateriaPrimaDTO.getCantidad());
		EspecificacionArticulo especificacionArticulo=em.find(EspecificacionArticulo.class, idEspecificacionArticulo);
		especificacionArticulo.getMateriaPrimas().add(e);
		if(especificacionArticulo.isNuevo())
			especificacionArticulo.setNuevo(false);
	}
	
	public EspecificacionArticulo getEspecificacionArticuloByReferencia(String referencia){
		Query q=em.createQuery("select e from EspecificacionArticulo e where e.referencia=?1");
		q.setParameter(1, referencia);
		return (EspecificacionArticulo)q.getSingleResult();
	}
}
