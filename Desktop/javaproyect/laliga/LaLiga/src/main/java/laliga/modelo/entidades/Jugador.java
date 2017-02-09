package laliga.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import laliga.modelo.enumeraciones.JugadoresCategoria;


@Entity
public class Jugador {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	private Integer edad;
	
	@NotNull
	private Integer goles;
	
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name = "equipo", nullable = true)
	@JsonIgnore
	private Equipo equipo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private JugadoresCategoria posicion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getGoles() {
		return goles;
	}

	public void setGoles(Integer goles) {
		this.goles = goles;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public JugadoresCategoria getPosicion() {
		return posicion;
	}

	public void setPosicion(JugadoresCategoria posicion) {
		this.posicion = posicion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + ((equipo == null) ? 0 : equipo.hashCode());
		result = prime * result + ((goles == null) ? 0 : goles.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (equipo == null) {
			if (other.equipo != null)
				return false;
		} else if (!equipo.equals(other.equipo))
			return false;
		if (goles == null) {
			if (other.goles != null)
				return false;
		} else if (!goles.equals(other.goles))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (posicion != other.posicion)
			return false;
		return true;
	}

	
}
