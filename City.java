
import java.util.*;

public class City {

	private String cityName;
	private Country country; // Asociacion
	
    public City(String cityName, Country coutry, Country country) {
    	this.cityName = cityName;
    	this.country = country;
    }

}