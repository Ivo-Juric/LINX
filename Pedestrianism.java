package model;
public class Pedestrianism extends Discipline {

    private static final long serialVersionUID = 1L;

	public Pedestrianism(String discipline) {
    	super(discipline);
    }

    public Penalty penalty() {
		return null;
    	
    }

	@Override
	public float getWear(float distance, Athlete at) {
		// TODO Auto-generated method stub
		return (float) ( distance * 0.01 / at.getPhysicalCondition().getPedestrianFitness());
	}

	@Override
	public int getLimitTime() {
		return 0;
	} 
	@Override
    public void completePrevDiscipline(Athlete athlete, Race race) {
        if (!race.isCyFin()) {
            race.setCyFin(true);
            athlete.getTournamentData().setCycWin(1);
        }
    }

    @Override
    public boolean hasFinished(Race race) {
        return race.isCyFin();
    }

	@Override
	public void setLimitTime(float distance) {}

	@Override
	public boolean prohibition(Athlete athlete) {
		return false;
	}

}