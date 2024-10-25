package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Java Data Base conection Class. 
public class DataBaseCC {
	
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/climaticCondition";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() throws SQLException{
    	 try {
 			Class.forName("org.postgresql.Driver");
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 		}
         return DriverManager.getConnection(jdbcUrl, username, password);
    } 
    
}
