package es.uniovi.asw.model;

public class Association {
	public static class Comenta {
		public static void link(Citizen citizen, Comentario comentario,
				Propuesta propuesta) {
			comentario._setCitizen(citizen);
			comentario._setPropuesta(propuesta);
			citizen._getComentarios().add(comentario);
			propuesta._getComentarios().add(comentario);
		}

		public static void unlink(Comentario comentario) {
			comentario.getCitizen()._getComentarios().remove(comentario);
			comentario.getPropuesta()._getComentarios().remove(comentario);
			comentario._setCitizen(null);
			comentario._setPropuesta(null);
		}
	}
}
