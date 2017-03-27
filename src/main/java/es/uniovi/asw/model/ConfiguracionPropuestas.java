package es.uniovi.asw.model;

import java.util.*;

public class ConfiguracionPropuestas {

	public List<Propuesta> propuestas = new ArrayList<Propuesta>();

	public List<Propuesta> getPropuestas() {
		// Tiene que devolver una lista con todas las propuestas que hay en la
		// base de datos
		return null;
	}

	public void establecerVotosMinimos(int votosMinimos) {
		for (Propuesta propuesta : propuestas) {
			propuesta.setVotosMinimos(votosMinimos);
		}
	}

	public void aceptarPropuestas() {
		for (Propuesta propuesta : propuestas) {
			propuesta.aceptarPropuesta();
		}
	}

	public void rechazarPropuestas() {
		for (Propuesta propuesta : propuestas) {
			propuesta.rechazarPropuesta();
		}
	}

	public void anularPropuestas() {
		for (Propuesta propuesta : propuestas) {
			propuesta.anularPropuesta();
		}
	}

	public void restablecerEstadoPropuestas() {
		for (Propuesta propuesta : propuestas) {
			propuesta.restablecerEstadoPropuesta();
		}
	}

	public List<Propuesta> getPropuestasAceptadas() {
		List<Propuesta> aceptadas = new ArrayList<Propuesta>();
		for (Propuesta propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Aceptada)) {
				aceptadas.add(propuesta);
			}
		}
		return aceptadas;
	}

	public List<Propuesta> getPropuestasEnTramite() {
		List<Propuesta> enTramite = new ArrayList<Propuesta>();
		for (Propuesta propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.EnTramite)) {
				enTramite.add(propuesta);
			}
		}
		return enTramite;
	}

	public List<Propuesta> getPropuestasRechzadas() {
		List<Propuesta> rechazadas = new ArrayList<Propuesta>();
		for (Propuesta propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Rechazada)) {
				rechazadas.add(propuesta);
			}
		}
		return rechazadas;
	}

	public List<Propuesta> getPropuestasAnuladas() {
		List<Propuesta> anuladas = new ArrayList<Propuesta>();
		for (Propuesta propuesta : propuestas) {
			if (propuesta.getEstado().equals(EstadosPropuesta.Anulada)) {
				anuladas.add(propuesta);
			}
		}
		return anuladas;
	}
}
