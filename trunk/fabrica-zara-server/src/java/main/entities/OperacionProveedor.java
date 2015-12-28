package entities;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
@Entity
public abstract class OperacionProveedor extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany
	@JoinTable(name = "OperacionProveedor_EspecificacionMateriaPrima", joinColumns = @JoinColumn(name = "idOperacionProveedor"), inverseJoinColumns = @JoinColumn(name = "idEspecificacionMateriaPrima"))
	private Set<EspecificacionMateriaPrima> especificacionMateriaPrima;
	private GregorianCalendar fecha;

	public void setEspecificacionMateriaPrima(
			Set<EspecificacionMateriaPrima> especificacionMateriaPrima) {
		this.especificacionMateriaPrima = especificacionMateriaPrima;
	}

	public Set<EspecificacionMateriaPrima> getEspecificacionMateriaPrima() {
		return especificacionMateriaPrima;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}
}
