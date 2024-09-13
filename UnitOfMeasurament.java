package model;

public class UnitOfMeasurament {

    private String description;

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UnitOfMeasurament (String description) {
        this.description = description;
    }

    public String getTempDescription(float temperature) {
        if (temperature > -1 && temperature < 6)
            this.description = "Very low temperature: ";
        else if (temperature > 5 && temperature < 23)
            this.description = "Normal temperature: ";
        else if (temperature > 22 && temperature < 35)
            this.description = "Warm temperature: ";
        else if (temperature > 34 && temperature < 51)
            this.description = "High temperature: ";
        
        this.description = this.description + temperature + "Cº";

        return this.description;
    }

    public String getWindDescription(float wind) {
        if (wind > -31 && wind < 0)
            this.description = "Headwind: ";
        else if (wind > -1 && wind < 31)
            this.description = "Tailwind: ";

        this.description = this.description + wind + "Km/h";
        
        return this.description;
    }

    
}