package model;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = 1L;

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