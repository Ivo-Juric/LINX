
import java.util.*;

public class Competition extends Athlete {
	
	private String subCategory;
	
	public Competition(String name, String id, String nationality, Date birthDate, String gender, String category, String subCategory, double weight, double height, int percentRacesFinished, double economicbudget, int rank) {
		super(name, id, nationality, birthDate, gender, category, weight, height, percentRacesFinished, economicbudget, rank);
		this.subCategory = subCategory;
	}
}