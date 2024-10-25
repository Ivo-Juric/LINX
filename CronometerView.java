package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CronometerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel timerLabel;
    private int totalMilliseconds;

    public CronometerView() {
        setTitle("Chronometer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(40, 44, 52));  

        // Create the JLabel with more detailed styling
        timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timerLabel.setForeground(Color.WHITE);  
        timerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // Add border to the JLabel
        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(60, 63, 65)); 
        timerLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 87, 34), 5)); 
        
        add(timerLabel, BorderLayout.CENTER);

        totalMilliseconds = 0;
        
        setLocationTopCenter();  
        setVisible(true);
    }

    public JLabel getTimerLabel() {
		return timerLabel;
	}

	// Method to position the window at the top of the screen
    private void setLocationTopCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = 0;
        setLocation(x, y);
    }

    // Update the time on the chronometer
    public void setTime(int maxTimeMilliseconds, int duration) {
        totalMilliseconds += maxTimeMilliseconds / (duration / 250); 

        int hours = totalMilliseconds / 3600000;
        int minutes = (totalMilliseconds % 3600000) / 60000;
        int seconds = (totalMilliseconds % 60000) / 1000;

        String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        
        timerLabel.setText(timeFormatted);
    }
    
    public int timeToMilliseconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        int milliseconds = (hours * 3600000) + (minutes * 60000) + (seconds * 1000);

        return milliseconds;
    }

	public int getTotalMilliseconds() {
		return totalMilliseconds;
	}

	public void setTotalMilliseconds(int totalMilliseconds) {
		this.totalMilliseconds = totalMilliseconds;
	}
}
