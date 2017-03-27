package es.uniovi.asw.business;

import org.springframework.stereotype.Service;

@Service
public interface ServicesFactory {
	CitizenService getCitizenService();

	PropuestaService getPropuestaService();

	ComentarioService getComentarioService();
}
