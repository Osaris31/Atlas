import atlas.Agent;
import atlas.Solution;


public class ExempleMain {
	public static void main(String args[]) {
		Agent agent = new Agent();
		agent.nom = "Smith";
		agent.prenom = "John";

		agent.rejoindreAgence("Atlas");

		agent.afficherInformationsAgent();

		agent.afficherMissionsDisponibles();



		agent.choisirMission("Cible prioritaire");

		Solution solution = new ExempleSolutionMission1();

		agent.accomplirMission(solution);



		agent.afficherMissionsDisponibles();



		agent.choisirMission("Comptage de ressources");

		solution = new ExempleSolutionMission2();

		agent.accomplirMission(solution);

	}
}
