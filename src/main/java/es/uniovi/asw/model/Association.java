package es.uniovi.asw.model;

public class Association {
	public static class Comenta {
		public static void link(Citizen citizen, Commentary comentario,
				Proposal propuesta) {
			comentario.setCitizen(citizen);
			comentario.setProposal(propuesta);
			citizen.getComentarios().add(comentario);
			propuesta.getComments().add(comentario);
		}

		public static void unlink(Commentary comentario) {
			comentario.getCitizen().getComentarios().remove(comentario);
			comentario.getProposal().getComments().remove(comentario);
			comentario.setCitizen(null);
			comentario.setProposal(null);
		}
	}
}
