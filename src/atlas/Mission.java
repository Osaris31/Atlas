package atlas;

import java.util.Random;

public class Mission {

	public Mission(String code, String objectif, int nombreCibles, Solution solutionPrevue) {
		this.code = code;
		this.objectif = objectif;
		this.nombreCibles = nombreCibles;
		this.solutionPrevue = solutionPrevue;

	}

	/**
	 * Nom de code de la mission
	 */
	public String code;

	/**
	 * Objectif
	 */
	public String objectif;

	/**
	 * Données d'entrée
	 */
	public Cible[] cibles;
	public int nombreCibles = 10;

	/**
	 * Solution
	 */
	private Solution solutionPrevue;


	public boolean testeSolution(Solution solution) {
		System.out.println("------------------------------");
		System.out.println("Mission lancée : "+code);
		System.out.println("------------------------------");

		genererCibles();

		String resultat = "";
		String resultatAttendu = solutionPrevue.resoudre(this);

		try{
			resultat = solution.resoudre(this);
			System.out.println("Résultat de la mission : "+resultat);
			if(resultat!=null && resultatAttendu.trim().toLowerCase().equals(resultat.toLowerCase().trim())) {
				System.out.println("------------------------------");
				System.out.println("Mission réussie!");
				System.out.println("------------------------------");

				return true;

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------");
		System.out.println("Mission échouée :(");
		System.out.println("On a trouvé "+resultat+" mais il fallait trouver "+resultatAttendu);
		System.out.println("------------------------------");
		return false;
	}

	private void genererCibles() {
		cibles = new Cible[nombreCibles];
		for(int i=0;i<nombreCibles;i++) {
			cibles[i] = new Cible();
			cibles[i].nom = generateName();
			cibles[i].age = (Math.abs(rand.nextInt())%56)+18;
			cibles[i].argent = (Math.abs(rand.nextInt())%5000000);
			cibles[i].distance = rand.nextFloat()*6000f + 250;
			cibles[i].dangereux = rand.nextDouble()<0.3;
		}

		for(int i=0;i<nombreCibles;i++) {
			int nbConnaissances = Math.abs(rand.nextInt())%(nombreCibles/3+1);
			for(int j=0;j<nbConnaissances;j++) {
				int c = Math.abs(rand.nextInt())%nombreCibles;
				if(i!=c && !cibles[i].connaissances.contains(cibles[c])) {
					cibles[i].connaissances.add(cibles[c]);
					cibles[c].connaissances.add(cibles[i]);
				}
			}
			System.out.println("Cible "+i+": ");
			cibles[i].afficher();
		}
	}


	private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
		"Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
		"Zork", "Mad", "Cry", "Zur", "Creo",  "Azur", "Rei", "Cro",
		"Mar", "Luk","Ad","Ae","Ara","Bal","Bei","Bi","Bry","Cai","Car","Chae","Cra","Da",
		"Dae","Dor","Eil","El","Ela","En","Er","Fa","Fae","Far","Fen","Gen","Gil","Glyn",
		"Gre","Hei","Hele","Her","Ian","Iar","Ili","Ina","Jo","Kea","Kel","Key",
		"Kris","Leo","Lia","Lora","Lu","Mag","Mia","Mor","Nae","Neri","Nor","Ola",
		"Olo","Oma","Ori","Pa","Per","Pet","Phi","Pres","Qi","Qin","Qui",
		"Rey","Ro","Sar","Sha","Syl","The","Tor","Tra","Tris","Ula","Ume","Uri","Va",
		"Val","Ven","Vir","Waes","Wran","Wyn",
		"Xyr","Yel","Yes","Yin","Zin","Zum","Zyl" };
	private static String[] Middle = { "air", "ir", "mit", "sor", "meel", "clo",
		"red", "cran", "ark", "arc", "mirid", "loris", "cres", "mur", "zer",
		"marac", "zoir", "h", "d", "z", "g", "m", "n", "salmar", "urak",
		"balar","ban","bel","ber","can","car","ceran","cyn","dan","di","dith",
		"dov","faren","fiel","fin","fir","geir","gel","golor","gwyn","han","hari",
		"hice","horn","jeon","jor","jyr","kal","kas","kian","krana","lam","lan","lar",
		"lee","len","leth","lynn","maer","mar","men","moir","myar","mys","na","nal","nan",
		"ral","ran","rel","ren","ric","ri","rieth","ris","ro","ron","rora","ror","salor",
		"sand","satr","stin","sys","than","thyr","tor","tris","tum","val","var",
		"ynor","yr","zana","zeir","zorw","zum"
	};
	private static String[] End = { "id", "ed", "ah", "arc", "es", "er", "en", "io", "a", "is", "in", "os", "in", "yn", "yh", "y", "as", "ean",
		"it", "ed", "ure", "azur", "ic", "an", "e", "eh", "i", "ih", "o", "oh", "ice", "ace", "oce", "u", "uh" };

	private static Random rand = new Random();


	private static String generateName() {

		String name = Beginning[rand.nextInt(Beginning.length)] +
				Middle[rand.nextInt(Middle.length)];
		if(name.length()<8 && Math.random()<0.5) {
			name+=End[rand.nextInt(End.length)];
		}
		return name;


	}
}
