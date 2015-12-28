package dto;

public class MateriaPrimaDTO extends TransferableObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String referencia;
	private String nombre;
	private String unidadMedida;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferencia() {
		return referencia;
	}
}
