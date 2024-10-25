package model;

import java.io.Serializable;
import java.util.Random;

public class Provisioning implements Serializable {

	private static final long serialVersionUID = 1L;
	private float distanceKm;
	private Food food; // Composition
	private Drink drink; // Composition
	private String modality; // Composition
	private double centerCost;
	
	public Provisioning(float distanceKm, Food food, Drink drink, String modality) {
		this.distanceKm = distanceKm;
		this.food = food;
		this.drink = drink;
		this.setModality(modality);
		Random r = new Random();
		this.setCenterCost(r.nextInt(1000, 3000));
    }

	public float getDistanceKm() {
		return distanceKm;
	}

	public Food getFood() {
		return food;
	}

	public Drink getDrink() {
		return this.drink;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public double getCenterCost() {
		return centerCost;
	}

	public void setCenterCost(double centerCost) {
		this.centerCost = centerCost;
	}

}