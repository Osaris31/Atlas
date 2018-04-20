package atlas;
import java.util.ArrayList;import java.util.HashMap;
public class Agence {
 protected final static HashMap<String, Agence> agences = initAgences();
 public String nom; protected HashMap<String, Mission> missionsMap = new HashMap<String, Mission>(); public ArrayList<Mission> missions = new ArrayList<Mission>();
 public void afficherMissionsDisponibles(Agent agent) {  for(Mission mission : missions) {   System.out.println("- "+mission.code+(agent.missionReussies.contains(mission) ? " [Fait]" : " [Pas encore fait]"));  } }
 private static HashMap<String, Agence> initAgences() {  HashMap<String, Agence> agences = new HashMap<>();
  Agence agence = new Agence();  agence.nom = "Atlas";
  agence.ajouterMission(new Mission("Cible prioritaire", "Pour cette mission, il y a deux cibles. Renvoyer le nom de la cible la plus proche des deux.", 2, new Solution() {
   @Override   public String resoudre(Mission mission) {    return mission.cibles[0].distance<mission.cibles[1].distance ? mission.cibles[0].nom : mission.cibles[1].nom;   }  }));
  agence.ajouterMission(new Mission("Comptage de ressources", "Pour cette mission, il y a dix cibles. Renvoyer la somme de l'argent possédé par les dix cibles.", 10, new Solution() {
   @Override   public String resoudre(Mission mission) {    int argent = 0;    for(Cible c : mission.cibles) {     argent+=c.argent;    }    return ""+argent;   }  }));
  agences.put(agence.nom.toLowerCase(), agence);
  return agences; }
 private void ajouterMission(Mission mission) {  missions.add(mission);  missionsMap.put(mission.code.toLowerCase(), mission);
 }}
