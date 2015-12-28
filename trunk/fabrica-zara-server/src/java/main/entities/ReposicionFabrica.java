package entities;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ReposicionFabrica extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GregorianCalendar fechaEnvio;
	@ManyToOne
	@JoinColumn(name="idCentroDistribucion")
	private CentroDistribucion centroDistribucion;
	@OneToMany(mappedBy="reposicionFabrica")
	//@JoinColumn(name="idReposicionFabrica")
	private Set<Articulo> articulos;

	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}

	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}

	public void setFechaEnvio(GregorianCalendar fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public GregorianCalendar getFechaEnvio() {
		return fechaEnvio;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

}
