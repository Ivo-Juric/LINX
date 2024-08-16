
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

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	
}