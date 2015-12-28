package entities;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Articulo extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "idEspecificacionArticulo")
	private EspecificacionArticulo especificacionArticulo; 
	@ManyToOne
	@JoinColumn(name="idSolicitudFabricacion")
	private SolicitudFabricacion solicitudFabricacion;
	@ManyToOne
	@JoinColumn(name="idProduccion")
	private Produccion produccion;
	@ManyToOne
	@JoinColumn(name="idReposicionFabrica")
	private ReposicionFabrica reposicionFabrica;
	
	private GregorianCalendar fechaFin;

	public EspecificacionArticulo getEspecificacionArticulo() {
		return especificacionArticulo;
	}

	public void setEspecificacionArticulo(
			EspecificacionArticulo especificacionArticulo) {
		this.especificacionArticulo = especificacionArticulo;
	}

	public void setSolicitudFabricacion(SolicitudFabricacion solicitudFabricacion) {
		this.solicitudFabricacion = solicitudFabricacion;
	}

	public SolicitudFabricacion getSolicitudFabricacion() {
		return solicitudFabricacion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Produccion getProduccion() {
		return produccion;
	}

	public void setReposicionFabrica(ReposicionFabrica reposicionFabrica) {
		this.reposicionFabrica = reposicionFabrica;
	}

	public ReposicionFabrica getReposicionFabrica() {
		return reposicionFabrica;
	}

	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}

}
