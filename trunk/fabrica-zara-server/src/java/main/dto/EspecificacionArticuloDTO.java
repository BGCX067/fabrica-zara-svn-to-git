package dto;

import java.util.Set;

public abstract class EspecificacionArticuloDTO extends TransferableObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String referencia;
	private String linea;
	private String descripcion;
	private String color;
	private String seccion;
	private float precio;

	private Set<EspecificacionMateriaPrimaDTO> materiaPrimas;
	private int tiempoFabricacion;
	private boolean nuevo;

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setMateriaPrimas(Set<EspecificacionMateriaPrimaDTO> materiaPrimas) {
		this.materiaPrimas = materiaPrimas;
	}

	public Set<EspecificacionMateriaPrimaDTO> getMateriaPrimas() {
		return materiaPrimas;
	}

	public void setTiempoFabricacion(int tiempoFabricacion) {
		this.tiempoFabricacion = tiempoFabricacion;
	}

	public int getTiempoFabricacion() {
		return tiempoFabricacion;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isNuevo() {
		return nuevo;
	}
}
