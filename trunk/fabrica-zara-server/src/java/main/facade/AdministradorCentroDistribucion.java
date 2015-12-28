package facade;

import java.util.List;

import javax.ejb.Local;

import entities.CentroDistribucion;

@Local
public interface AdministradorCentroDistribucion {

	CentroDistribucion getCentroDistribucion(String idCentro);
	List<CentroDistribucion> getAllCentroDistribucion();
}
