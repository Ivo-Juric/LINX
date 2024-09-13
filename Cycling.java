package model;
public class Cycling extends Discipline {

	private boolean draft;
	
    public Cycling(String discipline){ 
    	super(discipline);
    }

    public Penalty penalty() {
		return null;
    	
    }

    public boolean isDraft() {
		return draft;
	} 

}