/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class dashboard implements Initializable, ControlledScreen{
    
    private Button exit_button;
    ScreenController myController;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    

    public void goToLogin(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.loginScreen); 
    }
    
    public void goToHomepage(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.homepageScreen); 
    }

    public void goToSearch(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.searchScreen); 
    }

    public void goToTimetable(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.timetableScreen); 
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 
}
