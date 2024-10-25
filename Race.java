package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import view.AthletePanel;
import view.MenuRaces;

public class Race implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date date;
    private List<Athlete> athletes = new ArrayList<>(); // Composition
    private Modality modality; // Association
    private City city; // Association
    private List<Provisioning>  provisioning = new ArrayList<>(); // Composition
    private List<ClimaticCondition> climaticCondition = new ArrayList<>(); // Composition
    private AtomicInteger athletesFinished = new AtomicInteger(0); // Association
    private float totalDistance=0;; // Association
    
    // Flag to mark the finish for each discipline and race
    private boolean swFin = false; 
    private boolean CyFin = false;
    private boolean PdFin = false; 
	private boolean raceFinished = false; 

    public Race(Date date, Modality modality, City city, List<Provisioning> provisioning ) {
        this.date = date;
        this.modality = modality;
        this.city = city;
        this.provisioning.addAll(provisioning);
        setTotalDistance();
    }
    
    public void changeClimaticCondition(MenuRaces menu, AthletePanel athletePanel) {	
   	 Random random = new Random();
        int changeCli;      
   	 // Randomly change climatic conditions during the race
       changeCli = random.nextInt(1, 100000);
       if (changeCli < 50) {
           int r = random.nextInt(0, menu.getRaceView().getClimaticConditions().size()-1);
           athletePanel.setClimaticCondition(menu.getRaceView().getClimaticConditions().get(r));
           menu.getRaceView().setClimaticConditions(menu.getRaceView().getClimaticConditions().get(r).getDescrption());
       }

   }

    public Date getDate() {
        return date;
    }

    public List<Athlete> getAthlete() {
        return athletes;
    }
    
    public void setAthlete(List<Athlete>athlete) {
       athletes = athlete;
    }

    public Modality getModality() {
        return modality;
    }

    public City getCity() {
        return city;
    }

    public List<Provisioning> getProvisioning() {
        return provisioning;
    }

    public List<ClimaticCondition> getClimaticCondition() {
        return climaticCondition;
    }
    public void setClimaticCondition(List<ClimaticCondition> climaticCondition) {
    	this.climaticCondition.addAll(climaticCondition);
    }
    
    public Discipline getCurrentDiscipline(float distance) {
    	int i=0;
    	if (distance > 400 && distance < 650)
    		i = 1;
    	else if(distance > 650 && distance < 900)
    		i = 2;

    	return modality.getDistanceDisipline().get(i).getDiscipline();
    	
    }
    
    public boolean isSwFin() {
		return swFin;
	}

	public void setSwFin(boolean swFin) {
		this.swFin = swFin;
	}

	public boolean isCyFin() {
		return CyFin;
	}

	public void setCyFin(boolean cyFin) {
		CyFin = cyFin;
	}

	public boolean isPdFin() {
		return PdFin;
	}

	public void setPdFin(boolean pdFin) {
		PdFin = pdFin;
	}

	public boolean isRaceFinished() {
		return raceFinished;
	}

	public void setRaceFinished(boolean raceFinished) {
		this.raceFinished = raceFinished;
	}
	
	 // Method for regenerating energy at provisioning points
    public void energyRegenation(AthletePanel panel, Athlete athlete) {	
	        if (panel.getProvisioningPast() < panel.getProvisioningPosition().size() && panel.getPlacement() > panel.getProvisioningPosition().get(panel.getProvisioningPast())) {
	            if (athlete.getEconomicBudget() - getProvisioning().get(panel.getProvisioningPast()).getCenterCost() > 0) {
	                
	                athlete.getPhysicalCondition().boostEnergy(getProvisioning().get(panel.getProvisioningPast()).getFood(),getProvisioning().get(panel.getProvisioningPast()).getDrink());
	               
	                // Deduct the provisioning cost from the athlete's budget
	                athlete.setEconomicBudget(-(getProvisioning().get(panel.getProvisioningPast()).getCenterCost()));
	            }
	            panel.setProvisioningPast(1);
	        }
    	
    }

    @Override
    public String toString() {
    	return city.getCityName() + " - " + modality.getDescription();
       }

	public AtomicInteger getAthletesFinished() {
		return athletesFinished;
	}

	public void setAthletesFinished(AtomicInteger athletesFinished) {
		this.athletesFinished = athletesFinished;
	}

	public float getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance() {
		for (int i = 0; i< modality.getDistanceDisipline().size(); i++)
			totalDistance+= modality.getDistanceDisipline().get(i).getDistance();
	}
}