
public class Provisioning {

	private float distanceKm;
	private Food food; //Composición
	private Drink drink; //Composición
	
	public Provisioning(float distanceKm, Food food, Drink drink) {
		this.distanceKm = distanceKm;
		food = new Food();
		drink = new Drink();
    }

	public float getDistanceKm() {
		return distanceKm;
	}

	public Food getFood() {
		return food;
	}

	public Drink getDrink() {
		return drink;
	}

}