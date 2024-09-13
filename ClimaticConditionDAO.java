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
	
	// Método para insertar un nuevo registro de mascota en la base de datos.
	public static void insertClimaticCondition(int i, String description, String unitOfMeasurament, float lowerBound, float UpperBound, float SwWear, float CyWear,  float PdWear) throws SQLException {
        String sql = "INSERT INTO climaticCondition (description, unitOfMeasurament, lowerBound, UpperBound, SwWear, CyWear, PdWear) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, description);
            pstmt.setString(2, unitOfMeasurament);
            pstmt.setFloat(3, lowerBound);
            pstmt.setFloat(4, UpperBound);
            pstmt.setFloat(5, SwWear);
            pstmt.setFloat(6, CyWear);
            pstmt.setFloat(7, PdWear);
            pstmt.executeUpdate();
        }
    }
	
	// Método para actualizar un registro existente en la tabla climaticCondition.
	public static void updateClimaticCondition(ClimaticCondition clima) throws SQLException {
	    String sql = "UPDATE climaticCondition SET description = ?, unitOfMeasurament = ?, lowerBound = ?, UpperBound = ?, SwWear = ?, CyWear = ?, PdWear = ? WHERE idcli = ?";

	    try (Connection conn = DataBaseCC.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, clima.getDescrption());
	        pstmt.setString(2, clima.getUnitOfMeasurament().getDescription());
	        pstmt.setFloat(3, clima.getLowerBound());
	        pstmt.setFloat(4, clima.getUpperBound());
	        pstmt.setFloat(5, clima.getSwWear());
	        pstmt.setFloat(6, clima.getCyWear());
	        pstmt.setFloat(7, clima.getPdWear());
	        pstmt.setInt(8, clima.getIdCli()); 

	        pstmt.executeUpdate();
	    } catch (NullPointerException e) {
	        JOptionPane.showMessageDialog(null, "Please enter valid numeric or character values for bounds.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

    
    // Método para eliminar un registro en la tabla climaticCondition por su ID.
    public static void deleteClimaticCondition(String descrp) throws SQLException {
        String sql = "DELETE FROM climaticCondition WHERE description = ?";

        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descrp);
            pstmt.executeUpdate();
        }
    }
    
    // Método para obtener todos los registros de la tabla climaticCondition.
    public static List<ClimaticCondition> getAllClimaticConditions() throws SQLException {
        String sql = "SELECT idcli, description, unitOfMeasurament, lowerBound, UpperBound, SwWear, CyWear, PdWear FROM climaticCondition";
        List<ClimaticCondition> climaticConditions = new ArrayList<>();

        try (Connection conn = DataBaseCC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	int idcli = rs.getInt("idcli");
            	
                String description = rs.getString("description");
                String unitOfMeasurement = rs.getString("unitOfMeasurament");
                float lowerBound = rs.getFloat("lowerBound");
                float upperBound = rs.getFloat("UpperBound");
                float swWear = rs.getFloat("SwWear");
                float cyWear = rs.getFloat("CyWear");
                float pdWear = rs.getFloat("PdWear");

                ClimaticCondition climaticCondition = new ClimaticCondition (idcli ,description, new UnitOfMeasurament(unitOfMeasurement), lowerBound, upperBound, 
                                                                             swWear, cyWear, pdWear);
                climaticConditions.add(climaticCondition);
            }
        }

        return climaticConditions;
    }
    
    public static ClimaticCondition getConditionByDescription(String description) throws SQLException {
    	ClimaticCondition condition = null;
        String query = "SELECT * FROM climaticCondition WHERE description = ?";

        try (Connection conn = DataBaseCC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) { 
            
            pstmt.setString(1, description);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    condition = new ClimaticCondition(rs.getInt("idCli"),
                        rs.getString("description"),
                        new UnitOfMeasurament(rs.getString("unitOfMeasurament")),
                        rs.getFloat("lowerBound"),
                        rs.getFloat("UpperBound"),
                        rs.getFloat("SwWear"),
                        rs.getFloat("CyWear"),
                        rs.getFloat("PdWear")
                    );	
                    System.out.println(rs.getInt("idCli"));
                }
            }
        }
        return condition;
    }

    // Método para mostrar todos los registros de la tabla climaticCondition en la consola.
    public static void  displayAllClimaticConditions() throws SQLException {
        String sql = "SELECT idCli, description, unitOfMeasurament, lowerBound, UpperBound, SwWear, CyWear, PdWear FROM climaticCondition";

        try (Connection conn = DataBaseCC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long idCli = rs.getLong("idCli");
                String description = rs.getString("description");
                String unitOfMeasurement = rs.getString("unitOfMeasurament");
                int lowerBound = rs.getInt("lowerBound");
                int upperBound = rs.getInt("UpperBound");
                int swWear = rs.getInt("SwWear");
                int cyWear = rs.getInt("CyWear");
                int pdWear = rs.getInt("PdWear");

                System.out.println("ID: " + idCli + ", Description: " + description + ", Unit of Measurement: " + unitOfMeasurement + 
                                   ", Lower Bound: " + lowerBound + ", Upper Bound: " + upperBound + 
                                   ", Swimming Wear: " + swWear + ", Cycling Wear: " + cyWear +
                                   ", Pedestrian Wear: " + pdWear);
            }
            };
     }
}