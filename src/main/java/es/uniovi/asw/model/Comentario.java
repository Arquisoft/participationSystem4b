package es.uniovi.asw.model;

public class Comentario {

	private int valoracion;
	private String contenido;
	private EstadosComentario estado;
	
	public Comentario(String contenido) {
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
	
	public EstadosComentario getEstado() {
		return estado;
	}
	
	public void setEstado(EstadosComentario estado) {
		this.estado = estado;
	}
	
	public void censurarComentario(Comentario comentario) {
		comentario.setEstado(EstadosComentario.Censurado);
	}
}
