package dto;

public class EspecificacionRopaDTO extends EspecificacionArticuloDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String talle;
	private String origen;

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
