/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 *
 * @author Ming
 */
public class search_module implements Initializable,ControlledScreen{
    ScreenController myController;
    @FXML
    private Button exit_button;
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
    }
    
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void goToHomepage(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.homepageScreen); 
    }
    @FXML
    private void goToSearch(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.searchScreen); 
    }
    @FXML
    private void goToTimetable(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.timetableScreen); 
    }
    
}
