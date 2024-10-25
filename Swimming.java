package model;

import java.util.Random;

public class Swimming extends Discipline {
	private double waterTemp;
	private boolean suit = false;
	private int limitTime = 0;
	private static final long serialVersionUID = 1L;

	public Swimming(String discipline) {
		super(discipline);
		Random r = new Random();
    	waterTemp = r.nextInt(7, 25);
    	setSuit(waterTemp);
    }


    public boolean useSuit() {
        return suit;
    }

    public Penalty penalty() {
		return null;
    	
    }

    public double getWaterTemp() {
		return waterTemp;
	}


	public void setSuit(double waterTemp) {
		if (waterTemp <  14)
			this.suit = true;
	}


	public void setWaterTemp(double waterTemp) {
		this.waterTemp = waterTemp;
	}


	@Override
	public float getWear(float distance, Athlete at) {
		
		return (float) ((waterTemp*distance)/(500 * at.getPhysicalCondition().getSwimmingFitness()));
	}
	@Override
	public void setLimitTime(float distance) {
		if (distance <= 750)
			limitTime = 1200000;
		else if (distance > 750 && distance <= 1500)
			limitTime = 2400000;
			else if (distance > 1500 && distance <= 3000)
				limitTime = 5400000;
				else 
					limitTime = 8400000; 
	}

	public int getLimitTime() {
		return limitTime; 
	}
	public void completePrevDiscipline(Athlete athlete, Race race) {}

	    @Override
	 public boolean hasFinished(Race race) {
	        return false;
	    }

	@Override
	public boolean prohibition(Athlete athlete) {
		return (athlete.getTournamentData().isNeoprene() != suit);
	}

}