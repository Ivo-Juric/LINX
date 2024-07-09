
import java.util.*;

public class Race {

	private Date date;
	private Athlete athlete; // Agregacion
	private Modality modality; // Asociacion
	private City city; // Asociacion
	private Provisioning provisioning; //Composición
	private ClimaticCondition climaticCondition; //Composición
	
    public Race(Date date, Modality modality, City city, Provisioning provisioning, ClimaticCondition climaticCondition) {
    	this.date = date;
    	this.modality = modality;
    	this.city = city;
    	provisioning = new Provisioning (provisioning.getDistanceKm(), provisioning.getFood(), provisioning.getDrink());
    	climaticCondition = new ClimaticCondition (climaticCondition.getTemperature(), climaticCondition.getHumidity(), climaticCondition.getWindSpeed(), climaticCondition.getUnitOfMeasurament());
    }
}