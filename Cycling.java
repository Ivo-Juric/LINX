package model;
public class Cycling extends Discipline {

	private static final long serialVersionUID = 1L;
	private boolean draft;
	private int limitTime = 0;
	
    public Cycling(String discipline){ 
    	super(discipline);
    }

    public Penalty penalty() {
		return null;
    	
    }

    public boolean isDraft() {
		return draft;
	}

	@Override
	public float getWear(float distance, Athlete at) {
		// TODO Auto-generated method stub
		return (float) ( ((distance / 2) * 0.001) / at.getPhysicalCondition().getCyclingFitness());
	}

	@Override
	public int getLimitTime() {
		// TODO Auto-generated method stub
		return limitTime;
	}

	@Override
	public void setLimitTime(float distance) {
		// TODO Auto-generated method stub
		this.limitTime = 8000000;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}
	
	 @Override
	    public void completePrevDiscipline(Athlete athlete, Race race) {
	        if (!race.isSwFin()) {
	            race.setSwFin(true);
	            athlete.getTournamentData().setSwmmWin(1);
	        }
	    }

	    @Override
	    public boolean hasFinished(Race race) {
	        return race.isSwFin();
	    }

		@Override
		public boolean prohibition(Athlete athlete) {
			// TODO Auto-generated method stub
			return false;
		}

}