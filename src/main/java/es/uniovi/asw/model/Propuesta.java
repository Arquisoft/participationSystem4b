package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TPROPUESTA")
public class Propuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String nombre;

	@NotNull
	private String contenido;

	@OneToMany(mappedBy = "propuesta", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<Comentario>();

	@NotNull
	private int valoracion;

	@NotNull
	private int votosMinimos;

	@Enumerated(EnumType.STRING)
	private EstadosPropuesta estado;

	public Propuesta() {
	}

	public Propuesta(String nombre, String contenido, int votosMinimos) {
		this.nombre = nombre;
		this.contenido = contenido;
		this.votosMinimos = votosMinimos;
		this.estado = EstadosPropuesta.EnTramite;
		this.valoracion = 0;
	}

	public EstadosPropuesta getEstado() {
		return estado;
	}

	public void setEstado(EstadosPropuesta estado) {
		this.estado = estado;
	}

	public void restablecerEstadoPropuesta() {
		estado = EstadosPropuesta.EnTramite;
	}

	public void rechazarPropuesta() {
		estado = EstadosPropuesta.Rechazada;
	}

	public void aceptarPropuesta() {
		estado = EstadosPropuesta.Aceptada;
	}

	public void anularPropuesta() {
		estado = EstadosPropuesta.Anulada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	Set<Comentario> _getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public int getValoracion() {
		return valoracion;
	}

	public int getVotosMinimos() {
		return votosMinimos;
	}

	public void setVotosMinimos(int votosMinimos) {
		this.votosMinimos = votosMinimos;
	}

	public long getId() {
		return id;
	}

	void _setId(long id) {
		this.id = id;
	}

	public void votoPositivo() {
		this.valoracion++;
	}

	public void votoNegativo() {
		this.valoracion--;
	}

	public void insertarComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	@Override
	public String toString() {
		String cadena = "La propuesta: '" + nombre + "' tiene un total de "
				+ valoracion + " votos y " + comentarios + " comentarios.\n";
		if (comentarios.size() > 0) {
			for (Comentario comentario : comentarios) {
				cadena += "\t" + comentario.getContenido() + ".\n";
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
		Propuesta other = (Propuesta) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
