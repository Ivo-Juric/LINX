package events;

public class MovementEvent{
	private int speed;
	public MovementEvent(int speed) {
		this.speed=speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int i) {
		this.speed=i;
		
	}

}
