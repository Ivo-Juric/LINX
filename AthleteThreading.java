package controller;

import java.io.IOException;

import javax.swing.SwingUtilities;

import events.MovementEvent;
import events.MovementListener;
import model.Athlete;
import view.AthletePanel;
import view.CronometerView;

public class AthleteThreading extends Thread {
	private Athlete athelte;
    private AthletePanel athletePanel;
    private MovementListener listener;
    private CronometerView cronometer;

    public AthleteThreading(Athlete athelte, AthletePanel athletePanel, MovementListener listener, CronometerView cronometer) throws IOException{
    	this.athelte = athelte;	
        this.athletePanel = athletePanel;
        this.listener = listener;
        this.cronometer = cronometer;
    }
    
    
    public AthletePanel getAthletePanel() {
		return athletePanel;
	}
	final Runnable doMovement = new Runnable() {
        public void run() {
      	  if (athletePanel.getPlacement() < 900) {
      		listener.listenMovementEvent(athelte, athletePanel, new MovementEvent((int) athelte.getPhysicalCondition().getSpeed()));   
      	  }else
      		athletePanel.setFinished(true); 
        }
      };
      
      public void run() {
    	  	
			while (!Thread.currentThread().isInterrupted() && !athletePanel.isFinished() ) {
				try {
					SwingUtilities.invokeLater(doMovement);	
					cronometer.setTime(2400000, 800000);
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			athletePanel.setFinished(true); 
			listener.listenMovementEvent(athelte, athletePanel, new MovementEvent((int) athelte.getPhysicalCondition().getSpeed())); 
			
      }
}
