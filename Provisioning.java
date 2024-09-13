package model;

public class Provisioning {

	private float distanceKm;
	private Food food; // Composition
	private Drink drink; // Composition
	private String modality; // Composition
	
	public Provisioning(float distanceKm, Food food, Drink drink, String modality) {
		this.distanceKm = distanceKm;
		this.food = food;
		this.drink = drink;
		this.setModality(modality);
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

}