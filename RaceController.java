package controller;

import java.util.ArrayList;
import java.util.List;

import Serializable.SaveGame;
import events.MovementEvent;
import events.MovementListener;
import model.Athlete;
import model.Championship;
import model.Discipline;
import model.Race;
import view.AthletePanel;
import view.MenuRaces;

public class RaceController implements MovementListener {
    
    private List<Athlete> athletes = new ArrayList<>();
    private MenuRaces menu;
    private Championship championship;
    
    // Constructor initializes athletes and sets up the race menu
    public RaceController(List<Athlete> athletes, List<Race> races, Championship championship) {
        for (Athlete at : athletes) {
            at.getPhysicalCondition().setSpeed(0, true, 150);
            this.athletes.add(at);
        }
        this.menu = new MenuRaces(this.athletes, races, this, championship);
        this.setChampionship(championship);
    }

    // Event listener for movement events
    @Override
    public void listenMovementEvent(Athlete athlete, AthletePanel athletePanel, MovementEvent event) {  
        // Process movement only if the athlete hasn't finished the race
    	Race currentRace = menu.getRaceView().getCurrentRace();
        if (athletePanel != null &&  menu.getRaceView().getCurrentRace().getAthletesFinished().get() < athletes.size()) {
            if (!athletePanel.isFinished()) {
                if (athletePanel.getPlacement() <= 900) {
                	
                	Discipline currentDiscipline = currentRace.getCurrentDiscipline(athletePanel.getPlacement());
                	
                	// Set discipline completion flags
                    if (!currentDiscipline.hasFinished(currentRace)) {
                        currentDiscipline.completePrevDiscipline(athlete, currentRace);
                    }
                   
                    // If the race is "Long distance" set a time limit for finishing
                    if (currentRace.getModality().getDescription().equals("Larga distancia")) {
                        if ( (athlete.getTournamentData().getCyclingTime() == null || athlete.getTournamentData().getCyclingTime().equals("")) && menu.getCrono().timeToMilliseconds( menu.getCrono().getTimerLabel().getText()) > currentDiscipline.getLimitTime()) {
                            athletePanel.setFinished(true);      
                        }
                    }
                    // Handle time limits for the first discipline (swimming) and mark as finished if time limit is reached
                    if ( athlete.getTournamentData().getSwimTime() == null || athlete.getTournamentData().getSwimTime().equals("")) {
                    	if (menu.getCrono().timeToMilliseconds(menu.getCrono().getTimerLabel().getText()) >  currentDiscipline.getLimitTime())
                            athletePanel.setFinished(true);  
                    	
                        if (currentDiscipline.prohibition(athlete)) {
                        	athlete.getTournamentData().setFinalUDistance(0);
                        	athletePanel.setFinished(true); 
                        	}
                    }
                    
                    if (athlete.getPhysicalCondition().getEnergy() <= 0) 
                    	athletePanel.setFinished(true);  
                	
                    if (!athletePanel.isFinished()) {
                    		currentRace.energyRegenation(athletePanel, athlete); 
		                    athlete.getPhysicalCondition().setSpeed(athletePanel.getPlacement(), false, athletePanel.getPlacement() / 900); 
		                    athletePanel.movePanel((int) (event.getSpeed() / 5));
		                    athlete.getPhysicalCondition().setEnergy(currentDiscipline.getWear(athletePanel.getPlacement(), athlete));                   
		                    athlete.getPhysicalCondition().energyWearClimaticCondition(athletePanel.getClimaticCondition());
		                    athlete.getPhysicalCondition().changePhysicalCondition();
		                    
		                   athlete.getTournamentData().setTimePerDiscipline(athletePanel.getPlacement(), menu.getCrono().getTimerLabel().getText(), false);
		                    
		                   currentRace.changeClimaticCondition(menu, athletePanel);
                    
                    }  	
                             
                }               	
            } else { 
            	// If the athlete has finished or retired the race
            	boolean finished = false;
            	currentRace.getAthletesFinished().incrementAndGet();
                if (athletePanel.getPlacement() >= 900)  	
                    athlete.setFinishedRaces(1);
                 else {
                    finished = true;
                    athlete.getTournamentData().setAbandonedRaces(1);
                    }
                if (athletePanel.getPlacement() > 155)
                	athlete.getTournamentData().setFinalUDistance(athletePanel.getPlacement());
                else
                	athlete.getTournamentData().setFinalUDistance(0);
                	
                athletePanel.movePanel(0);
                athlete.getTournamentData().setTimePerDiscipline(athletePanel.getPlacement(), menu.getCrono().getTimerLabel().getText(), finished);
            }
        }

        // If all athletes have finished, finalize the race
        synchronized (this) {
        	if (currentRace.getAthletesFinished().get() == athletes.size() && !menu.getRaceView().getCurrentRace().isRaceFinished()) {   	
        		currentRace.setRaceFinished(true);
	            menu.getRaceView().addRaceToChamp(); 
	            menu.getRaceView().stopRace();
	            SaveGame.saveGame(menu.getRaceView().getChamp(), "settings.ser");
	            menu.championshipView(menu.getRaceView().getChamp());
        	}
        }
    }

    // Getters and setters
    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
    
    public MenuRaces getMenu() {
        return menu;
    }

	public List<Athlete> getAthletes() {
		return athletes;
	}

}
