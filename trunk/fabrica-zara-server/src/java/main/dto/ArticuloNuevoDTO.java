package dto;

import java.io.Serializable;
import java.util.List;

public class ArticuloNuevoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EspecificacionRopaDTO> especificacionRopaDTOs;
	private List<EspecificacionAccesorioDTO> especificacionAccesorioDTOs;

	public void setEspecificacionRopaDTOs(List<EspecificacionRopaDTO> especificacionRopaDTOs) {
		this.especificacionRopaDTOs = especificacionRopaDTOs;
	}

	public List<EspecificacionRopaDTO> getEspecificacionRopaDTOs() {
		return especificacionRopaDTOs;
	}

	public void setEspecificacionAccesorioDTOs(
			List<EspecificacionAccesorioDTO> especificacionAccesorioDTOs) {
		this.especificacionAccesorioDTOs = especificacionAccesorioDTOs;
	}

	public List<EspecificacionAccesorioDTO> getEspecificacionAccesorioDTOs() {
		return especificacionAccesorioDTOs;
	}
}