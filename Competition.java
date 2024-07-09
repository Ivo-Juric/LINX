
import java.util.*;

public class Competition extends Athlete {
	
	private String subCategory;
	
	public Competition(String name, String id, String nationality, Date birthDate, String gender, String category, String subCategory) {
		super(name, id, nationality, birthDate, gender, category);
		this.subCategory = subCategory;
	}
}