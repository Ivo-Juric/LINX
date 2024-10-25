package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.ClimaticCondition;
import model.UnitOfMeasurament;

public class ClimaticConditionDAO {
	
	// Method to insert a new record on the Base Data
	public static void insertClimaticCondition(int i, String unitOfMeasurament, float lowerBound, float UpperBound, float SwWear, float CyWear,  float PdWear) throws SQLException {
        String sql = "INSERT INTO climaticCondition (unitOfMeasurament, lowerBound, UpperBound, SwWear, CyWear, PdWear) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unitOfMeasurament);
            pstmt.setFloat(2, lowerBound);
            pstmt.setFloat(3, UpperBound);
            pstmt.setFloat(4, SwWear);
            pstmt.setFloat(5, CyWear);
            pstmt.setFloat(6, PdWear);
            pstmt.executeUpdate();
        }
    }
	
	// Method to refresh an existing record from climaticCondition table.
	public static void updateClimaticCondition(ClimaticCondition clima) throws SQLException {
	    String sql = "UPDATE climaticCondition SET unitOfMeasurament = ?, lowerBound = ?, UpperBound = ?, SwWear = ?, CyWear = ?, PdWear = ? WHERE idcli = ?";

	    try (Connection conn = DataBaseCC.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	        pstmt.setString(1, clima.getUnitOfMeasurament().getUnitofMeas());
	        pstmt.setFloat(2, clima.getLowerBound());
	        pstmt.setFloat(3, clima.getUpperBound());
	        pstmt.setFloat(4, clima.getSwWear());
	        pstmt.setFloat(5, clima.getCyWear());
	        pstmt.setFloat(6, clima.getPdWear());
	        pstmt.setInt(7, clima.getIdCli()); 

	        pstmt.executeUpdate();
	    } catch (NullPointerException e) {
	        JOptionPane.showMessageDialog(null, "Please enter valid numeric or character values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

    
    // Método para eliminar un registro en la tabla climaticCondition por su ID.
    public static void deleteClimaticCondition(int idCli) throws SQLException {
        String sql = "DELETE FROM climaticCondition WHERE idCli = ?";

        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, idCli);
            pstmt.executeUpdate();
        }
    }
    
    // Method to obtain all records from climaticCondition table.
    public static List<ClimaticCondition> getAllClimaticConditions() throws SQLException {
        String sql = "SELECT idcli, unitOfMeasurament, lowerBound, UpperBound, SwWear, CyWear, PdWear FROM climaticCondition";
        List<ClimaticCondition> climaticConditions = new ArrayList<>();

        try (Connection conn = DataBaseCC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	int idcli = rs.getInt("idcli");
                String unitOfMeasurement = rs.getString("unitOfMeasurament");
                float lowerBound = rs.getFloat("lowerBound");
                float upperBound = rs.getFloat("UpperBound");
                float swWear = rs.getFloat("SwWear");
                float cyWear = rs.getFloat("CyWear");
                float pdWear = rs.getFloat("PdWear");

                ClimaticCondition climaticCondition = new ClimaticCondition (idcli, new UnitOfMeasurament(unitOfMeasurement), lowerBound, upperBound, 
                                                                             swWear, cyWear, pdWear);
                climaticConditions.add(climaticCondition);
            }
        }

        return climaticConditions;
    }
    
    // Method to obtain one record from climaticCondition table by Id.
    public static ClimaticCondition getConditionByClim(int idCli) throws SQLException {
    	ClimaticCondition condition = null;
        String query = "SELECT * FROM climaticCondition WHERE idCli = ?";

        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) { 
            
            pstmt.setLong(1, idCli);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    condition = new ClimaticCondition(rs.getInt("idCli"),
                        new UnitOfMeasurament(rs.getString("unitOfMeasurament")),
                        rs.getFloat("lowerBound"),
                        rs.getFloat("UpperBound"),
                        rs.getFloat("SwWear"),
                        rs.getFloat("CyWear"),
                        rs.getFloat("PdWear")
                    );	
                }
            }
        }
        return condition;
    }
}