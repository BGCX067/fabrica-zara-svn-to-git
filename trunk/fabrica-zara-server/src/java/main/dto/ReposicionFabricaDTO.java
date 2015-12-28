package dto;

import java.io.Serializable;
import java.util.ArrayList;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="repAF")
public class ReposicionFabricaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Element
	private String numRepAF;
	@Element
	private String numSolFab;
	@Element
	private String idCentro;
	@Element
	private String idFabrica;

	@ElementList(name="articulos")
	private ArrayList<ArticuloFabricaDTO> articulos;

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

	public ArrayList<ArticuloFabricaDTO> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<ArticuloFabricaDTO> articulos) {
		this.articulos = articulos;
	}

	public void setNumRepAF(String numRepAF) {
		this.numRepAF = numRepAF;
	}

	public String getNumRepAF() {
		return numRepAF;
	}

}