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
public class control_center implements Initializable{
    
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
//    @FXML
//    private Button register_module_button;
    @FXML
    private Button exit_button;
    
    Parent root;
    Stage stage;
    Scene scene;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    
    public void home_button_on_action(ActionEvent event) {
        try {          
            root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void search_button_on_action(ActionEvent event) {
        try {          
            root = FXMLLoader.load(getClass().getResource("search_module.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            scene = new Scene(root);
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
            root = FXMLLoader.load(getClass().getResource("timetable.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void dashboard_button_on_action(ActionEvent event) {
        try {          
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void user_account_button_on_action(ActionEvent event) {
        try {          
            root = FXMLLoader.load(getClass().getResource("user_account.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
           
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void logout_button_on_action(ActionEvent event) {
        //Codes to open home page
        try {
            root = FXMLLoader.load(getClass().getResource("/assignment_MayaFOP/verify_logout.fxml"));
            stage = new Stage();
            scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }    
}
