package model;

import java.io.Serializable;

public class Penalty implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description;
    private boolean timeIncrement;

	public Penalty(String description, Boolean disqualifications) {
    	this.description = description;
    	this.timeIncrement = disqualifications;
    }

    public String getDescription() {
		return description;
	}

	public Boolean getDisqualifications() {
		return timeIncrement;
	}
	public void setDisqualifications(boolean disqualifications) {
		this.timeIncrement = disqualifications;
	}

    public void verifyDrafting(boolean draftingAllowed, boolean committedDrafting) {
        if (!draftingAllowed && committedDrafting) {
            penalizeDrafting("Illegal drafting during the cycling phase", false, 1);
        }
    }

    
    private void penalizeDrafting(String description, boolean disqualification, double time){}

}