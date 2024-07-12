
import java.util.*;

public class Penalty {

	private String description;
    private String disqualifications;

    public Penalty(String description, String disqualifications) {
    	this.description = description;
    	this.disqualifications = disqualifications;
    }

    public String getDescription() {
		return description;
	}

	public String getDisqualifications() {
		return disqualifications;
	}

}