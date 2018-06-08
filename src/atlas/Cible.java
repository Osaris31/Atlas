package atlas;

import java.util.ArrayList;

public class Cible {
	public String nom;
	public int age;
	public int argent;
	public float distance;
	public boolean dangereux = false;
	public ArrayList<Cible> connaissances = new ArrayList<>();


	public void afficher() {
		System.out.println("-> "+nom+", "+argent+" €, "+age+" ans, à "+distance+" km"+(dangereux ? ", dangereux" : "")+"");
		if(connaissances.size()>0) System.out.println("->   Connaissances : "+connaissances);
	}

	@Override
	public String toString() {
		return nom;
	}
}
