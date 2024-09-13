package controller;

import java.util.List;

import events.MovementEvent;
import events.MovementListener;
import model.Athlete;
import model.Race;
import view.AthletePanel;
import view.MenuRaces;
import view.RaceView;

public class RaceController implements MovementListener{
    private List<Athlete> athletes;
    private MenuRaces menu;

	public MenuRaces getMenu() {
		return menu;
	}

	public RaceController(List<Athlete> athletes, List<Race> race) {
        this.athletes = athletes;
        this.menu = new MenuRaces(athletes, race, this);
    }

	@Override
	public void listenMovementEvent(Athlete athlete, MovementEvent event) {
        int athleteIndex = athletes.indexOf(athlete);
        if (athleteIndex != -1) {
            AthletePanel panel = menu.getRaces().getAthletePanel(athleteIndex);
            if (panel != null) {
                panel.movePanel(event.getSpeed());
            }
        }
    }
}
