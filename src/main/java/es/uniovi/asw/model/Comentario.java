package es.uniovi.asw.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.uniovi.asw.model.types.keys.ComentarioKey;

@Entity
@IdClass(ComentarioKey.class)
@Table(name = "TCOMENTARIO")
public class Comentario {
	@Id
	@ManyToOne
	private Citizen citizen;

	@Id
	@ManyToOne
	private Propuesta propuesta;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@NotNull
	private int valoracion;

	@NotNull
	private String contenido;

	@Enumerated(EnumType.STRING)
	private EstadosComentario estado;

	Comentario() {
	}

	public Comentario(Citizen citizen, Propuesta propuesta, String contenido) {
		Association.Comenta.link(citizen, this, propuesta);
		Calendar calendar = GregorianCalendar.getInstance();
		this.fechaCreacion = new Date(calendar.getTimeInMillis());
		this.contenido = contenido;
		this.valoracion = 0;
		this.estado = EstadosComentario.Correcto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		estado = EstadosComentario.Editado;
		this.contenido = contenido;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public EstadosComentario getEstado() {
		return estado;
	}

	public void setEstado(EstadosComentario estado) {
		this.estado = estado;
	}

	public void censurarComentario(Comentario comentario) {
		comentario.setEstado(EstadosComentario.Censurado);
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	void _setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	void _setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Comentario [citizen=" + citizen + ", propuesta=" + propuesta
				+ ", fechaCreacion=" + fechaCreacion + ", valoracion="
				+ valoracion + ", contenido=" + contenido + ", estado=" + estado
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result
				+ ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result
				+ ((propuesta == null) ? 0 : propuesta.hashCode());
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
		Comentario other = (Comentario) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (propuesta == null) {
			if (other.propuesta != null)
				return false;
		} else if (!propuesta.equals(other.propuesta))
			return false;
		return true;
	}

}
