package es.uniovi.asw.business;

import org.springframework.stereotype.Service;

@Service
public interface ServicesFactory {
	public CitizenService getCitizenService();

	public PropuestaService getPropuestaService();

	public ComentarioService getComentarioService();
}
