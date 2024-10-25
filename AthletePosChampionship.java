package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Athlete;

public class AthletePosChampionship extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable athleteTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<Athlete> athletes = new ArrayList<>();

    public AthletePosChampionship(List<Athlete> athletes) {
    	this.athletes = athletes;
    	this.athletes.sort((o1,o2) -> (int) (o2.getTournamentData().getChampiosnshipPoints() - o1.getTournamentData().getChampiosnshipPoints()));
        setTitle("Athlete Championship Positions");
        setSize(750, 750);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (int) (screenSize.width / 2 + getWidth()/4.5);
        int y = screenSize.height / 2 - getHeight() / 2;
        setLocation(x, y); 

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(60, 63, 65));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Define table columns
        String[] columnNames = {"Name", "Position in Championship", "Points"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        athleteTable = new JTable(tableModel);
        athleteTable.setBackground(new Color(255, 255, 255));
        athleteTable.setForeground(Color.BLACK);
        athleteTable.setFont(new Font("Arial", Font.PLAIN, 14));
        athleteTable.setRowHeight(30);

        athleteTable.getTableHeader().setBackground(new Color(33, 150, 243));
        athleteTable.getTableHeader().setForeground(Color.WHITE);
        athleteTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        loadAthleteData();

        sorter = new TableRowSorter<>(tableModel);
        athleteTable.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(athleteTable);
        getContentPane().add(tableScrollPane, BorderLayout.CENTER);
        
        adjustColumnWidths();
        centerTableData();
        setVisible(true);
    }

    // Method to load athlete data into the table
    private void loadAthleteData() {
    	int i=0;
        for (Athlete athlete : this.athletes) {
        	athletes.get(i).getTournamentData().setChampiosnshipPosition(i+1);
            Object[] rowData = {
                athlete.getName() + " " + athlete.getSurname(),
                athlete.getTournamentData().getChampiosnshipPosition(),
                athlete.getTournamentData().getChampiosnshipPoints(), 
            };     
            i++;
            tableModel.addRow(rowData);
        }
    }


    // Adjust column widths
    private void adjustColumnWidths() {
        athleteTable.getColumnModel().getColumn(0).setPreferredWidth(200); 
        athleteTable.getColumnModel().getColumn(1).setPreferredWidth(150); 
        athleteTable.getColumnModel().getColumn(2).setPreferredWidth(100); 
    }

    // Center table data
    private void centerTableData() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < athleteTable.getColumnCount(); i++) {
            athleteTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
