package dto;

public class EnvioCentroDTO {

	private String nombreCentro;
	private String refArt;
	private String nombreArt;
	private String cantidad;

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getRefArt() {
		return refArt;
	}

	public void setRefArt(String refArt) {
		this.refArt = refArt;
	}

	public String getNombreArt() {
		return nombreArt;
	}

	public void setNombreArt(String nombreArt) {
		this.nombreArt = nombreArt;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
}
