package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    
    public SettingsFrame() throws SQLException {
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        climaticTableModel = new DefaultTableModel(new Object[]{"Description","Unit of Measurament","Lower Bound", "Upper Bound"}, 0);
        climaticTable = new JTable(climaticTableModel);
        getContentPane().add(new JScrollPane(climaticTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Condition");
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

        JButton editButton = new JButton("Edit Condition");
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

        JButton deleteButton = new JButton("Delete Condition");
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

    private void loadClimaticConditions() throws SQLException {
        climaticTableModel.setRowCount(0);
        List<ClimaticCondition> conditions = ClimaticConditionDAO.getAllClimaticConditions();
        conditions.sort(Comparator.comparingInt(ClimaticCondition::getIdCli));
        for (ClimaticCondition condition : conditions) {
            climaticTableModel.addRow(new Object[]{
                condition.getDescrption(),
                condition.getUnitOfMeasurament().getDescription(),
                condition.getLowerBound(),
                condition.getUpperBound()
            });
        }
    }

    private void addClimaticCondition() throws SQLException {
        ClimaticConditionDialog dialog = new ClimaticConditionDialog(null);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
        	ArrayList<ClimaticCondition> conditions = (ArrayList<ClimaticCondition>) ClimaticConditionDAO.getAllClimaticConditions();
            ClimaticCondition newCondition = dialog.getClimaticCondition(conditions);
            ClimaticConditionDAO.insertClimaticCondition(0 ,newCondition.getDescrption(), newCondition.getUnitOfMeasurament().getDescription(), newCondition.getLowerBound(),newCondition.getUpperBound(),newCondition.getSwWear(),newCondition.getCyWear(),newCondition.getPdWear());
            loadClimaticConditions();
        }
    }

    private void editClimaticCondition() throws SQLException {
        int selectedRow = climaticTable.getSelectedRow();
        if (selectedRow != -1) {
            String description = (String) climaticTableModel.getValueAt(selectedRow, 0);
            ClimaticCondition existingCondition = ClimaticConditionDAO.getConditionByDescription(description);

            ClimaticConditionDialog dialog = new ClimaticConditionDialog(existingCondition);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                ClimaticCondition updatedCondition = dialog.getClimaticCondition(existingCondition); 
                System.out.println(updatedCondition.getDescrption() + " " + updatedCondition.getIdCli());
                ClimaticConditionDAO.updateClimaticCondition(updatedCondition);
                loadClimaticConditions();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a condition to edit.");
        }
    }

    private void deleteClimaticCondition() throws SQLException {
        int selectedRow = climaticTable.getSelectedRow();
        if (selectedRow != -1) {
            String description = (String) climaticTableModel.getValueAt(selectedRow, 0);
            ClimaticConditionDAO.deleteClimaticCondition(description);
            loadClimaticConditions();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a condition to delete.");
        }
    }
}
