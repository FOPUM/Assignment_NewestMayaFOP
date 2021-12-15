/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ming
 */
public class control_center implements Initializable, ControlledScreen{
    
    @FXML
    private Button exit_button;
    @FXML
    private Button back_button;
    @FXML
    private Button forward_button;
    @FXML
    private Button home_button;
    @FXML
    private Button search_button;
    @FXML
    private Button timetable_button;
    @FXML
    private Button dashboard_button;
    
    @FXML Rectangle rectmaya;
    
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    
    ScreenController myController;
   
            
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_page home = new home_page();
        home.slider_animation(pane2, pane3, pane4);
        
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
    
    public void goToDashboard(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.dashboardScreen); 
    }
    
    public void goToUserAccount(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.userStudentScreen); 
    }
    
    public void logOut(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.dashboardScreen); 
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 


    
    
    
    
}

