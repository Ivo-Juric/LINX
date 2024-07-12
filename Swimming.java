
public abstract class Swimming extends Discipline {


    public Swimming() {
    	super();
    }


    public boolean useSuit(float waterTemperature) {
        return false;
    }

    public abstract Penalty penalty();

}