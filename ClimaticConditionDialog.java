package view;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ClimaticCondition;
import model.UnitOfMeasurament;

public class ClimaticConditionDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField descriptionField, lowerBoundField, upperBoundField, unitOfMeasuramentField;
    private boolean confirmed = false;
    private ClimaticCondition climaticCondition;

    public ClimaticConditionDialog(ClimaticCondition climaticCondition) {
        setTitle("Climatic Condition");
        setLayout(new GridLayout(10, 1000));

        // Campos de entrada
        add(new JLabel("Description:"));
        descriptionField = new JTextField(climaticCondition != null ? climaticCondition.getDescrption() : "");
        add(descriptionField);

        add(new JLabel("Lower Bound:"));
        lowerBoundField = new JTextField(climaticCondition != null ? Float.toString(climaticCondition.getLowerBound()) : "");
        add(lowerBoundField);

        add(new JLabel("Upper Bound:"));
        upperBoundField = new JTextField(climaticCondition != null ? Float.toString(climaticCondition.getUpperBound()) : "");
        add(upperBoundField);
        
        add(new JLabel("Unit of Measurament:"));
        unitOfMeasuramentField = new JTextField(climaticCondition != null ? (climaticCondition.getUnitOfMeasurament().getDescription()) : "");
        add(unitOfMeasuramentField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);

        pack();
        setModal(true);
        setLocationRelativeTo(null);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public ClimaticCondition getClimaticCondition(ClimaticCondition existingClima) {
        if (climaticCondition == null) {
            try {             
                climaticCondition = new ClimaticCondition(existingClima.getIdCli(),
                    descriptionField.getText(),
                    new UnitOfMeasurament(unitOfMeasuramentField.getText()),
                    Float.parseFloat(lowerBoundField.getText()),
                    Float.parseFloat(upperBoundField.getText()),
                    existingClima.getSwWear(), existingClima.getCyWear(), existingClima.getPdWear()
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return climaticCondition;
    }
    public ClimaticCondition getClimaticCondition(ArrayList<ClimaticCondition> existingClima) {
    	int idCli = existingClima.get(existingClima.size()-1).getIdCli();
        if (climaticCondition == null) {
            try {             
                climaticCondition = new ClimaticCondition(idCli,
                    descriptionField.getText(),
                    new UnitOfMeasurament(unitOfMeasuramentField.getText()),
                    Float.parseFloat(lowerBoundField.getText()),
                    Float.parseFloat(upperBoundField.getText()),
                    0, 0, 0
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return climaticCondition;
    }
    
    
    
    
}
