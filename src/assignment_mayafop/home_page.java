/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

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
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class home_page implements Initializable{
    
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
    @FXML
    private Button register_module_button;
    @FXML
    private Button exit_button;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    
    
    
    public void search_button_on_action(ActionEvent event) {
        try {          
            Parent root = FXMLLoader.load(getClass().getResource("search_module.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void home_button_on_action(ActionEvent event) {
        try {          
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void timetable_button_on_action(ActionEvent event) {
        try {          
            Parent root = FXMLLoader.load(getClass().getResource("search_module.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void logout_button_on_action(ActionEvent event) throws IOException { 
        verify_logout();  
    }
    
    public void verify_logout() {
        //Codes to open home page
        try {
            Assignment_MayaFOP maya = new Assignment_MayaFOP();
            
            Parent root = FXMLLoader.load(getClass().getResource("/assignment_MayaFOP/verify_logout.fxml"));
            Stage stage = new Stage();
            
            stage.initStyle(StageStyle.TRANSPARENT);
                      
            
            stage.setTitle("Home Page");
            stage.setScene(new Scene(root));
            stage.show();
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    
    
    public void no_on_verify_logout_button(ActionEvent event) {
        //Click on back button to exit 
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    //idk dy
    public void yes_on_verify_logout_button(ActionEvent event) throws IOException {
        Platform.exit();
        System.exit(0);
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
   
}
