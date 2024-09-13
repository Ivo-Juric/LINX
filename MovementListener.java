package events;

import model.Athlete;

public interface MovementListener {
	void listenMovementEvent(Athlete athlete, MovementEvent movementEvent);

}
