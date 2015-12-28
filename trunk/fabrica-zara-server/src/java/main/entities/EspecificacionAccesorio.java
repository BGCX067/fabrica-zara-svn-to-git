package entities;

import javax.persistence.Entity;

@Entity
public class EspecificacionAccesorio extends EspecificacionArticulo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String composicion;
	private String medida;
	private String categoria;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComposicion() {
		return composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
