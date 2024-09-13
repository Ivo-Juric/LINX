package model;
import java.util.*;

public class Amateur extends Athlete {
	
	private int subCategory;

	public Amateur (String number, String surname, String name, String id, String nationality, Date birthdate, String gender, String category, double weight, double height, int finishedRaces, double economicBudget, int rank, PhysicalCondition physicalCondition, int subCategory) {
	
		super(number, surname, name, id, nationality, birthdate, gender, category, weight, height, finishedRaces, economicBudget, rank, physicalCondition);
		this.subCategory = subCategory;
	}

	public int getSubCategory() {
		return subCategory;
	}
}

