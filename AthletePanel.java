package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import events.MovementListener;
import model.Athlete;
import model.Race;

@SuppressWarnings("serial")
public class AthletePanel extends JPanel {
    private JButton btnNewButton, btnNewButton_1;
    private JLabel ubicaction;
    @SuppressWarnings("unused")
	private MovementListener movementListener;
    private Race race;

    public AthletePanel(Athlete athlete, int yP, Race race) {
       // this.movementListener = movementListener;
    	this.race = race;
        setLayout(null);
        setBounds(10, yP, 1000, 100);

        JTextField Athleten = new JTextField();
        Athleten.setText(athlete.getName() + " " + athlete.getSurname());
        Athleten.setColumns(10);
        Athleten.setBounds(10, 10, 100, 25);
        Athleten.setBackground(UIManager.getColor("Button.background"));
        Athleten.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add(Athleten);

        btnNewButton = new JButton("+");
        btnNewButton_1 = new JButton("-");

        btnNewButton.setBounds(10, 40, 48, 25); 
        btnNewButton_1.setBounds(60, 40, 48, 25);
        add(btnNewButton);
        add(btnNewButton_1);

        ubicaction = new JLabel(new ImageIcon("C:\\Users\\Nicolas\\Desktop\\Facultad\\Programacion B\\WorkSapaceEclipse\\LINX\\Athlete_Image.jpg"));
        ubicaction.setBounds(150, 20, 20, 20);
        add(ubicaction);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                athlete.getPhysicalCondition().setSpeed(athlete.getPhysicalCondition().getSpeed() + 5);
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (athlete.getPhysicalCondition().getSpeed() - 5 >= 0) {
                    athlete.getPhysicalCondition().setSpeed(athlete.getPhysicalCondition().getSpeed() - 5);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, this.getHeight());
        g.drawLine(650, 0, 650,this.getHeight());
        g.drawLine(900, 0, 900, this.getHeight());       
        g.setColor(Color.RED);
        int x = 400;
        for (int i=0; i< race.getProvisioning().size(); i++) {
        		if (race.getProvisioning().get(i).getModality().equals("ciclismo"))
        			g.drawLine(x + getUbiProvisioningPlacesCy(i), 0, x + getUbiProvisioningPlacesCy(i), this.getHeight()); 
        		else{
        			x=650;
        			g.drawLine(x + getUbiProvisioningPlacesPd(i), 0, x + getUbiProvisioningPlacesPd(i), this.getHeight());
        		}
        }
    }
    
    public int getUbiProvisioningPlacesCy(int index) {
		return (int) (race.getProvisioning().get(index).getDistanceKm()*250 / race.getModality().getDistanceDisipline().get(1).getDistance());
    }
    
    public int getUbiProvisioningPlacesPd(int index) {
		return (int) (race.getProvisioning().get(index).getDistanceKm()*250 / race.getModality().getDistanceDisipline().get(2).getDistance());
    }

    public void movePanel(int dx) {
        ubicaction.setLocation(ubicaction.getX() + dx, ubicaction.getY());
        
    }
}
