package model;
import java.util.Date;

public class Competition extends Athlete {
	private static final long serialVersionUID = 1L;
	private String subCategory;

	public Competition (String number, String surname, String name, String id, String nationality, Date birthdate, String gender, double weight, double height, int finishedRaces, double economicBudget, int rank, PhysicalCondition physicalCondition, String subCategory) {
	
		super(number, surname, name, id, nationality, birthdate, gender, weight, height, finishedRaces, economicBudget, rank, physicalCondition);
		this.subCategory = subCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}
}