
import java.util.*;

public class Amateur extends Athlete {
	
	private int subCategory;
	
	public Amateur(String name, String id, String nationality, Date birthDate, String gender, String category, int subCategory) {
		super(name, id, nationality, birthDate, gender, category);
		this.subCategory = subCategory;
	}
	
}