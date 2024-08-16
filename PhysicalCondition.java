
public class PhysicalCondition {

	private double swimmingFitness;
    private double cyclingFitness;
    private double pedestrianFitness;
    private double resistance;
    private double psychologicalStrength;
    
    public PhysicalCondition (double swimmingFitness, double cyclingFitness, double pedestrianFitness, double resistance, double psychologicalStrength) {
  		this.swimmingFitness = swimmingFitness;
		this.cyclingFitness = cyclingFitness;
		this.pedestrianFitness = pedestrianFitness;
		this.resistance = resistance;
		this.psychologicalStrength = psychologicalStrength;
  	}

	public void boostEnergy(Food food, Drink drink) {
        if (food){
			this.cyclingFitness += food.boost();
			this.resistance+= food.boost();
			this.pedestrianFitness += food.boost();
		}
		if (drink){
			this.swimmingFitness += drink.boost();
			this.resistance+= drink.boost();
			this.psychologicalStrength += drink.boost();
		}
    }

    public void energyWear(float speed, ClimaticCondition climaticCondition) {
        if (climaticCondition.getTemperature() < 6){
			setSwimmingFitness(-15/100 *getSwimmingFitness());
			setPedestrianFitness(-8/100 * getPedestrianFitness());
			setCyclingFitness(-2/100 * getCyclingFitness());
		} else if (climaticCondition.getTemperature() > 5 && climaticCondition.getTemperature() < 23)
					setPedestrianFitness(-1/100 * getPedestrianFitness());
				else if (climaticCondition.getTemperature() > 22 && climaticCondition.getTemperature() < 35){
						setPedestrianFitness(-10/100 * getPedestrianFitness());
						setCyclingFitness(-15/100 * getCyclingFitness());
				} else if (climaticCondition.getTemperature() > 34 && climaticCondition.getTemperature() < 51){
						setPedestrianFitness(-20/100 * getPedestrianFitness());
						setCyclingFitness(-15/100 * getCyclingFitness());
				}

		if (climaticCondition.getWindSpeed() < 0){
			setSwimmingFitness(-1/100 *getSwimmingFitness());
			setCyclingFitness(-30/100 * getCyclingFitness());
			setPedestrianFitness(-4/100 * getPedestrianFitness());
		} else if (climaticCondition.getWindSpeed() > -1){
			setSwimmingFitness(1/100 *getSwimmingFitness());
			setCyclingFitness(35/100 * getCyclingFitness());
			setPedestrianFitness(5/100 * getPedestrianFitness());
			}
    }

	public double getSwimmingFitness() {
		return swimmingFitness;
	}

	public double getCyclingFitness() {
		return cyclingFitness;
	}

	public double getPedestrianFitness() {
		return pedestrianFitness;
	}

	public void setSwimmingFitness(float change) {
		 this.swimmingFitness += change;
	}

	public void setCyclingFitness(float change) {
		this.cyclingFitness += change;
	}

	public void setPedestrianFitness(float change) {
		this.pedestrianFitness += change;
	}

	public double getResistance() {
		return resistance;
	}

	public double getPsychologicalStrength() {
		return psychologicalStrength;
	}
}