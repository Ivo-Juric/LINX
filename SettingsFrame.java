package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClimaticConditionDAO;
import model.ClimaticCondition;

public class SettingsFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel climaticTableModel;
    private JTable climaticTable;

    // Constructor for initializing the settings frame
    public SettingsFrame() throws SQLException {
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(40, 44, 52)); // Dark background

        // Create table model
        climaticTableModel = new DefaultTableModel(new Object[]{"Id", "Description", "Lower Bound", "Upper Bound"}, 0);
        climaticTable = new JTable(climaticTableModel);
        climaticTable.setBackground(new Color(255, 255, 255));
        climaticTable.setForeground(Color.BLACK);
        climaticTable.getTableHeader().setBackground(new Color(255, 165, 0));
        climaticTable.getTableHeader().setForeground(Color.WHITE);
        climaticTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(new JScrollPane(climaticTable), BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 44, 52)); 

        // Create and add "Add Condition" button
        JButton addButton = createStyledButton("Add Condition");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addClimaticCondition();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(addButton);

        // Create and add "Edit Condition" button
        JButton editButton = createStyledButton("Edit Condition");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editClimaticCondition();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(editButton);

        // Create and add "Delete Condition" button
        JButton deleteButton = createStyledButton("Delete Condition");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteClimaticCondition();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(deleteButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);

        loadClimaticConditions();
    }

    // Method for creating styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(255, 165, 0)); // Orange
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    // Load climatic conditions from the database and display them in the table
    private void loadClimaticConditions() throws SQLException {
        climaticTableModel.setRowCount(0); 
        List<ClimaticCondition> conditions = ClimaticConditionDAO.getAllClimaticConditions();
        conditions.sort(Comparator.comparingInt(ClimaticCondition::getIdCli));
        for (ClimaticCondition condition : conditions) {
            climaticTableModel.addRow(new Object[]{
                condition.getIdCli(),
                condition.getUnitOfMeasurament().getUnitofMeas(),
                condition.getLowerBound(),
                condition.getUpperBound()
            });
        }
    }

    // Add a new climatic condition to the database
    private void addClimaticCondition() throws SQLException {
        ClimaticConditionDialog dialog = new ClimaticConditionDialog(null);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            ArrayList<ClimaticCondition> conditions = (ArrayList<ClimaticCondition>) ClimaticConditionDAO.getAllClimaticConditions();
            ClimaticCondition newCondition = dialog.getClimaticCondition(conditions);
            ClimaticConditionDAO.insertClimaticCondition(0, newCondition.getUnitOfMeasurament().getUnitofMeas(),
                    newCondition.getLowerBound(), newCondition.getUpperBound(), newCondition.getSwWear(), 
                    newCondition.getCyWear(), newCondition.getPdWear());
            loadClimaticConditions(); 
        }
    }

    // Edit an existing climatic condition in the database
    private void editClimaticCondition() throws SQLException {
        int selectedRow = climaticTable.getSelectedRow();
        if (selectedRow != -1) {
            int idCli = (int) climaticTableModel.getValueAt(selectedRow, 0);
            ClimaticCondition existingCondition = ClimaticConditionDAO.getConditionByClim(idCli);

            ClimaticConditionDialog dialog = new ClimaticConditionDialog(existingCondition);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                ClimaticCondition updatedCondition = dialog.getClimaticCondition(existingCondition);
                ClimaticConditionDAO.updateClimaticCondition(updatedCondition);
                loadClimaticConditions(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a condition to edit.");
        }
    }

    // Delete a climatic condition from the database
    private void deleteClimaticCondition() throws SQLException {
        int selectedRow = climaticTable.getSelectedRow();
        if (selectedRow != -1) {
            int idCli = (int) climaticTableModel.getValueAt(selectedRow, 0);
            ClimaticConditionDAO.deleteClimaticCondition(idCli);
            loadClimaticConditions();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a condition to delete.");
        }
    }
}
