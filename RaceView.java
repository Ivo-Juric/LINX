package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Serializable.LoadGame;
import Serializable.SaveGame;
import controller.AthleteThreading;
import controller.RaceController;
import dao.ClimaticConditionDAO;
import events.MovementListener;
import model.Athlete;
import model.Championship;
import model.ClimaticCondition;
import model.Race;

public class RaceView extends JFrame {

    private static final long serialVersionUID = 1L;

    // Attributes
    private JPanel principalPanel;
	private JTextPane climaticPanel;
	private JButton startNewRace = new JButton("New Race");
    private JButton saveAndClose = new JButton("Go Menu");
	private CronometerView crono;
    private ChampionshipFrame championFrame;
    private Championship championship;
    private int yP = 150;
    private MovementListener movementListener;
    private List<Athlete> athletes;
    private Clip clip;
    private List<ClimaticCondition> ClimaticConditions;
    private List<AthleteThreading> thread = new ArrayList<>();
    private List<Race> races;
    private int raceChoosen;
    

    // Constructor for initializing the race view
    public RaceView(List<Athlete> athletes, List<Race> race, MovementListener movementListener, CronometerView cronometerView, Championship championship) throws SQLException {

        playMusic("C:\\Users\\Ivo\\eclipse-workspace\\LINX\\Race_Music.wav");
        this.crono = cronometerView;
        this.races = race;
        this.championship = championship;
        Random ra = new Random();
        raceChoosen = ra.nextInt(0, races.size());
        this.setAthletes(athletes);
        this.movementListener = movementListener;
        this.ClimaticConditions = ClimaticConditionDAO.getAllClimaticConditions();
        races.get(raceChoosen).setClimaticCondition(this.ClimaticConditions);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 600);
        setTitle("Race");
        setLayout(new BorderLayout());
        setBackground(new Color(40, 44, 52));

        // Create main panel
        principalPanel = new JPanel();
        principalPanel.setBackground(new Color(60, 63, 65));
        principalPanel.setLayout(null);
        principalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create race name panel
        JTextPane raceName = new JTextPane();
        raceName.setEditable(false);
        raceName.setText(races.get(raceChoosen).toString());
        raceName.setFont(new Font("Arial", Font.BOLD, 16));
        raceName.setForeground(Color.WHITE);
        raceName.setBackground(new Color(255, 87, 34));
        raceName.setBounds(30, 20, 300, 40);
        raceName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerText(raceName);
        principalPanel.add(raceName);

        // Create climatic condition panel
        Random r = new Random();
        int randomClima = r.nextInt(0, ClimaticConditions.size()-1);
        climaticPanel = new JTextPane();
        climaticPanel.setEditable(false);
        climaticPanel.setText(ClimaticConditions.get(randomClima).getDescrption());
        climaticPanel.setFont(new Font("Arial", Font.PLAIN, 16));
        climaticPanel.setForeground(Color.WHITE);
        climaticPanel.setBackground(new Color(33, 150, 243));
        climaticPanel.setBounds(350, 20, 250, 40);
        climaticPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerText(climaticPanel);
        principalPanel.add(climaticPanel);

        createRaceStagePanels(principalPanel);
        setupSaveAndCloseButton();
        setupStartNewRaceButton();
        addAthletesToRacePanel(randomClima);
        setupScrollPane();
        centerOnScreen();
    }

    // Adds athletes to the race panel and starts their respective threads
    private void addAthletesToRacePanel(int randomClima) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (Athlete at : athletes) {
                        AthletePanel athletePane = new AthletePanel(at, yP, races.get(raceChoosen), ClimaticConditions.get(randomClima));
                        thread.add(new AthleteThreading(at, athletePane, movementListener, crono));
                        principalPanel.add(athletePane);
                        yP += 100;
                    }
                    for (AthleteThreading threads : thread) {
                        threads.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Centers the text inside a JTextPane component
    private void centerText(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), attrs, false);
        textPane.setParagraphAttributes(attrs, true);
    }

    // Centers the frame on the screen
    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
    }

    // Plays background music from a file path
    public void playMusic(String musicPath) {
        try {
            File musicFile = new File(musicPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates the panels for use neoprene, swimming, cycling, and pedestriasm stages
    private void createRaceStagePanels(JPanel panel) {

    	JTextPane swimmingPanel = new JTextPane();
        swimmingPanel.setEditable(false);
        swimmingPanel.setText("Swimming");
        swimmingPanel.setFont(new Font("Arial", Font.BOLD, 16));
        swimmingPanel.setForeground(Color.WHITE);
        swimmingPanel.setBackground(new Color(255, 87, 34));
        swimmingPanel.setBounds(160, 70, 250, 40);
        swimmingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerText(swimmingPanel);
        panel.add(swimmingPanel);

        JTextPane cyclingPanel = new JTextPane();
        cyclingPanel.setEditable(false);
        cyclingPanel.setText("Cycling");
        cyclingPanel.setFont(new Font("Arial", Font.BOLD, 16));
        cyclingPanel.setForeground(Color.WHITE);
        cyclingPanel.setBackground(new Color(255, 87, 34));
        cyclingPanel.setBounds(410, 70, 250, 40);
        cyclingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerText(cyclingPanel);
        panel.add(cyclingPanel);

        JTextPane runningPanel = new JTextPane();
        runningPanel.setEditable(false);
        runningPanel.setText("Pedestriasm");
        runningPanel.setFont(new Font("Arial", Font.BOLD, 16));
        runningPanel.setForeground(Color.WHITE);
        runningPanel.setBackground(new Color(255, 87, 34));
        runningPanel.setBounds(660, 70, 250, 40);
        runningPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerText(runningPanel);
        panel.add(runningPanel);
    }

    // Sets up the Save and Close button with its action listener
    private void setupSaveAndCloseButton() {
        saveAndClose.setBounds(880, 20, 150, 40);
        saveAndClose.setFont(new Font("Arial", Font.BOLD, 16));
        saveAndClose.setBackground(new Color(76, 175, 80));
        saveAndClose.setForeground(Color.WHITE);
        saveAndClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveGame.saveGame(championship, "settings.ser");
                setVisible(false);
                crono.setVisible(false);
                clip.stop();
                RaceController newRaceController = new RaceController(getAthletes(), races, LoadGame.loadUserSettings("settings.ser"));
                newRaceController.getMenu().setVisible(true);
            }
        });
        principalPanel.add(saveAndClose);
    }

    // Sets up the Start New Race button with its action listener
    private void setupStartNewRaceButton() {
    	startNewRace.setBounds(700, 20, 150, 40);
        startNewRace.setFont(new Font("Arial", Font.BOLD, 16));
        startNewRace.setBackground(new Color(244, 67, 54));
        startNewRace.setForeground(Color.WHITE);
        startNewRace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	clip.stop();
                	SaveGame.saveGame(getChamp(), "settings.ser");
                	RaceController newRaceController = new RaceController(getAthletes(), races, LoadGame.loadUserSettings("settings.ser"));
                	newRaceController.getMenu().raceCreation(getAthletes(), races, newRaceController);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                crono.setVisible(false);
                setVisible(false);
            }
        });
        startNewRace.setEnabled(false);
        saveAndClose.setEnabled(false);
        principalPanel.add(startNewRace);
    }

    // Scroll pane setup for better visibility of race content
    private void setupScrollPane() {
    	principalPanel.setPreferredSize(new Dimension(1080, 4500));  
        JScrollPane scrollPane = new JScrollPane(principalPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPane.getViewport().setBackground(new Color(60, 63, 65));
        setContentPane(scrollPane);
        centerOnScreen();
    }
    
    private void AthletesSorter() {
    	athletes.sort((o1,o2) -> (int) (o2.getTournamentData().getFinalUDistance() - o1.getTournamentData().getFinalUDistance()));
    	athletes.sort((o1, o2) -> {
    	        String runTime1 = o1.getTournamentData().getRunningTime();
    	        String runTime2 = o2.getTournamentData().getRunningTime();
    	        
    	        if (runTime1 == null || runTime1.equals("")) {
    	        	String cycleTime1 = o1.getTournamentData().getCyclingTime();
        	        String cycleTime2 = o2.getTournamentData().getCyclingTime();
        	        
        	        if (cycleTime1 == null || cycleTime1.equals("")) {
        	        	String swimTime1 = o1.getTournamentData().getSwimTime();
            	        String swimTime2 = o2.getTournamentData().getSwimTime();
            	        
            	        if (swimTime1 == null || swimTime1.equals("")) {
            	            return 1; 
            	        } else if (swimTime2 == null || swimTime2.equals("")) {
            	            return -1;
            	        } else {
            	            int swimTime1InSeconds = convertTimeToSeconds(swimTime1);
            	            int swimTime2InSeconds = convertTimeToSeconds(swimTime2);
            	            return Integer.compare(swimTime1InSeconds, swimTime2InSeconds);
            	        }
        	        } else if (cycleTime2 == null || cycleTime2.equals("")) {
        	            return -1; 
        	        } else {
        	            int cycleTime1InSeconds = convertTimeToSeconds(cycleTime1);
        	            int cycleTime2InSeconds = convertTimeToSeconds(cycleTime2);
        	            return Integer.compare(cycleTime1InSeconds, cycleTime2InSeconds);
        	        }
    	        } else if (runTime2 == null || runTime2.equals("")) {
    	        	String cycleTime1 = o1.getTournamentData().getCyclingTime();
        	        String cycleTime2 = o2.getTournamentData().getCyclingTime();
        	        
        	        if (cycleTime1 == null || cycleTime1.equals("")) {
        	        	String swimTime1 = o1.getTournamentData().getSwimTime();
            	        String swimTime2 = o2.getTournamentData().getSwimTime();
            	        
            	        if (swimTime1 == null || swimTime1.equals("")) {
            	            return 1; 
            	        } else if (swimTime2 == null || swimTime2.equals("")) {
            	            return -1;
            	        } else {
            	            int swimTime1InSeconds = convertTimeToSeconds(swimTime1);
            	            int swimTime2InSeconds = convertTimeToSeconds(swimTime2);
            	            return Integer.compare(swimTime1InSeconds, swimTime2InSeconds);
            	        }
        	        } else if (cycleTime2 == null || cycleTime2.equals("")) {
        	            return -1; 
        	        } else {
        	            int cycleTime1InSeconds = convertTimeToSeconds(cycleTime1);
        	            int cycleTime2InSeconds = convertTimeToSeconds(cycleTime2);
        	            return Integer.compare(cycleTime1InSeconds, cycleTime2InSeconds);
        	        }
    	        } else {
    	            int runTime1InSeconds = convertTimeToSeconds(runTime1);
    	            int runTime2InSeconds = convertTimeToSeconds(runTime2);
    	            return Integer.compare(runTime1InSeconds, runTime2InSeconds);
    	        }
    	    });
    }
    private int convertTimeToSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    // Add the Current Race to the Championship
    public void addRaceToChamp() { 	
    	AthletesSorter();
    	int pos = 1;
    	for (Athlete at : athletes) {
	        if (pos == 1) {
	        	at.getTournamentData().setFinishedAndWon(true);
	        	at.getTournamentData().setPedsWon(1);
	        }
	        at.getTournamentData().setFinalPosition(pos);
	        pos++;
	        if (at.getPhysicalCondition().getEnergy() < 20)
	        	at.getPhysicalCondition().setIEnergy(100);
    	}
    	getCurrentRace().setAthlete(athletes);
    	this.championship.setRaces(getCurrentRace());  	
    }
    
   
    // Stop the Current Race
    public void stopRace() {
    	for (AthleteThreading threads : thread) {
				 threads.interrupt();
		 }
    	startNewRace.setEnabled(true);
        saveAndClose.setEnabled(true);
    	
    JOptionPane.showMessageDialog(this, "All athletes have finished the race!", "Race Finished", JOptionPane.INFORMATION_MESSAGE);
    
}

	// Getter and Setter for athletes 
    public Race getCurrentRace() {
		return races.get(raceChoosen);
	}
    
    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }
    
    public List<ClimaticCondition> getClimaticConditions() {
		return ClimaticConditions;
	}

	public void setClimaticConditions(String climaticConditions) {
		 this.climaticPanel.setText(climaticConditions);
	}
	
	public int getRaceChoosen() {
		return raceChoosen;
	}

	public void setRaceChoosen(int raceChoosen) {
		this.raceChoosen = raceChoosen;
	}
	
	public List<Race> getRaces() {
		return races;
	}
	public void setRaces(List<Race> races) {
		this.races = races;
	}
	
public Championship getChamp() {
    	return this.championship;
    }

	public ChampionshipFrame getChampionFrame() {
		return championFrame;
	}

	public void setChampionFrame(ChampionshipFrame championFrame) {
		this.championFrame = championFrame;
	}
		
}
