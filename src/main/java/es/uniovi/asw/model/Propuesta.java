package es.uniovi.asw.model;

import java.util.List;

public class Propuesta {

	private long id;
	private String nombre;
	private String contenido;
	private List<Comentario> comentarios;
	private int valoracion;
	private int votosMinimos;
	private EstadosPropuesta estado;

	public Propuesta(String nombre, String contenido, int votosMinimos) {
		super();
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
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
		String cadena = "La propuesta: '" + nombre + "' tiene un total de " + valoracion + " votos y " + comentarios
				+ " comentarios.\n";
		if (comentarios.size() > 0) {
			for (Comentario comentario : comentarios) {
				cadena += "\t" + comentario.getContenido() + ".\n";
			}
		}

		return cadena;
	}
}
