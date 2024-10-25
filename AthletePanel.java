package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.Athlete;
import model.ClimaticCondition;
import model.Race;

@SuppressWarnings("serial")
public class AthletePanel extends JPanel {

    // Panel components
    private JButton increaseSpeedButton, decreaseSpeedButton;
    private JLabel athleteIcon;
    private JTextField energyDisplay;
    private DecimalFormat energyFormat;

    // Logic attributes
    private Athlete athlete;
    private Race race;
    private ClimaticCondition climaticCondition;
    private List<Integer> provisioningPositions;
    private boolean finished;
    private int provisioningPast = 0;

    // Constructor
    public AthletePanel(Athlete athlete, int yPosition, Race race, ClimaticCondition climaticCondition) {
        this.athlete = athlete;
        this.race = race;
        this.climaticCondition = climaticCondition;
        this.provisioningPositions = new ArrayList<>();
        this.finished = false;
        this.energyFormat = new DecimalFormat("#.0");

        // Panel setup
        initializePanel(yPosition);
        initializeComponents();
        calculateProvisioningPositions();
    }

    // Initialize panel settings
    private void initializePanel(int yPosition) {
        setLayout(null);
        setBounds(10, yPosition, 1000, 100);
        setBackground(new Color(230, 230, 250));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                athlete.getName() + " " + athlete.getSurname(),
                javax.swing.border.TitledBorder.LEADING,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.BLUE
        ));
    }

    // Initialize UI components
    private void initializeComponents() {
        // Energy display
        energyDisplay = new JTextField();
        energyDisplay.setEditable(false);
        energyDisplay.setBounds(10, 67, 100, 25);
        energyDisplay.setBackground(UIManager.getColor("Button.background"));
        energyDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add(energyDisplay);

        // Speed control buttons
        initializeSpeedButtons();

        // Athlete icon
        athleteIcon = new JLabel(new ImageIcon("C:\\Users\\Ivo\\eclipse-workspace\\LINX\\Athlete_Image.jpg"));
        athleteIcon.setBounds(150, 40, 30, 30);
        add(athleteIcon);
    }

    // Initialize buttons to increase/decrease speed
    private void initializeSpeedButtons() {
        increaseSpeedButton = new JButton("+");
        decreaseSpeedButton = new JButton("-");

        increaseSpeedButton.setBounds(10, 30, 48, 25);
        decreaseSpeedButton.setBounds(60, 30, 48, 25);
        increaseSpeedButton.setFont(new Font("Arial", Font.BOLD, 14));
        decreaseSpeedButton.setFont(new Font("Arial", Font.BOLD, 14));
        increaseSpeedButton.setBackground(Color.GREEN);
        decreaseSpeedButton.setBackground(Color.RED);

        add(increaseSpeedButton);
        add(decreaseSpeedButton);

        // Action listener for increasing speed
        increaseSpeedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (athlete.getPhysicalCondition().getSpeed() + 5 <= 30) {            		
            		athlete.getPhysicalCondition().setButtomSpeed(athleteIcon.getX(), 3);
            	} else
            		increaseSpeedButton.setEnabled(false);
            	
            }
        });

        // Action listener for decreasing speed
        decreaseSpeedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (athlete.getPhysicalCondition().getSpeed() - 5 >= 0) {
                    athlete.getPhysicalCondition().setButtomSpeed(athleteIcon.getX(), -3);
                } else
                	decreaseSpeedButton.setEnabled(false);
            }
        });
    }

    // Calculate the provisioning positions for the race
    private void calculateProvisioningPositions() {
        int sum = 0;
        int x = 400; // Starting position for cycling
        for (int i = 0; i < race.getProvisioning().size(); i++) {
            if (race.getProvisioning().get(i).getModality().equals("ciclismo")) {
                sum = x + getProvisioningPositionForCycling(i);
               
            } else {
                x = 650; // Starting position for running
                sum = x + getProvisioningPositionForRunning(i);
            }
            
            provisioningPositions.add(sum);
        }
    }

    // Calculate provisioning position for cycling
    public int getProvisioningPositionForCycling(int index) {
        return (int) (race.getProvisioning().get(index).getDistanceKm() * 250 /
                      race.getModality().getDistanceDisipline().get(1).getDistance());
    }

    // Calculate provisioning position for running
    public int getProvisioningPositionForRunning(int index) {
        return (int) (race.getProvisioning().get(index).getDistanceKm() * 250 /
                      race.getModality().getDistanceDisipline().get(2).getDistance());
    }

    // Paint the race track and provisioning lines
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, this.getHeight()); // Cycling line
        g.drawLine(650, 0, 650, this.getHeight()); // Running line
        g.drawLine(900, 0, 900, this.getHeight()); // Finish line

        g.setColor(Color.RED);
        int x = 400;
        int sum = 0;
        for (int i = 0; i < race.getProvisioning().size(); i++) {
            if (race.getProvisioning().get(i).getModality().equals("ciclismo")) {
                sum = x + getProvisioningPositionForCycling(i);
                g.drawLine(sum, 0, sum, this.getHeight()); // Cycling provisioning line
            } else {
                x = 650;
                sum = x + getProvisioningPositionForRunning(i);
                g.drawLine(sum, 0, sum, this.getHeight()); // Running provisioning line
            } 
        }
    }

    // Move the athlete's panel (icon) based on speed and weather conditions
    public void movePanel(int dx) {
        int currentWear;
        if (dx > 0 || athleteIcon.getX() == 150) {
        	increaseSpeedButton.setEnabled(true);
        	decreaseSpeedButton.setEnabled(true);
	        if (athleteIcon.getX() > 650 && athleteIcon.getX() < 900) {
	            currentWear = (int) climaticCondition.getCyWear();
	        } else if (athleteIcon.getX() > 900) {
	            currentWear = (int) climaticCondition.getPdWear();
	        } else {
	            currentWear = (int) climaticCondition.getSwWear();
	        }
	
	        if (athleteIcon.getX() < 900) {
	            athleteIcon.setLocation(
	                (int) (athleteIcon.getX() + dx - dx * currentWear / 100),
	                athleteIcon.getY()
	            );
	        } else {
	            athleteIcon.setLocation(930, athleteIcon.getY());
	            
	        }	        
	        energyDisplay.setText("Energy: " + energyFormat.format(athlete.getPhysicalCondition().getEnergy()));
	        
        } else {
        	increaseSpeedButton.setEnabled(false);
        	energyDisplay.setText("Energy: " + energyFormat.format(athlete.getPhysicalCondition().getEnergy()));
        	decreaseSpeedButton.setEnabled(false);
        	this.setFinished(true);
        	}
       
    }

    // Check if the athlete has finished the race
    public boolean isFinished() {
        return finished;
    }

    // Set the athlete's finished state
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // Get the athlete's current position on the track
    public int getPlacement() {
        return athleteIcon.getX();
    }

    // Get and set provisioning passed count
    public int getProvisioningPast() {
        return provisioningPast;
    }

    public void setProvisioningPast(int provisioningPast) {
        this.provisioningPast += provisioningPast;
    }
    
	public ClimaticCondition getClimaticCondition() {
		return climaticCondition;
	}

	public void setClimaticCondition(ClimaticCondition climaticCondition) {
		this.climaticCondition = climaticCondition;
	}
	
	public List<Integer> getProvisioningPosition() {
		return provisioningPositions;
	}


}

