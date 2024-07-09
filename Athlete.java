
import java.util.*;

public class Athlete {

	private String name;
	private String id;
	private String nationality;
	private Date birthDate;
	private String gender;
	private String category;
	private PhysicalCondition physicalCondition; //Composición
	private Championship championship; //Composición
	
	public Athlete(String name, String id, String nationality, Date birthDate, String gender, String category) {
		this.name = name;
		this.id = id;
		this.nationality = nationality;
		this.birthDate = birthDate;
		this.gender = gender;
		this.category = category;
		physicalCondition = new PhysicalCondition(physicalCondition.getSpeed(), physicalCondition.getEndurance());
		championship = new Championship(championship.getRaces(), championship.getDistanceDisipline(), championship.getAthletePenalty());
	}
}

