package model;

import java.io.Serializable;

public class DistanceDiscipline implements Serializable{

	private static final long serialVersionUID = 1L;
	private float distance;
    private Discipline discipline; // Association

	
    public DistanceDiscipline(float distance, Discipline discipline) {
    	this.distance = distance;   	
    	this.discipline = discipline;
    	discipline.setLimitTime(distance*1000);
    }

	public float getDistance() {
		return distance;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}

}