package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Serializable.LoadGame;
import Serializable.SaveGame;
import dao.ClimaticConditionDAO;
import events.MovementListener;
import model.Athlete;
import model.Championship;
import model.Race;

public class MenuRaces extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel menuPanel;
    private List<Athlete> athletes;
    private RaceView raceView;
    private List<Race> races;
    private SettingsFrame settingPanel;
    private ChampionshipFrame championshipPanel;
    private Clip clip;
    private MovementListener movementListener;
    private CronometerView crono;
    private Championship championship;
    private static final String FILE_PATH = "settings.ser";

    // Constructor for MenuRaces class.
    // Initializes the main menu and its components.
    public MenuRaces(List<Athlete> athletes, List<Race> raceList, MovementListener movementListener, Championship championship) {
        this.athletes = athletes;
        this.races = raceList;
        this.movementListener = movementListener;
        this.championship = championship;

        initializeUI();
        playMusic("C:\\Users\\Ivo\\eclipse-workspace\\LINX\\Menu_Music.wav");
        centerOnScreen();
    }

    // Initializes the user interface, including layout and menu buttons.
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 440);
        setTitle("Principal Menu");

        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(40, 44, 52)); 
        setContentPane(menuPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        createMenuButtons(gbc);
    }

    // Creates and adds the main menu buttons to the panel.
    private void createMenuButtons(GridBagConstraints gbc) {
        // Start New Championship button
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton startButton = createStyledButton("Start New Championship");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	if (ClimaticConditionDAO.getAllClimaticConditions().size() > 0) {
	                    raceCreation(athletes, races, movementListener); 
	                    setChampionship(new Championship());
	                    SaveGame.saveGame(getChampionship(), FILE_PATH);
                    } else {
                    	startButton.setEnabled(false);
                    	JOptionPane.showMessageDialog(startButton, "You need to add some Climatic Conditions to start Championship.");
                    }
                    	
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuPanel.add(startButton, gbc);

        // Load Last Championship button
        gbc.gridy = 1;
        Championship loadedSettings = LoadGame.loadUserSettings(FILE_PATH);    
        JButton loadRace = createStyledButton("Load Last Championship");
        if (loadedSettings.getRaces().isEmpty()) {
            loadRace.setEnabled(false);
        }
        loadRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChampionship(loadedSettings);
                championshipView(loadedSettings);
            }
        });
        menuPanel.add(loadRace, gbc);

        // Climatic Settings button
        gbc.gridy = 2;
        JButton configButton = createStyledButton("Climatic Settings");
        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    settingPanel = new SettingsFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                settingPanel.setVisible(true);
            }
        });
        menuPanel.add(configButton, gbc);

        // Athletes Stats button
        gbc.gridy = 3;
        JButton athletesStats = createStyledButton("Athletes Stats");
        athletesStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AthleteStatsView(getAthletes());
            }
        });
        menuPanel.add(athletesStats, gbc);

        // Quit Game button
        gbc.gridy = 4;
        JButton backButton = createStyledButton("Quit Game");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clip.stop();
            }
        });
        menuPanel.add(backButton, gbc);
    }

    // Creates a styled JButton with the given text.
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
    
    

    // Centers the window on the screen.
    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    // Starts the race creation process, initializing views and setting up the race.

    public void raceCreation(List<Athlete> athletes, List<Race> raceList, MovementListener movementListener) throws SQLException {
        dispose();
        clip.stop();
        if (crono != null) 
        	crono.setVisible(false);
        crono = new CronometerView();
        raceView = new RaceView(athletes, raceList, movementListener, crono, championship);
        raceView.setVisible(true);
    }

    // Plays background music from the specified file path.

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

    // Getter and Setter methods

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public List<Race> getRaces() {
        return races;
    }

    public MovementListener getMovementListener() {
        return movementListener;
    }

    public void setMovementListener(MovementListener movementListener) {
        this.movementListener = movementListener;
    }

    public RaceView getRaceView() {
        return raceView;
    }

    public CronometerView getCrono() {
        return crono;
    }

    public ChampionshipFrame getChampionshipPanel() {
        return championshipPanel;
    }

    public void setChampionshipPanel(ChampionshipFrame championshipPanel) {
        this.championshipPanel = championshipPanel;
        this.championshipPanel.setVisible(true);
    }

    // Displays the championship view based on the loaded Championship object.
    public void championshipView(Championship champ) {
        if (getRaceView() != null) { 
        	setAthletes(getRaceView().getAthletes());
        	getRaceView().getChamp().setAthletes(getRaceView().getAthletes());
        	}
        setChampionshipPanel(new ChampionshipFrame(this, champ));
    }

    // Returns a list of races that haven't been played yet.
    public List<Race> getNoneplayedRaces() {
        List<Race> nonePlayedRaces = new ArrayList<>();
        for (Race race : getRaces()) {
            for (@SuppressWarnings("rawtypes") Entry entry : championship.getRaces().entrySet()) {
                if (!entry.getKey().equals(race.getCity().getCityName())) {
                    nonePlayedRaces.add(race);
                }
            }
        }
        return nonePlayedRaces;
    }
}
