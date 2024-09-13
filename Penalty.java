package model;

public class Penalty {

	private String description;
    private boolean disqualifications;

    public Penalty(String description, Boolean disqualifications) {
    	this.description = description;
    	this.disqualifications = disqualifications;
    }

    public String getDescription() {
		return description;
	}

	public Boolean getDisqualifications() {
		return disqualifications;
	}
	
	public void verifySwimming(double temperature, double timeInWater, double distance, boolean wetsuitUsage) {

        if (distance <= 750) {
            if (temperature > 20 && wetsuitUsage == true) {
                penalizeSwimming("Wetsuit usage is prohibited above 20 degrees for distances of 750m or less", true);
            } else if (temperature < 14 && !wetsuitUsage == false) {
                penalizeSwimming("Wetsuit is mandatory below 14 degrees for distances of 750m or less", true);
            }
            if (timepoAtleta > 20) {
                penalizeSwimming("The athlete exceeded the maximum time to complete the swimming phase for distances of 750m or less", true);
            }
        }

        if (distance > 750 && distance <= 1500) {
            if (temperature > 20 && wetsuitUsage == true) {
                penalizeSwimming("Wetsuit usage is prohibited above 20 degrees for distances between 750m and 1500m", true);
            } else if (temperature < 14 && !wetsuitUsage == false) {
                penalizeSwimming("Wetsuit is mandatory below 14 degrees for distances between 750m and 1500m", true);
            }
            if (timepoAtleta > 40) {
                penalizeSwimming("The athlete exceeded the maximum time to complete the swimming phase for distances between 750m and 1500m", true);
            }
        }

        if (distance > 1500 && distance <= 3000) {
            if (temperature > 22 && wetsuitUsage == true) {
                penalizeSwimming("Wetsuit usage is prohibited above 22 degrees for distances between 1500m and 3000m", true);
            } else if (temperature < 15 && !wetsuitUsage == false) {
                penalizeSwimming("Wetsuit is mandatory below 15 degrees for distances between 1500m and 3000m", true);
            }
            if (timepoAtleta > 90) {
                penalizeSwimming("The athlete exceeded the maximum time to complete the swimming phase for distances between 1500m and 3000m", true);
            }
        }

        if (distance > 3000 && distance <= 4000) {
            if (temperature > 23 && wetsuitUsage == true) {
                penalizeSwimming("Wetsuit usage is prohibited above 23 degrees for distances between 3000m and 4000m", true);
            } else if (temperature < 16 && !wetsuitUsage == false) {
                penalizeSwimming("Wetsuit is mandatory below 16 degrees for distances between 3000m and 4000m", true);
            }
            if (timepoAtleta > 140) {
                penalizeSwimming("The athlete exceeded the maximum time to complete the swimming phase for distances between 3000m and 4000m", true);
            }
        }
    }

    private void penalizeSwimming(String description, boolean disqualifications) {
    }

    public void verifyMaxTimes(double maxTimeT2, double maxFinishTime) {

        if (maxTimeT2 > timepoAtleta) {
            penalizeMaxTime("The athlete exceeded the maximum time to reach T2", true);
        } else if (maxFinishTime > timepoAtleta) {
            penalizeMaxTime("The athlete exceeded the maximum time to reach the Finish Line", true);
        }
    }

    private void penalizeMaxTime(String description, boolean disqualification) {
    }

    public void verifyDrafting(boolean draftingAllowed, boolean committedDrafting) {

        if (!draftingAllowed && committedDrafting) {
            penalizeDrafting("Illegal drafting during the cycling phase", false, 1);
        }
    }

    private void penalizeDrafting(String description, boolean disqualification, double time) {
    }

}