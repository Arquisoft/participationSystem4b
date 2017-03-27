package es.uniovi.asw.persistence;

public interface PersistenceFactory {

	public CitizenRepository newCitizenRepository();

	public PropuestaRepository newPropuestaRepository();

	public ComentarioRepository newComentarioRepository();
}
