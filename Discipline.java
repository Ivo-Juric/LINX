package model;
public abstract class Discipline {
	private String discipline;
	
    public Discipline(String discipline) {
    	this.setDiscipline(discipline);
    }

    public abstract Penalty penalty();

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

}