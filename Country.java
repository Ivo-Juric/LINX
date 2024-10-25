package model;

import java.io.Serializable;

public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String countryName;
	
    public Country(String country) {
    	this.countryName = country;
    }

    public String getCountryName() {
		return countryName;
	}

}