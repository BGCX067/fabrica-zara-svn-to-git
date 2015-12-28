package dto;

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="envProv")
public class EnvioProveedorDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ElementList(inline=true)
	private List<EspecificacionMateriaPrimaDTO> materiaPrimas;

	public void setMateriaPrimas(List<EspecificacionMateriaPrimaDTO> materiaPrimas) {
		this.materiaPrimas = materiaPrimas;
	}

	public List<EspecificacionMateriaPrimaDTO> getMateriaPrimas() {
		return materiaPrimas;
	}
}