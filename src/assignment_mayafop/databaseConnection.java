/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Ming
 */
public class databaseConnection {
    public Connection databaseLink;
    
    public Connection getConnection(){
        // Connect to mayadb
        String database_name = "mayadb";
        String database_user = "root";
        String database_password = "";
        String url = "jdbc:mysql://localhost:3306/mayadb";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, database_user, database_password);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        
        return databaseLink;
    }
    
}
