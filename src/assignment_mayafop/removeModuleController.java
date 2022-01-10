/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class removeModuleController implements Initializable{
    @FXML
    private Button yes_button;
    
    @FXML
    private Button no_button;
    
    ScreenController myController;
    searchModule SMControl;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/searchModule.fxml"));
            loader.load();
            SMControl= loader.getController();
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
        SMControl.setConfirmeddelete(true);
        System.out.println("After set: " + SMControl.getisConfirmedtake());
        Stage stage = (Stage) yes_button.getScene().getWindow();
        stage.close();     

        
    }
}
