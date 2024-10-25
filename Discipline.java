package model;

import java.io.Serializable;

public abstract class  Discipline implements Serializable{

	private static final long serialVersionUID = 1L;
	private String discipline;
	
    public Discipline(String discipline) {
    	this.setDiscipline(discipline);
    }

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	
	public abstract float getWear(float distance, Athlete at);
	
	public abstract boolean prohibition(Athlete athlete);

	public abstract int getLimitTime();
	
	public abstract void setLimitTime(float distance);

	public abstract boolean hasFinished(Race currentRace);

	public abstract void completePrevDiscipline(Athlete athlete, Race currentRace);

}