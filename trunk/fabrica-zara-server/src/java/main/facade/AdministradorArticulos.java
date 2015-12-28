package facade;

import java.util.List;

import javax.ejb.Local;
import dto.ArticuloNuevoDTO;
import dto.EspecificacionArticuloDTO;
import dto.EspecificacionMateriaPrimaDTO;
import entities.EspecificacionArticulo;

@Local
public interface AdministradorArticulos {

	void registrarArticulosNuevos(ArticuloNuevoDTO articuloNuevoDTO);
	List<EspecificacionArticuloDTO> obtenerEspecificacionArticulos();
	EspecificacionArticuloDTO getEspecificacionArticuloById(long id);
	void guardarTiemporFabricacion(long idEspecificacionArticulo, int tiempoFabricacion);
	void agregarMateriaPrima(long idEspecificacionArticulo, EspecificacionMateriaPrimaDTO especificacionMateriaPrimaDTO);
	EspecificacionArticulo getEspecificacionArticuloByReferencia(String referencia);
}
