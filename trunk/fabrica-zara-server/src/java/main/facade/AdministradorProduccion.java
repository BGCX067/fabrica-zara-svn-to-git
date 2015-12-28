package facade;

import java.util.List;

import javax.ejb.Local;

import dto.ProduccionDTO;

@Local
public interface AdministradorProduccion {
	
	void fabricar();
	List<ProduccionDTO> obtnerArticulosFabricacionPendiente();
	List<ProduccionDTO> obtnerArticulosFabricacionComenzada();
}
