package entities;

import java.util.GregorianCalendar;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Produccion extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar fechaComienzo;
	@OneToMany(mappedBy="produccion")
	//@JoinColumn(name="idProduccion")
	private Set<Articulo> articulos;

	public void setFechaComienzo(GregorianCalendar fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public GregorianCalendar getFechaComienzo() {
		return fechaComienzo;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

}
