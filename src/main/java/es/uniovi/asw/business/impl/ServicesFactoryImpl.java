package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.ComentarioService;
import es.uniovi.asw.business.PropuestaService;
import es.uniovi.asw.business.ServicesFactory;

@Service
public class ServicesFactoryImpl implements ServicesFactory {

	@Autowired
	private CitizenServiceImpl citizenService;

	@Autowired
	private PropuestaServiceImpl propuestaService;

	@Autowired
	private ComentarioServiceImpl comentarioService;

	@Override
	public CitizenService getCitizenService() {
		return citizenService;
	}

	@Override
	public PropuestaService getPropuestaService() {
		return propuestaService;
	}

	@Override
	public ComentarioService getComentarioService() {
		return comentarioService;
	}

}
