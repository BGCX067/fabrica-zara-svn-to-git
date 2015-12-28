package entities;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SolicitudFabricacion extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar fechaSolicitud;
	@ManyToOne
	@JoinColumn(name = "idCentroDistribucion")
	private CentroDistribucion centroDistribucion;
	@OneToMany(mappedBy="solicitudFabricacion", cascade=CascadeType.ALL)
	//@JoinColumn(name = "idSolicitudFabricacion")
	private Set<Articulo> articulos;
	private String numSolFab;

	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}

	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setFechaSolicitud(GregorianCalendar fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public GregorianCalendar getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setNumSolFab(String numSolFab) {
		this.numSolFab = numSolFab;
	}

	public String getNumSolFab() {
		return numSolFab;
	}
}
