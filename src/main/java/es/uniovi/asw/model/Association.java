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
	
	public static class Vota {
		public static void link(Citizen citizen, Vote vote, Proposal proposal) {
			vote.setCitizen(citizen);
			vote.setProposal(proposal);
			citizen.getVotes().add(vote);
			proposal.getVotes().add(vote);
		}
		
		public static void unlink(Vote vote) {
			vote.getCitizen().getVotes().remove(vote);
			vote.getProposal().getVotes().remove(vote);
			vote.setCitizen(null);
			vote.setProposal(null);
		}

	}

}
