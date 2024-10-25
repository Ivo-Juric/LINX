package events;

import model.Athlete;
import view.AthletePanel;

public interface MovementListener {
	void listenMovementEvent(Athlete athlete,AthletePanel athletePanel ,MovementEvent movementEvent);

}
