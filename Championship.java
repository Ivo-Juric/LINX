
public class Championship {

	private Race races; // Asociacion
	private DistanceDiscipline distanceDisipline; //Composición
	private AthletePenalty athletePenalty; //Composición
	 
    public Championship(Race races, DistanceDiscipline distanceDisipline, AthletePenalty athletePenalty) {
    	this.races = races;
    	distanceDisipline = new DistanceDiscipline (distanceDisipline.getDistance(), distanceDisipline.getTime(), distanceDisipline.getDiscipline());
    	athletePenalty = new AthletePenalty (athletePenalty.getDateTime(), athletePenalty.getDistance(), athletePenalty.getPenalty());
    }

	public Race getRaces() {
		return races;
	}

	public DistanceDiscipline getDistanceDisipline() {
		return distanceDisipline;
	}

	public AthletePenalty getAthletePenalty() {
		return athletePenalty;
	}
	
}