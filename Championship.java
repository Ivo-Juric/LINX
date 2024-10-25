package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Championship implements Serializable{

	private static final long serialVersionUID = 1L;
	private HashMap<String, Race> races ; // Association
	private DistanceDiscipline distanceDisipline; // Composition
	private AthletePenalty athletePenalty; // Composition
	private LocalDateTime dateTime;
	private List<Athlete> athletes;

	public Championship() {
		 races = new HashMap<>();	
		 athletes = new ArrayList<>();
	}

    public Championship(DistanceDiscipline distanceDisipline, AthletePenalty athletePenalty) {
    	distanceDisipline = new DistanceDiscipline (distanceDisipline.getDistance(), distanceDisipline.getDiscipline());
    	athletePenalty = new AthletePenalty (athletePenalty.getDateTimeFormat(dateTime), athletePenalty.getDistance(), athletePenalty.getPenalty());
    }

	public HashMap<String, Race> getRaces() {
		return races;
	}

	public DistanceDiscipline getDistanceDisipline() {
		return distanceDisipline;
	}

	public AthletePenalty getAthletePenalty() {
		return athletePenalty;
	}
	
	public List<Athlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(List<Athlete> athletes) {
		this.athletes = athletes;
	}
	

	public void setRaces(Race race) {
		athletes = race.getAthlete();
		race.setAthlete(athletes);
		this.races.put(race.getCity().getCityName(), race); 
     }
	
}