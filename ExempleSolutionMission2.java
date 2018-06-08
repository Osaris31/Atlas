import atlas.Mission;
import atlas.Solution;


public class ExempleSolutionMission2 implements Solution {

	@Override
	public Object resoudre(Mission mission) {
		if(mission.cibles[0].argent>mission.cibles[1].argent && mission.cibles[0].argent>mission.cibles[2].argent) {
			return mission.cibles[0].nom;
		}
		if(mission.cibles[1].argent>mission.cibles[0].argent && mission.cibles[1].argent>mission.cibles[2].argent) {
			return mission.cibles[1].nom;
		}
		return mission.cibles[2].nom;
	}

}
