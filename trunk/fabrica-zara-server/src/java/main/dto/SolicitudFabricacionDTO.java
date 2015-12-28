package dto;

import java.io.Serializable;
import java.util.List;

public class SolicitudFabricacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numSolFab;
	private String idCentro;
	private String idFabrica;

	private List<ArticuloFabricaDTO> articulos;

	public String getNumSolFab() {
		return numSolFab;
	}

	public void setNumSolFab(String numSolFab) {
		this.numSolFab = numSolFab;
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getIdFabrica() {
		return idFabrica;
	}

	public void setIdFabrica(String idFabrica) {
		this.idFabrica = idFabrica;
	}

	public List<ArticuloFabricaDTO> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloFabricaDTO> articulos) {
		this.articulos = articulos;
	}
}
