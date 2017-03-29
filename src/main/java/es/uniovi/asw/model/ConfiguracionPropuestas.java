package es.uniovi.asw.model;

import java.util.*;

public class ConfiguracionPropuestas {

	public List<Proposal> propuestas = new ArrayList<Proposal>();

	public List<Proposal> getPropuestas() {
		// Tiene que devolver una lista con todas las propuestas que hay en la
		// base de datos
		return null;
	}

	public void establecerVotosMinimos(int votosMinimos) {
		for (Proposal propuesta : propuestas) {
			propuesta.setVotosMinimos(votosMinimos);
		}
	}

	public void aceptarPropuestas() {
		for (Proposal propuesta : propuestas) {
			propuesta.aceptarPropuesta();
		}
	}

	public void rechazarPropuestas() {
		for (Proposal propuesta : propuestas) {
			propuesta.rechazarPropuesta();
		}
	}

	public void anularPropuestas() {
		for (Proposal propuesta : propuestas) {
			propuesta.anularPropuesta();
		}
	}

	public void restablecerEstadoPropuestas() {
		for (Proposal propuesta : propuestas) {
			propuesta.restablecerEstadoPropuesta();
		}
	}

	public List<Proposal> getPropuestasAceptadas() {
		List<Proposal> aceptadas = new ArrayList<Proposal>();
		for (Proposal propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Aceptada)) {
				aceptadas.add(propuesta);
			}
		}
		return aceptadas;
	}

	public List<Proposal> getPropuestasEnTramite() {
		List<Proposal> enTramite = new ArrayList<Proposal>();
		for (Proposal propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.EnTramite)) {
				enTramite.add(propuesta);
			}
		}
		return enTramite;
	}

	public List<Proposal> getPropuestasRechzadas() {
		List<Proposal> rechazadas = new ArrayList<Proposal>();
		for (Proposal propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Rechazada)) {
				rechazadas.add(propuesta);
			}
		}
		return rechazadas;
	}

	public List<Proposal> getPropuestasAnuladas() {
		List<Proposal> anuladas = new ArrayList<Proposal>();
		for (Proposal propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Anulada)) {
				anuladas.add(propuesta);
			}
		}
		return anuladas;
	}
}
