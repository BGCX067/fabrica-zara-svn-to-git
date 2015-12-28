package dto;

import java.io.Serializable;

public class EnvProvDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String referencia;
	private String nombre;
	private float cantidad;
	private float recibido;

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public void setRecibido(float recibido) {
		this.recibido = recibido;
	}

	public float getRecibido() {
		return recibido;
	}
}
