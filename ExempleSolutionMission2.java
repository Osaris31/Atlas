import atlas.Mission;
import atlas.Solution;


public class ExempleSolutionMission2 implements Solution {

	@Override
	public String resoudre(Mission mission) {
		int argent = 0;

		for(int i=0;i<10;i++) {
			argent+=mission.cibles[i].argent;
		}

		return ""+argent;
	}

}
