package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import model.Athlete;

public class AthleteStatsView extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // UI components
    private JTable athleteTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    // Main constructor that sets up the view
    public AthleteStatsView(List<Athlete> athletes) {
        configureMainFrame();             
        createSearchPanel(); 
        createTableModel();               
        createAthleteTable();             
        loadAthleteData(athletes);        
        
        JScrollPane tableScrollPane = new JScrollPane(athleteTable);
        getContentPane().add(tableScrollPane, BorderLayout.CENTER); 
        
        addSearchFunctionality();        
        displayWindow();                  
    }

    // Configures basic properties of the JFrame
    private void configureMainFrame() {
        setTitle("Athlete Stats & Reports");
        setSize(1200, 800);  
        setLocationRelativeTo(null);      
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65));  
    }

    // Creates the top panel with the search field
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(60, 63, 65));  
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(Color.WHITE); 

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBackground(new Color(255, 255, 255)); 
        searchField.setForeground(Color.BLACK);  

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        getContentPane().add(searchPanel, BorderLayout.NORTH); 
        
        return searchPanel;
    }

    // Creates the table model, which contains the columns and data
    private void createTableModel() {
        String[] columnNames = {
            " Name and Surname ", " Nationality ", " Swimming Wins ", 
            " Cycling Wins ", " Pedestrianism Wins ", " Races Wins ", 
            " Retirements ", " Finished Races "
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1303891900209740457L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Cells are not editable
            }
        };
    }

    // Creates and configures the athlete table
    private void createAthleteTable() {
        athleteTable = new JTable(tableModel);
        athleteTable.setBackground(new Color(255, 255, 255)); 
        athleteTable.setForeground(Color.BLACK);
        athleteTable.setFont(new Font("Arial", Font.PLAIN, 14));
        athleteTable.setRowHeight(30); 
        
        // Table header styling
        JTableHeader header = athleteTable.getTableHeader();
        header.setBackground(new Color(33, 150, 243));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        adjustColumnWidths(athleteTable);
        
        rowSorter = new TableRowSorter<>(tableModel);
        athleteTable.setRowSorter(rowSorter);
    }

    // Method to adjust the width of the columns based on the size of the header and content
    private void adjustColumnWidths(JTable table) {
        JTableHeader header = table.getTableHeader();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int preferredWidth = header.getDefaultRenderer()
                                       .getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, i)
                                       .getPreferredSize().width + 20;
            column.setPreferredWidth(preferredWidth);
        }
    }

    // Loads athlete data into the table
    private void loadAthleteData(List<Athlete> athletes) {
        for (Athlete athlete : athletes) {
            Object[] rowData = {
                athlete.getName() + " " + athlete.getSurname(),
                athlete.getNationality(),
                athlete.getTournamentData().getSwmmWin(), 
                athlete.getTournamentData().getCycWin(),
                athlete.getTournamentData().getPedsWon(),
                athlete.getTournamentData().getFinishedAndWon(),
                athlete.getTournamentData().getAbandonedRaces(),
                athlete.getFinishedRaces()
            };
            tableModel.addRow(rowData);
        }
    }

    // Adds functionality to filter athletes by search input
    private void addSearchFunctionality() {
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                filterAthletes(searchText);
            }
        });
    }

    // Method to filter athletes by name
    private void filterAthletes(String searchText) {
        if (searchText.trim().isEmpty()) {
            rowSorter.setRowFilter(null); 
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); 
        }
    }

    // Displays the window with appropriate size and centering
    private void displayWindow() {
        setPreferredSize(new Dimension(1200, 800)); 
        setLocationRelativeTo(null); 
        pack(); 
        setVisible(true);
    }
}
