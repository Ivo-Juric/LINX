package model;

import java.time.LocalDate;
import java.util.Random;

public class PhysicalCondition {

	private double swimmingFitness;
    private double cyclingFitness;
    private double pedestrianFitness;
    private double resistance;
    private double psychologicalStrength;
	private double speed;
	private double energy; 

    
    public PhysicalCondition (double swimmingFitness, double cyclingFitness, double pedestrianFitness, double resistance, double psychologicalStrength) {
  		this.swimmingFitness = swimmingFitness;
		this.cyclingFitness = cyclingFitness;
		this.pedestrianFitness = pedestrianFitness;
		this.resistance = resistance;
		this.psychologicalStrength = psychologicalStrength;
  	}

	public void boostEnergy(Food food, Drink drink) {
        if (food.getCantidad() > 0){
		}
		if (drink.getCantidad() > 0){
		}
    }

    public void energyWearClimaticCondition(ClimaticCondition climaticCondition) {
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

	public void setSwimmingFitness(double change) {
		 this.swimmingFitness += change;
	}

	public void setCyclingFitness(double change) {
		this.cyclingFitness += change;
	}

	public void setPedestrianFitness(double change) {
		this.pedestrianFitness += change;
	}

	public double getResistance() {
		return resistance;
	}

	public double getPsychologicalStrength() {
		return psychologicalStrength;
	}
	
	public double getSpeed() {
			return speed;
		}

	public void setSpeed(double speed) {
			this.speed = speed;
		}
	@SuppressWarnings("deprecation")
	public void setInitialSpeed(Athlete ath) {
		double var;
		Random r = new Random();
		int anio = LocalDate.now().getYear() + ath.getBirthdate().getYear() - 100;
		if (ath.getGender().equals("Femenino")) {
			var= r.nextDouble(4,8);
			
			if (anio > 19 && anio < 30) {
				var+= r.nextDouble(5,10);
			}else if(anio > 29 && anio < 45) {
				var+= r.nextDouble(5,7);
			}else{
				var+=5;
			}
			if (ath.getCategory().equals("Competición")) {
				var+= r.nextDouble(10,15);
			}else if (ath.getCategory().equals("Amateur")) {
				var+= r.nextDouble(8,12);
			}else {
				var+= 5;
			}
		}else {
			var= r.nextDouble(5,10);			
			if (anio > 19 && anio < 30) {
				var+= r.nextDouble(7,15);
			}else if(anio > 29 && anio < 45) {
				var+= r.nextDouble(5,10);
			}else {
				var+=2;
			}
			if (ath.getCategory().equals("Competición")) {
				var+= r.nextDouble(10,15);
			}else if (ath.getCategory().equals("Amateur")) {
				var+= r.nextDouble(10,13);
			}else {
				var+= 10;
			}			
		}
		this.speed = var;
		
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
}