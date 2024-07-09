
import java.util.*;

public class ClimaticCondition {
	private float temperature;
	private float humidity;
	private float windSpeed;
	private UnitOfMeasurament unitOfMeasurament; // Asociacion
	
	public ClimaticCondition(float temperature, float humidity, float windSpeed, UnitOfMeasurament unitOfMeasurament) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.unitOfMeasurament = unitOfMeasurament;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public UnitOfMeasurament getUnitOfMeasurament() {
		return unitOfMeasurament;
	}

}