package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.ComentarioService;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.persistence.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}

	@Override
	public Comentario findById(long id) {
		return comentarioRepository.findByID(id);
	}

	@Override
	public Comentario findByCitizen(String dni) {
		return comentarioRepository.findByDni(dni);
	}

	@Override
	public void save(Comentario comentario) {
		comentarioRepository.save(comentario);
	}

}
