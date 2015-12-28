package dto;

import org.simpleframework.xml.Element;


public class ArticuloDTO {

	 @Element(name="referencia")
	 private String referencia;
	 @Element(name="seccion")
	 private String seccion;
	 @Element(name="precio")
	 private float precio;
	 @Element(name="color")
	 private String color;
	 @Element(name="linea")
	 private String linea;
	 @Element(name="descripcion")
     private String descripcion;
     
     public String getReferencia() {
             return referencia;
     }
     public void setReferencia(String referencia) {
             this.referencia = referencia;
     }
     public String getSeccion() {
             return seccion;
     }
     public void setSeccion(String seccion) {
             this.seccion = seccion;
     }
     public float getPrecio() {
             return precio;
     }
     public void setPrecio(float precio) {
             this.precio = precio;
     }
     public String getColor() {
             return color;
     }
     public void setColor(String color) {
             this.color = color;
     }
     public String getLinea() {
             return linea;
     }
     public void setLinea(String linea) {
             this.linea = linea;
     }
     public String getDescripcion() {
             return descripcion;
     }
     public void setDescripcion(String descripcion) {
             this.descripcion = descripcion;
     }       
}
