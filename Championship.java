package model;

import java.time.LocalDateTime;

public class Championship {

	private Race races; // Association
	private DistanceDiscipline distanceDisipline; // Composition
	private AthletePenalty athletePenalty; // Composition
	private LocalDateTime dateTime;
	
	 
    public Championship(Race races, DistanceDiscipline distanceDisipline, AthletePenalty athletePenalty) {
    	this.races = races;
    	distanceDisipline = new DistanceDiscipline (distanceDisipline.getDistance(), distanceDisipline.getDiscipline());
    	athletePenalty = new AthletePenalty (athletePenalty.getDateTimeFormat(dateTime), athletePenalty.getDistance(), athletePenalty.getPenalty());
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