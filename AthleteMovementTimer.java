package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import events.MovementEvent;
import events.MovementListener;
import model.Athlete;

public class AthleteMovementTimer {
    private Timer timer;
    private List<MovementListener> listeners;
    private List<Athlete> athletes;

    public AthleteMovementTimer(List<MovementListener> listeners, List<Athlete> athletes) {
        this.listeners = listeners;
        this.athletes = athletes;
        timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyListeners();
                
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void notifyListeners() {
        for (Athlete athlete : athletes) {
            athlete.setMovement(); 
            for (MovementListener listener : listeners) {
                listener.listenMovementEvent(athlete, new MovementEvent((int) athlete.getPhysicalCondition().getSpeed()/10));
            }
        }
    }
}
