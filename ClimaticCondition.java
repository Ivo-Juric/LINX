package model;
public class ClimaticCondition {
	private float temperature;
	private float humidity;
	private float windSpeed;
	private UnitOfMeasurament unitOfMeasurament; // Association
	private float lowerBound, UpperBound, SwWear, CyWear, PdWear;
	private String descrption;
	private int idCli;
	
	public ClimaticCondition(int idCli, String description, UnitOfMeasurament unitOfMeasurament, float lowerBound, float UpperBound, float SwWear, float CyWear,  float PdWear) {
		this.idCli = idCli;
		this.descrption = description;
		this.lowerBound= lowerBound;
		this.UpperBound = UpperBound;
		this.SwWear = SwWear;
		this.CyWear = CyWear; 
		this.PdWear = PdWear;
		this.unitOfMeasurament = unitOfMeasurament;
	}

	public float getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(float lowerBound) {
		this.lowerBound = lowerBound;
	}

	public float getUpperBound() {
		return UpperBound;
	}

	public void setUpperBound(float upperBound) {
		UpperBound = upperBound;
	}

	public float getSwWear() {
		return SwWear;
	}

	public void setSwWear(float swWear) {
		SwWear = swWear;
	}

	public float getCyWear() {
		return CyWear;
	}

	public void setCyWear(float cyWear) {
		CyWear = cyWear;
	}

	public float getPdWear() {
		return PdWear;
	}

	public void setPdWear(float pdWear) {
		PdWear = pdWear;
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

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public void setUnitOfMeasurament(UnitOfMeasurament unitOfMeasurament) {
		this.unitOfMeasurament = unitOfMeasurament;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public int getIdCli() {		
		return this.idCli;
	}
	
}