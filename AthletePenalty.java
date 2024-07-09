
import java.util.*;

public class AthletePenalty {
	
	private Date dateTime;
	private float distance;
	private Penalty penalty; // Asociacion

    public AthletePenalty(Date dateTime, float distance, Penalty penalty) {
    	this.dateTime = dateTime;
    	this.distance = distance;
    	this.penalty = penalty;
    }

	public float getDistance() {
		return distance;
	}


	public Date getDateTime() {
		return dateTime;
	}

	public Penalty getPenalty() {
		return penalty;
	}

}