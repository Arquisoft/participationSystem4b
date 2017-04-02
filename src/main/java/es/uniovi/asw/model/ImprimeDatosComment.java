package es.uniovi.asw.model;

public class ImprimeDatosComment {
	
	private String nombre;
	private String content;
	private String date;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ImprimeDatosComment(){}

	@Override
	public String toString() {
		return "Comentario subido por " + nombre + "\n\t" + content + ".\n\t" + date ;
	}
	
	
}
