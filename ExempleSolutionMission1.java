import atlas.Mission;
import atlas.Solution;


public class ExempleSolutionMission1 implements Solution {

	@Override
	public Object resoudre(Mission mission) {
		if(mission.cibles[0].distance<mission.cibles[1].distance) {
			return mission.cibles[0].nom;
		}
		else {
			return mission.cibles[1].nom;
		}
	}

}
