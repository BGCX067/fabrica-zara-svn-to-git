package facade;

import java.util.List;

import javax.ejb.Local;

import dto.EnvProvDTO;
import dto.EnvioProveedorDTO;
import dto.MateriaPrimaDTO;
import dto.StockMateriaPrimaDTO;
import entities.MateriaPrima;

@Local
public interface AdministradorMateriaPrima {
	
	List<EnvProvDTO> procesarEnvioProveedor(EnvioProveedorDTO envioProveedorDTO);
	List<StockMateriaPrimaDTO> obtenerStockMateriaPrima();
	List<MateriaPrimaDTO> obtenerMateriaPrimas();
	MateriaPrima obtenerMateriaPrima(String referencia);
	List<MateriaPrimaDTO> obtenerMateriaPrimaParaAgregar(long idEspecificacionArticulo);
}
