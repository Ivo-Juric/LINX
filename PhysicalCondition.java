import java.util.*;

public class PhysicalCondition {

    private float speed;
    private float endurance;
    
    public PhysicalCondition (float speed, float endurance) {
  		this.speed = speed;
  		this.endurance = endurance;
  	}

	public float boostEnergy(Food food) {
        return 0.0f;
    }

    public float energyWear(float speed, ClimaticCondition climaticCondition) {
        return 0.0f;
    }

	public float getSpeed() {
		return speed;
	}

	public float getEndurance() {
		return endurance;
	}
}