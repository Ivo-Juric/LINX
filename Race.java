package model;

import java.util.*;

public class Race {

	private Date date;
    private Athlete athlete; // Aggregation
    private Modality modality; // Association
    private City city; // Association
    private List<Provisioning>  provisioning = new ArrayList<>();; // Composition
    private List<ClimaticCondition> climaticCondition = new ArrayList<>(); // Composition

    public Race(Date date, Modality modality, City city, List<Provisioning> provisioning ) {
        this.date = date;
        this.modality = modality;
        this.city = city;
        this.provisioning.addAll(provisioning);
    }

    public Date getDate() {
        return date;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Modality getModality() {
        return modality;
    }

    public City getCity() {
        return city;
    }

    public List<Provisioning> getProvisioning() {
        return provisioning;
    }

    public List<ClimaticCondition> getClimaticCondition() {
        return climaticCondition;
    }
    public void setClimaticCondition(List<ClimaticCondition> climaticCondition) {
    	this.climaticCondition.addAll(climaticCondition);
    }

    @Override
    public String toString() {
    	return city.getCityName() + " - " + modality.getDescription();
       }
}