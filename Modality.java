
public class Modality {

	private String description;
	private DistanceDiscipline distanceDisipline; //Composición
	
    public Modality(String description, DistanceDiscipline distanceDisipline) {
    	this.description = description;
    	distanceDisipline = new DistanceDiscipline (distanceDisipline.getDistance(), distanceDisipline.getTime(), distanceDisipline.getDiscipline());
    }
    
	public String getDescription() {
		return description;
	}

	public DistanceDiscipline getDistanceDisipline() {
		return distanceDisipline;
	}

}