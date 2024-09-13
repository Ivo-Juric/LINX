package model;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class AthletePenalty {
	
	private String dateTime;
	private float distance;
	private Penalty penalty; // Association

    public AthletePenalty(String dateTime, float distance, Penalty penalty) {
    	this.dateTime = dateTime;
    	this.distance = distance;
    	this.penalty = penalty;
    }

	public float getDistance() {
		return distance;
	}


	public String getDateTimeFormat(LocalDateTime dateTime) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    return dateTime.format(formatter);
	}


	public Penalty getPenalty() {
		return penalty;
	}
	
	 public String toString() {
	        return "PenalizacionAtleta cometida en esta fecha y hora: Fecha y Hora: " + dateTime + ", a la distancia de: " + distance 
	               + ", y la razon de la penalizacion fue la siguiente:" + penalty.toString() + "]";
	 }
}