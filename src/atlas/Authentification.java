package atlas;

import java.util.Random;

public class Authentification {
	private static String motDePasse = "";

	static {
		Random r = new Random();
		r.setSeed(System.currentTimeMillis());
		motDePasse = "";
		for(int i=0;i<5;i++) {
			motDePasse+=(char)('a'+r.nextInt(26));
		}
	}

	public static boolean testerMotDePasse(String motDePasse_) {
		//	System.out.println(motDePasse+" "+motDePasse_);
		return motDePasse.equals(motDePasse_);
	}
}
