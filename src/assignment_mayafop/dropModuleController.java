/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class dropModuleController implements Initializable{
    @FXML
    private Button yes_button;
    
    @FXML
    private Button no_button;
    
    ScreenController myController;
    registeredModuleController RMController;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredModule.fxml"));
            loader.load();
            RMController= loader.getController();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void noButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) no_button.getScene().getWindow();
        stage.close();
    } 
    
    public void yesButton(ActionEvent event){
        RMController.setConfirmedDrop(true);
        Stage stage = (Stage) yes_button.getScene().getWindow();
        stage.close();     

        
    }
    
    
}
