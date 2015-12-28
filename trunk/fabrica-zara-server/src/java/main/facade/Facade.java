package facade;

import java.util.List;
import javax.ejb.Remote;

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

@Remote
public interface Facade {
	//Administrador Articulos
	void registrarArticulosNuevos(ArticuloNuevoDTO articuloNuevoDTO);
	List<EspecificacionArticuloDTO> obtenerEspecificacionArticulos();
	EspecificacionArticuloDTO getEspecificacionArticuloById(long id);
	void guardarTiemporFabricacion(long idEspecificacionArticulo, int tiempoFabricacion);
	void agregarMateriaPrima(long idEspecificacionArticulo, EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO);
	
	//Administrador Envio Articulos
	List<EnvioCentroDTO> generarReposicionFabrica();
	
	//Administrador Materia Prima
	List<EnvProvDTO> procesarEnvioProveedor(EnvioProveedorDTO envioProveedorDTO);
	List<StockMateriaPrimaDTO> obtenerStockMateriaPrima();
	List<MateriaPrimaDTO> obtenerMateriaPrimaParaAgregar(long idEspecificacionArticulo);
	
	//Administrador Produccion
	void fabricar();
	List<ProduccionDTO> obtnerArticulosFabricacionPendiente();
	List<ProduccionDTO> obtnerArticulosFabricacionComenzada();
	
	//Administrar Solicitud Fabricacion
	void registrarSolicitudFabricacion(SolicitudFabricacionDTO solicitudFabricacionDTO);
}