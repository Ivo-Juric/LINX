package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ClimaticCondition;
import model.UnitOfMeasurament;

public class ClimaticConditionDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField descriptionField, lowerBoundField, upperBoundField;
    private boolean confirmed = false;
    private ClimaticCondition climaticCondition;
    private String unitOfMeasuramentField;
    private int lastID = 0;

    public ClimaticConditionDialog(ClimaticCondition climaticCondition) {
        setTitle("Climatic Condition");
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(40, 44, 52));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createColoredLabel("Lower Bound:"), gbc);
        
        lowerBoundField = new JTextField(climaticCondition != null ? Float.toString(climaticCondition.getLowerBound()) : "");
        lowerBoundField.setPreferredSize(new Dimension(200, 25)); 
        gbc.gridx = 1;
        add(lowerBoundField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createColoredLabel("Upper Bound:"), gbc);
        
        upperBoundField = new JTextField(climaticCondition != null ? Float.toString(climaticCondition.getUpperBound()) : "");
        upperBoundField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        add(upperBoundField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createColoredLabel("Unit of Measurement:"), gbc);
        
        gbc.gridx = 1;

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setBackground(new Color(40, 44, 52));
        add(checkBoxPanel, gbc);

        JCheckBox hrCheckBox = new JCheckBox("HR");        
        JCheckBox kmhCheckBox = new JCheckBox("Km/h");
        JCheckBox celsiusCheckBox = new JCheckBox("ºC");
        
        hrCheckBox.setForeground(Color.WHITE);
        hrCheckBox.setBackground(new Color(40, 44, 52));
        kmhCheckBox.setForeground(Color.WHITE);
        kmhCheckBox.setBackground(new Color(40, 44, 52));
        celsiusCheckBox.setForeground(Color.WHITE);
        celsiusCheckBox.setBackground(new Color(40, 44, 52));
        checkBoxPanel.add(hrCheckBox);
        checkBoxPanel.add(kmhCheckBox);
        checkBoxPanel.add(celsiusCheckBox);

        hrCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kmhCheckBox.setSelected(false);
                celsiusCheckBox.setSelected(false);
                unitOfMeasuramentField = hrCheckBox.getText();
            }
        });

        kmhCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrCheckBox.setSelected(false);
                celsiusCheckBox.setSelected(false);
                unitOfMeasuramentField = kmhCheckBox.getText();
            }
        });

        celsiusCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrCheckBox.setSelected(false);
                kmhCheckBox.setSelected(false);
                unitOfMeasuramentField = celsiusCheckBox.getText();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        JButton okButton = createStyledButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        add(okButton, gbc);

        JButton cancelButton = createStyledButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton, gbc);

        pack();
        setModal(true);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
    }

    private JLabel createColoredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    
    public ClimaticCondition getClimaticCondition(ClimaticCondition existingClima) {
        if (climaticCondition == null) {
            try {
                climaticCondition = new ClimaticCondition(existingClima.getIdCli(),
                        new UnitOfMeasurament(unitOfMeasuramentField),
                        Float.parseFloat(lowerBoundField.getText()),
                        Float.parseFloat(upperBoundField.getText()),
                        existingClima.getSwWear(), existingClima.getCyWear(), existingClima.getPdWear()
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Please enter a Unit of Measurment.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            } 
        }
        return climaticCondition;
    }
    
    public ClimaticCondition getClimaticCondition(ArrayList<ClimaticCondition> existingClima) {
        if (existingClima.size() > 0) {
	    	int idCli = existingClima.get(existingClima.size()-1).getIdCli();       
	        if (climaticCondition == null && lastID < idCli) {
	            try {
	                climaticCondition = new ClimaticCondition(idCli,
	                        new UnitOfMeasurament(unitOfMeasuramentField),
	                        Float.parseFloat(lowerBoundField.getText()),
	                        Float.parseFloat(upperBoundField.getText()),
	                        0, 0, 0
	                );
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
	                return null;
	            }
	            lastID = idCli;
	        }      
	        
	    }else {
	    	if (climaticCondition == null) {
	    		lastID++;
	            try {
	                climaticCondition = new ClimaticCondition(lastID,
	                        new UnitOfMeasurament(unitOfMeasuramentField),
	                        Float.parseFloat(lowerBoundField.getText()),
	                        Float.parseFloat(upperBoundField.getText()),
	                        0, 0, 0
	                );
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
	                return null;
	            }
	            
	        }      
	    }    	
        return climaticCondition;
    }

	public JTextField getDescriptionField() {
		return descriptionField;
	}

	public void setDescriptionField(JTextField descriptionField) {
		this.descriptionField = descriptionField;
	}
}
