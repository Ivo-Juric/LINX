
public class UnitOfMeasurament {

	private String description;
	
    public UnitOfMeasurament(String description) {
    	this.description = description;
    }

    public String getTempDescrption(float temperature) {
        if (temperature > -1 && temperature < 6)
            this.description = "Temperatura muy baja: ";
        else if (temperature > 5 && temperature < 23)
                this.description = "Temperatura normal: ";
            else if (temperature > 22 && temperature < 35)
                this.description ="Temperatura cálida: ";
                else if (temperature > 34 && temperature < 51)
                        this.description ="Alta temperatura: ";
        this.description = this.description + temperature + "Cº";

        return this.description;
    }

    public String getWindDescription(float wind) {
         if (wind > -31 && wind < 0)
            this.description = "Viento en contra: ";
        else if (wind > -1 && wind < 31)
                this.description = "Viento a favor: ";

        this.description = this.description + wind + "Km/h";
        
        return this.description;
    }

    public String getDescription() {
		return description;
	}
}