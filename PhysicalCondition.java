package model;

import java.io.Serializable;

public class PhysicalCondition implements Serializable {
	private static final long serialVersionUID = 1L;
	private double swimmingFitness;
    private double cyclingFitness;
    private double pedestrianFitness;
    private double resistance;
    private double psychologicalStrength;
	private double speed;
	private double energy;
	private int discipline=0;

    
    public PhysicalCondition (double swimmingFitness, double cyclingFitness, double pedestrianFitness, double resistance, double psychologicalStrength) {
  		this.swimmingFitness = swimmingFitness;
		this.cyclingFitness = cyclingFitness;
		this.pedestrianFitness = pedestrianFitness;
		this.resistance = resistance;
		this.psychologicalStrength = psychologicalStrength;
		this.energy= getSwimmingFitness()*getCyclingFitness()*getPedestrianFitness()*getResistance()*getPsychologicalStrength()/10;
		
  	}

	public void boostEnergy(Food food, Drink drink) {
        if (food.getCantidad() > 0){
        	this.energy +=  food.boost();
		}
		if (drink.getCantidad() > 0){
			this.energy +=  drink.boost();
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

	public void setButtomSpeed(int pos, int btmType) {
			if (pos < 400) {
				this.speed += 0.5 *btmType ;
			}else if (pos > 400 && pos < 650){
				this.speed += 1.5 * btmType;
			}else {
				this.speed += btmType;
			}
	}
	public void setSpeed(int pos, boolean newRace, float distance) {
		if (pos < 400 && discipline == 0 || newRace) {
			this.speed =  (getSwimmingFitness()/100)/ distance*0.5;
			discipline=1;
			newRace = false;
			this.speed = (speed+getResistance() + getPsychologicalStrength());
		}else if (pos > 400 && pos < 650 && discipline == 1){
			this.speed =  speed*getCyclingFitness()/50 ;
			discipline=2;
			this.speed = (speed+getResistance() + getPsychologicalStrength());
		}else if (pos > 650 && discipline == 2){
			this.speed = speed*getPedestrianFitness()/75 ;
			this.speed = (speed+getResistance() + getPsychologicalStrength());
			discipline=3;
		}
		
			
	}
	public void setResistance(double change) {
		this.resistance +=change;
	}
	
	public void changePhysicalCondition() {
		setSwimmingFitness(-0.0001);	
		setPedestrianFitness(-0.0001);
		setCyclingFitness(-0.0001);
		setResistance(-0.0001);
		setPsychologicalStrength(-0.0001);
	}
	
	

	public void setPsychologicalStrength(double d) {
		this.psychologicalStrength +=d;
		
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(float changeByDiscipline) {
		double energywear = energy*speed/(10000);
		if (energywear > 0 && energy-(energywear + changeByDiscipline) > 0)
			this.energy -= (energywear + changeByDiscipline);
		else {
				this.energy = 0;
				this.speed = 0;
		}
	}

	public void setIEnergy(int InEnergy) {
		this.energy = InEnergy;
	}
}