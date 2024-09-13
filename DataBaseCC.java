package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCC {
	
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Base de Datos";
    private static final String username = "postgres";
    private static final String password = "Ndfe02010926";

    public static Connection getConnection() throws SQLException{
    	 try {
 			Class.forName("org.postgresql.Driver");
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 		}
         return DriverManager.getConnection(jdbcUrl, username, password);
    } 
    
}
