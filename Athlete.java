
import java.util.*;

public class Athlete {

	private String name;
	private String id;
	private String nationality;
	private Date birthDate;
	private String gender;
	private String category;
	private double weight;
	private double height;
	private int percentRacesFinished;
	private double economicbudget;
	private int rank;
	private PhysicalCondition physicalCondition; //Composición
	private Championship championship; //Composición
	
	public Athlete(String name, String id, String nationality, Date birthDate, String gender, String category, double weight, double height, int percentRacesFinished, double economicbudget, int rank) {
		this.name = name;
		this.id = id;
		this.nationality = nationality;
		this.birthDate = birthDate;
		this.gender = gender;
		this.category = category;
		this.weight = weight;
		this.height = height;
		this.percentRacesFinished = percentRacesFinished;
		this.economicbudget = economicbudget;
		this.rank = rank;
		physicalCondition = new PhysicalCondition(getSwimmingFitness(), getCyclingFitness(), getPedestrianFitness(), getResistance(), getPsychologicalStrength());
		championship = new Championship(championship.getRaces(), championship.getDistanceDisipline(), championship.getAthletePenalty());
	}
}

