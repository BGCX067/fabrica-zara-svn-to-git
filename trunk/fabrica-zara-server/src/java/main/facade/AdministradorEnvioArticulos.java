package facade;

import java.util.List;
import javax.ejb.Remote;
import dto.EnvioCentroDTO;

@Remote
public interface AdministradorEnvioArticulos {

	List<EnvioCentroDTO> generarReposicionFabrica();
	
}
