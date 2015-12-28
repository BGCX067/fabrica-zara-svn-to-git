package dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="accesorio")
public class AccesorioDTO extends ArticuloDTO {
	
	@Element(name="nombre")
	private String nombre;
	@Element(name="composicion")
	private String composicion;
	@Element(name="medidas")
	private String medidas;
	@Element(name="categoria")
	private String categoria;
   
    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public String getComposicion() {
            return composicion;
    }
    public void setComposicion(String composicion) {
            this.composicion = composicion;
    }
    public String getMedidas() {
            return medidas;
    }
    public void setMedidas(String medidas) {
            this.medidas = medidas;
    }
    public String getCategoria() {
            return categoria;
    }
    public void setCategoria(String categoria) {
            this.categoria = categoria;
    }

}
