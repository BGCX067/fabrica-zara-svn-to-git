package dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="ropa")
public class RopaDTO extends ArticuloDTO{
	
	@Element(name="talle")
	private String talle;
	@Element(name="origen")
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
