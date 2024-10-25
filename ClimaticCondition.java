package model;

import java.io.Serializable;
import java.util.Random;

public class ClimaticCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	private float temperature=0;
	private float humidity=0;
	private float windSpeed=0;
	private UnitOfMeasurament unitOfMeasurament; // Association
	private float lowerBound, UpperBound, SwWear, CyWear, PdWear;
	private String descrption;
	private int idCli;	
	
	public ClimaticCondition(int idCli, UnitOfMeasurament unitOfMeasurament, float lowerBound, float UpperBound, float SwWear, float CyWear,  float PdWear) {
		this.idCli = idCli;
		this.lowerBound= lowerBound;
		this.UpperBound = UpperBound;
		this.SwWear = SwWear;
		this.CyWear = CyWear; 
		this.PdWear = PdWear;
		this.unitOfMeasurament = unitOfMeasurament;
		unitOfMeasurament.setUnitofMeas(unitOfMeasurament.getDescription());
		if(unitOfMeasurament.getDescription().equals("°C")) 
			setTemperature(lowerBound,UpperBound);
		else if(unitOfMeasurament.getDescription().equals("Km/h")) 
				setWindSpeed(lowerBound,UpperBound, unitOfMeasurament);
			 else if(unitOfMeasurament.getDescription().equals("HR")) 
				 	setHumidity(lowerBound,UpperBound);		
	}

	public void setTemperature(float lowerBound, float UpperBound) {
		Random r = new Random();
		this.temperature = (lowerBound +UpperBound)/(r.nextInt(2,5));
		setDescrption(unitOfMeasurament.setTempDescription(temperature));
	}

	public void setHumidity(float lowerBound, float UpperBound) {	
		Random r = new Random();
		this.humidity = (lowerBound +UpperBound)/(r.nextInt(2,5));
		setDescrption(unitOfMeasurament.setHumidityDescription(windSpeed));
	}

	public void setWindSpeed(float lowerBound, float UpperBound, UnitOfMeasurament unitOfMeasurament) {
		Random r = new Random();	
		this.windSpeed = (lowerBound + UpperBound)/(r.nextInt(2,5));
		setDescrption(unitOfMeasurament.setWindDescription(windSpeed));
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
	
	public int getIdCli() {		
		return this.idCli;
	}
	
}