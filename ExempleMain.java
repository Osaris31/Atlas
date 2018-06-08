import atlas.Agent;
import atlas.Solution;


public class ExempleMain {
	public static void main(String args[]) {

		// on créé d'abord son objet Agent avec son nom et prénom
		Agent agent = new Agent();
		agent.nom = "Smith";
		agent.prenom = "John";


		// puis on rejoint une agence, il y en a actuellement 2, Atlas pour les premières missions et DGSE pour des missions plus complexes
		agent.rejoindreAgence("Atlas");

		agent.afficherInformationsAgent();

		// par cette commande on voit la liste des missions disponible
		agent.afficherMissionsDisponibles();


		// on en choisit une par son nom, cela a pour effet d'afficher le briefing
		agent.choisirMission("Cible prioritaire");
		// toutes les missions sont des problèmes qui demandent de travailler sur un tableau d'objets Cible donné en entré, pour retourner un résultat précis


		// on doit alors résoudre la mission en créant une classe qui implement Solution
		Solution solution = new ExempleSolutionMission1();
		agent.accomplirMission(solution);



		agent.afficherMissionsDisponibles();



		agent.choisirMission("Argent facile");

		solution = new ExempleSolutionMission2();
		agent.accomplirMission(solution);





		agent.afficherMissionsDisponibles();



		agent.choisirMission(3); // on peut aussi sélectionner la mission par son numéro

		solution = new ExempleSolutionMission3();
		agent.accomplirMission(solution);





	}
}
