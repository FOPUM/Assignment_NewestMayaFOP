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
public class control_center implements Initializable{
    
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
    
    
    Parent root;
    Stage stage;
    Scene scene;
    static Parent previousroot = null;
    static Stage previousstage = null;
    
   
            
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_page home = new home_page();
        home.slider_animation(pane2, pane3, pane4);
        
    }
    


    
    private static int previous_page = 0;
    public void back_buttononAction(ActionEvent event) throws IOException {
       
    }
    

    public void forward_button(ActionEvent event) throws IOException {
        
    }
    
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 
   
    
    public void home_button_on_action(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
        previous_page = 1;
        
    }
    
    public void search_button_on_action(ActionEvent event) throws IOException {        
        root = FXMLLoader.load(getClass().getResource("search_module.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        previous_page = 2;
        
    }
    
    public void timetable_button_on_action(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("timetable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        previous_page = 3;
    }
    
    public void dashboard_button_on_action(ActionEvent event) throws IOException{         
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        previous_page = 4;
    }
    
    public void user_account_button_on_action(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("user_account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        previous_page = 5;
    }
    
    public void logout_button_on_action(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("verify_logout.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }   
    
    
    
}

