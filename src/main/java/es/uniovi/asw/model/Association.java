package es.uniovi.asw.model;

public class Association {
	public static class Comenta {
		public static void link(Citizen citizen, Commentary comentario,
				Proposal propuesta) {
			comentario._setCitizen(citizen);
			comentario._setProposal(propuesta);
			citizen._getComentarios().add(comentario);
			propuesta._getComments().add(comentario);
		}

		public static void unlink(Commentary comentario) {
			comentario.getCitizen()._getComentarios().remove(comentario);
			comentario.getProposal()._getComments().remove(comentario);
			comentario._setCitizen(null);
			comentario._setProposal(null);
		}
	}
}
