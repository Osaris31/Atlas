package atlas;

import java.util.ArrayList;
import java.util.HashMap;

public class Agence {

	protected final static HashMap<String, Agence> agences = initAgences();

	public String nom;
	protected HashMap<String, Mission> missionsMap = new HashMap<String, Mission>();
	protected HashMap<Integer, Mission> missionsMapById = new HashMap<Integer, Mission>();
	public ArrayList<Mission> missions = new ArrayList<Mission>();


	public void afficherMissionsDisponibles(Agent agent) {
		for(Mission mission : missions) {
			System.out.println("- "+mission.id+") "+mission.code+(agent.missionReussies.contains(mission) ? " [Fait]" : " [Pas encore fait]"));
		}
	}


	private static HashMap<String, Agence> initAgences() {
		HashMap<String, Agence> agences = new HashMap<>();



		/*
		 * Première agence: Atlas offre des missions faciles
		 *
		 */


		Agence agence = new Agence();
		agence.nom = "Atlas";

		agence.ajouterMission(new Mission("Cible prioritaire", "Pour cette mission, il y a deux cibles. Renvoyer le nom de la cible la plus proche des deux.", 2, new Solution() {

			@Override
			public String resoudre(Mission mission) {
				return mission.cibles[0].distance<mission.cibles[1].distance ? mission.cibles[0].nom : mission.cibles[1].nom;
			}
		}));


		agence.ajouterMission(new Mission("Argent facile", "Il y a trois cibles. Renvoyer le nom de la cible qui a le plus d'argent parmis les 3.", 3, new Solution() {

			@Override
			public String resoudre(Mission mission) {
				if(mission.cibles[0].argent>mission.cibles[1].argent && mission.cibles[0].argent>mission.cibles[2].argent) {
					return mission.cibles[0].nom;
				}
				if(mission.cibles[1].argent>mission.cibles[0].argent && mission.cibles[1].argent>mission.cibles[2].argent) {
					return mission.cibles[1].nom;
				}
				return mission.cibles[2].nom;
			}
		}));


		agence.ajouterMission(new Mission("Comptage de ressources", "Pour cette mission, il y a dix cibles. Donner la somme de l'argent possédé par les dix cibles.", 10, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int argent = 0;
				for(Cible c : mission.cibles) {
					argent+=c.argent;
				}
				return argent;
			}
		}));

		agence.ajouterMission(new Mission("Age moyen", "Pour cette mission, il y a dix cibles. Donner la moyenne d'age des cibles, arrondi au nombre entier inférieur.", 10, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int age = 0;
				for(Cible c : mission.cibles) {
					age+=c.age;
				}
				return age/10;
			}
		}));

		agence.ajouterMission(new Mission("La sagesse des anciens", "Pour cette mission, il y a 20 cibles. Donner le nombre de cibles qui ont 40 ans ou plus.", 20, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int nombre = 0;
				for(Cible c : mission.cibles) {
					if(c.age>=40) {
						nombre++;
					}
				}
				return nombre;
			}
		}));

		agence.ajouterMission(new Mission("Menace lointaine", "Atlas a identifié des cibles. Certaines sont marquées comme étant dangereuses. Donner la distance de la cible dangereuse qui est la plus éloignée.", 20, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				float distanceMax = 0;
				for(Cible c : mission.cibles) {
					if(c.distance>=distanceMax && c.dangereux) {
						distanceMax = c.distance;
					}
				}
				return distanceMax;
			}
		}));

		agences.put(agence.nom.toLowerCase(), agence);




		/*
		 * Deuxième agence: DGSE offre des missions plus difficiles
		 *
		 */
		agence = new Agence();
		agence.nom = "DGSE";


		agence.ajouterMission(new Mission("Etude démographique", "Donner la différence d'age entre la cible la plus jeune et la plus agée", 10, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int ageMin = 1000;
				int ageMax = 0;
				for(Cible c : mission.cibles) {
					if(c.age<ageMin) {
						ageMin = c.age;
					}
					if(c.age>ageMax) {
						ageMax = c.age;
					}
				}
				return ageMax-ageMin;
			}
		}));

		agence.ajouterMission(new Mission("Mauvaises fréquentations", "Donner le nombre de cibles qui connaissent au moins 1 cible marquée comme étant dangereuse.", 20, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int nombre = 0;
				for(Cible c : mission.cibles) {
					for(Cible c2 : c.connaissances) {
						if(c2.dangereux) {
							nombre++;
							break;
						}
					}
				}
				return nombre;
			}
		}));

		agence.ajouterMission(new Mission("Cible ultra prioritaire", "Donner le nom de la cible qui connait le plus de cibles marquées comme étant dangereuses.", 100, new Solution() {

			@Override
			public Object resoudre(Mission mission) {
				int nombreMax = 0;
				String nom = "";
				for(Cible c : mission.cibles) {
					int nombre = 0;

					for(Cible c2 : c.connaissances) {
						if(c2.dangereux) {
							nombre++;
						}
					}

					if(nombre>=nombreMax) {
						nombreMax = nombre;
						nom = c.nom;
					}
				}
				return nom;
			}
		}));


		agence.ajouterMission(new Mission("Classement des fortunes", "Il y a 10 cibles. Votre mission est de donner les cibles dans l'ordre de richesse, en partant de ceux ayant le moins d'argent._nRenvoyer un tableau Cible[10].\nIndication : Utiliser le tri à bulle", 10, new Solution() {

			/* Algorithme : Tri à bulle
			 * tri_à_bulles(Tableau T)
   				pour i allant de taille de T - 1 à 1
       				pour j allant de 0 à i - 1
           				si T[j+1] < T[j]
               				échanger(T[j+1], T[j])
			 */


			@Override
			public Cible[] resoudre(Mission mission) {

				for(int i=mission.cibles.length;i>1;i--) {
					for(int j=0;j<i-1;j++) {
						if(mission.cibles[j+1].argent<mission.cibles[j].argent) {
							Cible echange = mission.cibles[j+1];
							mission.cibles[j+1] = mission.cibles[j];
							mission.cibles[j] = echange;
						}
					}
				}


				return mission.cibles;
			}
		}));


		agence.ajouterMission(new Mission("Personnes clés", "Il y a 20 cibles. Si on espionne une cible, on a automatiquement aussi des informations sur les cibles qu'elle connait.\nPour utiliser au mieux nos 3 agents de terrain disponibles, on va espionner seulement 3 cibles qu'on choisira au mieux pour qu'à 3 elles connaissent le plus de personnes différentes.\nDonner en résultat le nombre de cibles différentes sur lesquelles on obtiendra ainsi des informations, en ayant bien choisi les 3 cibles pour que ce soit le nombre maximal.", 20, new Solution() {

			@Override
			public Object resoudre(Mission mission) {

				ArrayList<Cible> ciblesCouvertes = new ArrayList<>();
				int nombreMaxCiblesCouvertes = 0;

				for(Cible c1 : mission.cibles) {
					for(Cible c2 : mission.cibles) {
						for(Cible c3 : mission.cibles) {
							if(c1!=c2 && c2!=c3) {
								ciblesCouvertes.clear();
								ciblesCouvertes.add(c1);
								ciblesCouvertes.add(c2);
								ciblesCouvertes.add(c3);

								for(Cible connaissance1 : c1.connaissances) {
									if(!ciblesCouvertes.contains(connaissance1)) ciblesCouvertes.add(connaissance1);
								}

								for(Cible connaissance2 : c2.connaissances) {
									if(!ciblesCouvertes.contains(connaissance2)) ciblesCouvertes.add(connaissance2);
								}

								for(Cible connaissance3 : c3.connaissances) {
									if(!ciblesCouvertes.contains(connaissance3)) ciblesCouvertes.add(connaissance3);
								}

								if(nombreMaxCiblesCouvertes<ciblesCouvertes.size()) nombreMaxCiblesCouvertes = ciblesCouvertes.size();
							}
						}
					}
				}


				return nombreMaxCiblesCouvertes;
			}
		}));


		agence.ajouterMission(new Mission("Cracker le mot de passe", "Trouver le mot de passe des systèmes informatiques de l'agence ennemie. Il est composé de 5 lettres en minuscule.\nExemple : azert\nPour tester si le mot de passe est correct, utiliser Authentification.testerMotDePasse(String motDePasse) qui renvoie true si c'est le bon mot de passe.", 0, new Solution() {

			@Override
			public String resoudre(Mission mission) {

				for(char c='a';c<='z';c++) {
					for(char c2='a';c2<='z';c2++) {
						for(char c3='a';c3<='z';c3++) {
							for(char c4='a';c4<='z';c4++) {
								for(char c5='a';c5<='z';c5++) {
									String motDePasse = ""+c+c2+c3+c4+c5;

									if(Authentification.testerMotDePasse(motDePasse)) {
										return motDePasse;
									}
								}
							}
						}
					}
				}


				return "pas trouvé";
			}
		}));


		agences.put(agence.nom.toLowerCase(), agence);


		return agences;
	}


	private void ajouterMission(Mission mission) {
		missions.add(mission);
		missionsMap.put(mission.code.toLowerCase(), mission);


		mission.id=missions.size();
		missionsMapById.put(mission.id, mission);
	}
}
