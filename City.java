package model;

public class City {

	private String cityName;
	private Country country; // Association
	
    public City(String cityName, Country country) {
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