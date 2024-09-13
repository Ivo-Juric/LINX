package model;
public class Swimming extends Discipline {


    public Swimming(String discipline) {
    	super(discipline);
    }


    public boolean useSuit(float waterTemperature) {
        return false;
    }

    public Penalty penalty() {
		return null;
    	
    }

}