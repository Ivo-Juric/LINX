
import java.util.*;

public class Amateur extends Athlete {
	
	private int subCategory;
	
	public Amateur(String name, String id, String nationality, Date birthDate, String gender, String category, int subCategory, double weight, double height, int percentRacesFinished, double economicbudget, int rank) {
		super(name, id, nationality, birthDate, gender, category, weight, height, percentRacesFinished, economicbudget, rank);
		this.subCategory = subCategory;
	}
	
}