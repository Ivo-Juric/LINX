package model;

import java.util.*;

public class DistanceDiscipline {

	private float distance;
    private Date limitTime; 
    private Discipline discipline; // Association

	
    public DistanceDiscipline(float distance, Discipline discipline) {
    	this.distance = distance;
    	this.discipline = discipline;
    }

	public float getDistance() {
		return distance;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

}