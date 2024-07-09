
import java.util.*;

public class DistanceDiscipline {

	private float distance;
    private Date time; 
    private Discipline discipline; // Asociacion
	
    public DistanceDiscipline(float distance, Date time, Discipline discipline) {
    	this.distance = distance;
    	this.time = time;
    	this.discipline = discipline;
    }

	public float getDistance() {
		return distance;
	}

	public Date getTime() {
		return time;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

}