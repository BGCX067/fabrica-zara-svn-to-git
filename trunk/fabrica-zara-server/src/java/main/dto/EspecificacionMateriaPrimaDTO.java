package dto;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="matPrima")
public class EspecificacionMateriaPrimaDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Element(name="ref")
	private String referencia;
	@Element
	private float cantidad;
	private String nombre;
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}