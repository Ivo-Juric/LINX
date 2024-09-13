package model;

import java.util.ArrayList;
import java.util.List;

public class Modality {

	private String description;
	private List<DistanceDiscipline> distanceDisipline; // Composition
    
	public List<DistanceDiscipline> getDistanceDisipline() {
		return distanceDisipline;
	}


	public Modality(String modalidadDesc) {
		this.distanceDisipline = new ArrayList<>();
		this.description = modalidadDesc;
	}

	public String getDescription() {
		return description;
	}
	
	public void addDiscipline(DistanceDiscipline distanceDisiplines) {
		this.distanceDisipline.add(distanceDisiplines);
	}
	
	public String toString() {
		String sb = "";
		for (DistanceDiscipline d: distanceDisipline) {
			sb+=d.getDistance() + " ";
		}
		return sb;
	}

}