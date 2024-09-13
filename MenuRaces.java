package view;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.AthleteMovementTimer;
import dao.ClimaticConditionDAO;
import events.MovementListener;
import model.Athlete;
import model.Race;

public class MenuRaces extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel menuPanel;
    private List<Athlete> athletes;
    private RaceView Races;
	private Race race;
    private SettingsFrame settingPanel;
    private Clip clip;

    public RaceView getRaces() {
        return Races;
    }

    public MenuRaces(List<Athlete> athletes, List<Race> raceList, MovementListener movementListener) {
        this.athletes = athletes;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 440);

        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        setContentPane(menuPanel);
        setTitle("Principal Menu");

        playMusic("C:\\Users\\Nicolas\\Desktop\\Facultad\\Programacion B\\WorkSapaceEclipse\\LINX\\Menu_Music.wav");

        JButton startButton = new JButton("Start Race");
        startButton.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
        startButton.setBounds(20, 270, 116, 33);
        startButton.setBackground(UIManager.getColor("Button.background"));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clip.stop();                       	
                Random random = new Random();
                int r = random.nextInt(raceList.size());
                race = raceList.get(r);
                try {
					race.setClimaticCondition(ClimaticConditionDAO.getAllClimaticConditions());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                raceCreation(athletes, race, movementListener);
                AthleteMovementTimer movementTimer = new AthleteMovementTimer(List.of(movementListener), athletes);
            	movementTimer.start();
            }
        });
        menuPanel.add(startButton);

        JButton configButton = new JButton("Settings");
        configButton.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
        configButton.setBounds(20, 314, 116, 33);
        configButton.setBackground(UIManager.getColor("Button.background"));
        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
					try {
						settingPanel = new SettingsFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	
            	settingPanel.setVisible(true);    	
            }
        });
        menuPanel.add(configButton);

        JButton backButton = new JButton("Quit Game");
        backButton.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
        backButton.setBounds(20, 358, 116, 33);
        backButton.setBackground(UIManager.getColor("Button.background"));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                clip.stop();
            }
        });
        menuPanel.add(backButton);
        JLabel imageLabel = new JLabel(new ImageIcon("C:\\Users\\Nicolas\\Desktop\\Facultad\\Programacion B\\WorkSapaceEclipse\\LINX\\triatlon_Menu.jpg"));
        imageLabel.setBounds(new Rectangle(-239, -273, 1165, 943));
        menuPanel.add(imageLabel);
       }

    public void raceCreation(List<Athlete> athletes, Race race, MovementListener movementListener) {
        this.Races = new RaceView(athletes, race, movementListener);
        Races.setVisible(true);
    }
    
    public void SettingsPanel() {
        try {
			this.settingPanel = new SettingsFrame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        settingPanel.setVisible(true);
    }

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
}
