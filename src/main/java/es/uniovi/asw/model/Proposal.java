package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TPROPUESTA")
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String content;

	@OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER)
	private Set<Commentary> comments = new HashSet<Commentary>();

	@NotNull
	private int valoration;

	@NotNull
	private int minVotes;

	@Enumerated(EnumType.STRING)
	private EstadosPropuesta status;

	public Proposal() {
	}

	public Proposal(String name, String content, int minVotes) {
		this.name = name;
		this.content = content;
		this.minVotes = minVotes;
		this.status = EstadosPropuesta.EnTramite;
		this.valoration = 0;
	}

	public EstadosPropuesta getStatus() {
		return status;
	}

	public void setStatus(EstadosPropuesta status) {
		this.status = status;
	}

	public void restoreEstadoPropuesta() {
		status = EstadosPropuesta.EnTramite;
	}

	public void refuseProposal() {
		status = EstadosPropuesta.Rechazada;
	}

	public void acceptProposal() {
		status = EstadosPropuesta.Aceptada;
	}

	public void cancelProposal() {
		status = EstadosPropuesta.Anulada;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	Set<Commentary> _getComments() {
		return comments;
	}

	public void setComments(Set<Commentary> comments) {
		this.comments = comments;
	}

	public int getValoration() {
		return valoration;
	}

	public int getMinVotes() {
		return minVotes;
	}

	public void setMinVotes(int minVotes) {
		this.minVotes = minVotes;
	}

	public long getId() {
		return id;
	}

	void _setId(long id) {
		this.id = id;
	}

	public void positiveVote() {
		this.valoration++;
	}

	public void negativeVote() {
		this.valoration--;
	}

	public void insertComment(Commentary comment) {
		this.comments.add(comment);
	}

	@Override
	public String toString() {
		String cadena = "La propuesta: '" + name + "' tiene un total de "
				+ valoration + " votos y " + comments + " comments.\n";
		if (comments.size() > 0) {
			for (Commentary comment : comments) {
				cadena += "\t" + comment.getContenido() + ".\n";
			}
		}

		return cadena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Proposal other = (Proposal) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
