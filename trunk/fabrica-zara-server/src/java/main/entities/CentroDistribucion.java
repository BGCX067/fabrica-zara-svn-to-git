package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CentroDistribucion extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String idCentro;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getIdCentro() {
		return idCentro;
	}

}
