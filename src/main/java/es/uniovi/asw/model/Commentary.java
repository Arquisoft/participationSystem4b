package es.uniovi.asw.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.uniovi.asw.model.types.keys.ComentaryKey;

@Entity
@IdClass(ComentaryKey.class)
@Table(name = "TCOMENTARIOS")
public class Commentary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Id
	@ManyToOne
	private Citizen citizen;

	@Id
	@ManyToOne
	private Proposal proposal;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@NotNull
	private int valoration;

	@NotNull
	private String content;

	@Enumerated(EnumType.STRING)
	private EstadosComentario status;

	Commentary() {
	}

	public Commentary(Citizen citizen, Proposal proposal, String content) {
		Association.Comenta.link(citizen, this, proposal);
		Calendar calendar = GregorianCalendar.getInstance();
		this.creationDate = new Date(calendar.getTimeInMillis());
		this.content = content;
		this.valoration = 0;
		this.status = EstadosComentario.Correcto;
	}

	public String getContenido() {
		return content;
	}

	public void setContenido(String content) {
		status = EstadosComentario.Editado;
		this.content = content;
	}

	public int getValoracion() {
		return valoration;
	}

	public void setValoracion(int valoration) {
		this.valoration = valoration;
	}

	public EstadosComentario getEstado() {
		return status;
	}

	public void setEstado(EstadosComentario status) {
		this.status = status;
	}

	public void censurarComentario(Commentary comment) {
		comment.setEstado(EstadosComentario.Censurado);
	}

	public Proposal getProposal() {
		return proposal;
	}

	void _setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	void _setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Comentario [citizen=" + citizen + ", proposal=" + proposal
				+ ", fechaCreacion=" + creationDate + ", valoracion="
				+ valoration + ", contenido=" + content + ", estado=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((proposal == null) ? 0 : proposal.hashCode());
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
		Commentary other = (Commentary) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (proposal == null) {
			if (other.proposal != null)
				return false;
		} else if (!proposal.equals(other.proposal))
			return false;
		return true;
	}

}
