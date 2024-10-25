package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Modality implements Serializable {

	private static final long serialVersionUID = 1L;
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

}