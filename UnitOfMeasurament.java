package model;

import java.io.Serializable;

public class UnitOfMeasurament implements Serializable{

    private static final long serialVersionUID = 1L;
	private String description;
    private String UnitofMeas;
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UnitOfMeasurament (String description) {
        this.description = description;
    }

    public String setTempDescription(float temperature) {
        if (temperature > -1 && temperature < 6)
            this.description = "Very low temperature: ";
        else if (temperature > 5 && temperature < 23)
            this.description = "Normal temperature: ";
        else if (temperature > 22 && temperature < 35)
            this.description = "Warm temperature: ";
        else if (temperature > 34 && temperature < 51)
            this.description = "High temperature: ";
        
        this.description = this.description + " " +  String.format("%.2f", temperature) + " Cº";

        return this.description;
    }

    public String setWindDescription(float wind) {
        if (wind > -31 && wind < 0)
            this.description = "Headwind: ";
        else if (wind > -1 && wind < 31)
            this.description = "Tailwind: ";

        this.description = this.description + " " + String.format("%.2f", wind) + " Km/h";
        
        return this.description;
    }
    
    public String setHumidityDescription(float humidity) {
        if (humidity > -1 && humidity < 34)
            this.description = "Low humidity: ";
        else if (humidity > 33 && humidity < 67)
            this.description = "Normal humidity: ";
        else if (humidity > 66 && humidity < 101)
            this.description = "High humidity: ";
        
        this.description = this.description + " " + String.format("%.2f", humidity) + " HR";

        return this.description;
    }

	public String getUnitofMeas() {
		return UnitofMeas;
	}

	public void setUnitofMeas(String unitofMeas) {
		UnitofMeas = unitofMeas;
	}


    
}