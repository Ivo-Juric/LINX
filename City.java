
import java.util.*;

public class City {

	private String cityName;
	private Country country; // Asociacion
	
    public City(String cityName, Country coutry, Country country) {
    	this.cityName = cityName;
    	this.country = country;
    }

	public String getCityName() {
		return cityName;
	}

	public Country getCountry() {
		return country;
	}

}