package facade;

import javax.ejb.Local;

import dto.SolicitudFabricacionDTO;

@Local
public interface AdministradorSolicitudFabricacion {

	void registrarSolicitudFabricacion(SolicitudFabricacionDTO solicitudFabricacionDTO);
}
