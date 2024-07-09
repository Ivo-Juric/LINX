
import java.util.*;

public class Modality {

	private String description;
	private DistanceDiscipline distanceDisipline; //Composición
	
    public Modality(String description, DistanceDiscipline distanceDisipline) {
    	this.description = description;
    	distanceDisipline = new DistanceDiscipline (distanceDisipline.getDistance(), distanceDisipline.getTime(), distanceDisipline.getDiscipline());
    }
    
}