package session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import closures.Closure;
import closures.Executor;

import dto.ArticuloNuevoDTO;
import dto.EnvProvDTO;
import dto.EnvioCentroDTO;
import dto.EnvioProveedorDTO;
import dto.EspecificacionArticuloDTO;
import dto.EspecificacionMateriaPrimaDTO;
import dto.MateriaPrimaDTO;
import dto.ProduccionDTO;
import dto.SolicitudFabricacionDTO;
import dto.StockMateriaPrimaDTO;
import facade.AdministradorArticulos;
import facade.AdministradorEnvioArticulos;
import facade.AdministradorMateriaPrima;
import facade.AdministradorProduccion;
import facade.AdministradorSolicitudFabricacion;
import facade.Facade;

@Stateless
public class SessionFacade implements Facade{
	
	@EJB
	private AdministradorMateriaPrima administradorMateriaPrima;
	@EJB
	private AdministradorArticulos administradorArticulos;
	@EJB
	private AdministradorProduccion administradorProduccion;
	@EJB
	private AdministradorSolicitudFabricacion administradorSolicitudFabricacion;
	@EJB
	private AdministradorEnvioArticulos administradorEnvioArticulos;

	//Administrador Articulos
	public void registrarArticulosNuevos(final ArticuloNuevoDTO articuloNuevoDTO) {
		Executor.execute(new Closure() {
			public Object execute() {
				administradorArticulos.registrarArticulosNuevos(articuloNuevoDTO);
				return null;
			}
		});
			
	}
	
	@SuppressWarnings("unchecked")
	public List<EspecificacionArticuloDTO> obtenerEspecificacionArticulos(){
		return (List<EspecificacionArticuloDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return  administradorArticulos.obtenerEspecificacionArticulos();
			}
		});
	}
	
	public EspecificacionArticuloDTO getEspecificacionArticuloById(final long id){
		return (EspecificacionArticuloDTO)Executor.execute(new Closure() {
			public Object execute() {
				return administradorArticulos.getEspecificacionArticuloById(id);
			}
		});
	}
	
	public void guardarTiemporFabricacion(final long idEspecificacionArticulo,
			final int tiempoFabricacion) {
		Executor.execute(new Closure() {
			public Object execute() {
				administradorArticulos.guardarTiemporFabricacion(idEspecificacionArticulo, tiempoFabricacion);
				return null;
			}
		});
	}
	
	public void agregarMateriaPrima(final long idEspecificacionArticulo,
			final EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO) {
		Executor.execute(new Closure() {
			public Object execute() {
				administradorArticulos.agregarMateriaPrima(idEspecificacionArticulo, especificacionMateriaPrimaDTO);
				return null;
			}
		});
		
		
	}
	
	//Administrador Envio Articulos
	@SuppressWarnings("unchecked")
	public List<EnvioCentroDTO> generarReposicionFabrica() {
		return (List<EnvioCentroDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorEnvioArticulos.generarReposicionFabrica();
			}
		});
	}
	
	//Administrador Materia Prima
	@SuppressWarnings("unchecked")
	public List<EnvProvDTO> procesarEnvioProveedor(final EnvioProveedorDTO envioProveedorDTO) {
		return (List<EnvProvDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorMateriaPrima.procesarEnvioProveedor(envioProveedorDTO);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<StockMateriaPrimaDTO> obtenerStockMateriaPrima() { 
		return (List<StockMateriaPrimaDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorMateriaPrima.obtenerStockMateriaPrima();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<MateriaPrimaDTO> obtenerMateriaPrimaParaAgregar(final long idEspecificacionArticulo) {
		return (List<MateriaPrimaDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorMateriaPrima.obtenerMateriaPrimaParaAgregar(idEspecificacionArticulo);
			}
		});
	}
	
	//Administrador Produccion
	public void fabricar() {
		Executor.execute(new Closure() {
			public Object execute() {
				administradorProduccion.fabricar();
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<ProduccionDTO> obtnerArticulosFabricacionPendiente(){
		return (List<ProduccionDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorProduccion.obtnerArticulosFabricacionPendiente();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<ProduccionDTO> obtnerArticulosFabricacionComenzada(){
		return (List<ProduccionDTO>)Executor.execute(new Closure() {
			public Object execute() {
				return administradorProduccion.obtnerArticulosFabricacionComenzada();
			}
		});
	}
	
	//Administrar Solicitud Fabricacion
	public void registrarSolicitudFabricacion(final SolicitudFabricacionDTO solicitudFabricacionDTO) {
		Executor.execute(new Closure() {
			public Object execute() {
				administradorSolicitudFabricacion.registrarSolicitudFabricacion(solicitudFabricacionDTO);
				return null;
			}
		});
	}
}
