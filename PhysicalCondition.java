import java.util.*;

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

	public float boostEnergy(Food food) {
        return 0.0f;
    }

    public float energyWear(float speed, ClimaticCondition climaticCondition) {
        return 0.0f;
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

	public double getResistance() {
		return resistance;
	}

	public double getPsychologicalStrength() {
		return psychologicalStrength;
	}
}