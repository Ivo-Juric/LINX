package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Serializable.LoadGame;
import controller.RaceController;
import model.Athlete;
import model.Championship;
import model.Race;

public class ChampionshipFrame extends JFrame {
	
	// Attributes
    private static final long serialVersionUID = 1L;
    private JTable championshipTable;
    private DefaultTableModel tableModel;
    private AtomicInteger athleteCount = new AtomicInteger(0);
    private AthletePosChampionship athletePosChampionship;

    public ChampionshipFrame(MenuRaces menu, Championship championships) {
    	athletePosChampionship = new AthletePosChampionship(championships.getAthletes());
        setTitle("Championship Details");
        setSize(1000, 750);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65)); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = (int) (screenSize.width / 2 - getWidth()/1.2);
        int y = screenSize.height / 2 - getHeight() / 2;
        setLocation(x, y); 

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(60, 63, 65));  
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        String[] columnNames = {"Race", "Position", "Name && Surname", "Swim Time", "Cycling Time", "Running Time", "Total Distance"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        championshipTable = new JTable(tableModel);
        championshipTable.setBackground(new Color(255, 255, 255)); 
        championshipTable.setForeground(Color.BLACK); 
        championshipTable.setFont(new Font("Arial", Font.PLAIN, 14)); 
        championshipTable.setRowHeight(30); 

        championshipTable.getTableHeader().setBackground(new Color(33, 150, 243));  
        championshipTable.getTableHeader().setForeground(Color.WHITE);  
        championshipTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); 
        
        addButtoms(menu,titlePanel);
        
        loadChampionshipData(championships);

        JScrollPane tableScrollPane = new JScrollPane(championshipTable);
        getContentPane().add(tableScrollPane, BorderLayout.CENTER);
        
        
        
        adjustColumnWidths();   
        highlightEmptyCells();
        athletePosChampionship.setVisible(true);
        setVisible(true);
    	
    }
   

    private void addButtoms(MenuRaces menu, JPanel titlePanel) {
    	JButton Continuebuton = new JButton("Continue race");
        Continuebuton.setForeground(Color.WHITE);
        Continuebuton.setFont(new Font("Arial", Font.BOLD, 18));
        Continuebuton.setBackground(new Color(33, 150, 243));
        titlePanel.add(Continuebuton);
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        
        Continuebuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	if (menu != null) {
                		RaceController newRaceController = new RaceController(menu.getAthletes(), menu.getRaces(), LoadGame.loadUserSettings("settings.ser"));
                		for (Athlete at : newRaceController.getAthletes()) {
                	        at.getTournamentData().setDiscipline(0);
                		}
                		newRaceController.getMenu().raceCreation(newRaceController.getAthletes(), newRaceController.getMenu().getNoneplayedRaces() , newRaceController);		
                		if (menu.getRaceView() != null) {
                			menu.getRaceView().setVisible(false);
                				menu.getCrono().setVisible(false);
                		}
                		menu.setVisible(false);	
                	}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                setVisible(false);
                athletePosChampionship.setVisible(false);
            }
        });
        
        JButton goBackbuton = new JButton("Go Back");
        goBackbuton.setForeground(Color.WHITE);
        goBackbuton.setFont(new Font("Arial", Font.BOLD, 18));
        goBackbuton.setBackground(new Color(33, 150, 243));
        titlePanel.add(goBackbuton);
        getContentPane().add(titlePanel, BorderLayout.NORTH);  
        goBackbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                athletePosChampionship.setVisible(false);
            }
        });
	}
    
    // Method to paint in color red to empty Discipline Times
    private void highlightEmptyCells() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;		
			@Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                if (value == null || value.toString().trim().isEmpty()|| value.toString().trim().equals("")) {
                    c.setBackground(Color.red);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        };
        DefaultTableCellRenderer cellRenderer2 = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;		
			@Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                if (value == null || value.toString().trim().isEmpty() || value.toString().trim().equals("")) 
                	c.setBackground(Color.BLACK);
                else
                	c.setBackground(Color.WHITE);
                 return c;
            }
        };
        championshipTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer2);
        championshipTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer2);
        championshipTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer2); 
        championshipTable.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        championshipTable.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        championshipTable.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        championshipTable.getColumnModel().getColumn(6).setCellRenderer(cellRenderer2); 
        
        
    }

	// Method to load championship data into the table
    public void loadChampionshipData(Championship championship) {
            for (Entry<String, Race> raceEntry : championship.getRaces().entrySet()) {
                Race race = raceEntry.getValue();
                race.getAthlete().sort((o1,o2) -> (int) (o1.getTournamentData().getPosition() - o2.getTournamentData().getPosition())); 
                for (Athlete athletes : race.getAthlete()) {
                	float dis = 0;
                	if (athletes.getTournamentData().getFinalUDistance() != 0) {
                		
                		if (athletes.getTournamentData().getFinalUDistance() >= 900)
                			dis = race.getTotalDistance();
                		else
                			dis = athletes.getTournamentData().getFinalUDistance()*race.getTotalDistance()/900;
                	}
                	
	                Object[] rowData = {
	                    race.getCity().getCityName(), 
	                    athletes.getTournamentData().getPosition(), 
	                    athletes.getName() + " " + athletes.getSurname(),         
	                    athletes.getTournamentData().getSwimTime(),           
	                    athletes.getTournamentData().getCyclingTime(),       
	                    athletes.getTournamentData().getRunningTime(), 
	                    String.format("%.2f km", dis)                 
	                };
	                	athleteCount.incrementAndGet();
	                	tableModel.addRow(rowData);
                }
                Object[] rowData = {"", "", "", "", "","" };
                tableModel.addRow(rowData);
                highlightEmptyCells();             
            }
            
    }
 
    
    // Adjust the width of the columns to be visible without scrolling
    private void adjustColumnWidths() {
        
        championshipTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
        championshipTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        championshipTable.getColumnModel().getColumn(2).setPreferredWidth(200); 
        championshipTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        championshipTable.getColumnModel().getColumn(4).setPreferredWidth(100); 
        championshipTable.getColumnModel().getColumn(5).setPreferredWidth(100);
    }
}
