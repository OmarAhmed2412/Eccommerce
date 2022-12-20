/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blu-Ray
 */
public class MainViewModel {
    public void createAConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            System.out.println("Successful Connection");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
