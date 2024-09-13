package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import events.MovementListener;
import model.Athlete;
import model.Race;

public class RaceView extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel principalPanel;
    @SuppressWarnings("unused")
	private MovementListener movementListener;
    private List<AthletePanel> athletePanel = new ArrayList<>();
    private List<Athlete> athletes;
    private Clip clip;
    
    public AthletePanel getAthletePanel(int i) {
        return athletePanel.get(i);
    }

    public RaceView(List<Athlete> athletes, Race race, MovementListener movementListener) {
    	//playMusic("");
    	this.athletes = athletes;
        this.movementListener = movementListener;

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 561);
        
        principalPanel = new JPanel();
        principalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        principalPanel.setLayout(null);
        
        JTextPane swimming = new JTextPane();
        swimming.setBounds(235, 20, 111, 34);
        swimming.setBackground(UIManager.getColor("Button.background"));
        swimming.setFont(new Font("Tahoma", Font.PLAIN, 23));
        swimming.setText("Swimming");
        principalPanel.add(swimming);
        
        JTextPane cycling = new JTextPane();
        cycling.setBounds(495, 20, 79, 50);
        cycling.setBackground(UIManager.getColor("Button.background"));
        cycling.setText("Cycling");
        cycling.setFont(new Font("Tahoma", Font.PLAIN, 23));
        principalPanel.add(cycling);
        
        JTextPane pedestrism = new JTextPane();
        pedestrism.setBounds(730, 20, 131, 50);
        pedestrism.setText("Pedestriasm");
        pedestrism.setFont(new Font("Tahoma", Font.PLAIN, 23));
        pedestrism.setBackground(UIManager.getColor("Button.background"));
        principalPanel.add(pedestrism);
        
        int yP = 100;
        for (Athlete at : athletes) {
            AthletePanel athletePane = new AthletePanel(at, yP,race);
            athletePanel.add(athletePane);
            principalPanel.add(athletePane);
            yP += 100;
        }

        principalPanel.setPreferredSize(new java.awt.Dimension(900, yP + 5));
        
        JScrollPane scrollPane = new JScrollPane(principalPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JTextPane raceName = new JTextPane();
        raceName.setBounds(27, 20, 131, 50);
        raceName.setText(race.toString());
        principalPanel.add(raceName);
        setContentPane(scrollPane);
        setTitle("Carrera de Triatlón");
    }

    public void setMovementListener(MovementListener movementListener) {
        this.movementListener = movementListener;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
       
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
