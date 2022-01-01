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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ming
 */
public class controlCenter implements Initializable, ControlledScreen{
    Assignment_MayaFOP op;
    
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
    @FXML
    private Button logout_button;
    @FXML
    private Button userAccountButton;
    
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
    
    @FXML
    private StackPane contentArea;
   
            
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        home_page home = new home_page();
//        home.slider_animation(pane2, pane3, pane4);

        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(controlCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void logOut(ActionEvent event){
        myController.setScreen(Assignment_MayaFOP.dashboardScreen); 
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 
    
    public void goToLogin(ActionEvent event)throws IOException{
        swapScreen("login.fxml");
    }
    
    public void goToHomepage(ActionEvent event) throws IOException{
        swapScreen("homePage.fxml");
    }
    
    public void goToSearch(ActionEvent event) throws IOException{
        swapScreen("searchModule.fxml");
    }
    
    public void goToTimetable(ActionEvent event)throws IOException{
        swapScreen("timetable.fxml");
    }
    
    public void goToDashboard(ActionEvent event)throws IOException{
        swapScreen("dashboard.fxml");
    }
    
    public void goToUserAccount(ActionEvent event)throws IOException{
        swapScreen("userAccount.fxml");
    }
    
    public void goToLogout(ActionEvent event)throws IOException{   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Assignment_MayaFOP/logoutConfirmation.fxml"));
        Parent root = loader.load();
        verifyLogout vLcontroller = loader.getController();
        Stage register_stage = new Stage();
        register_stage.initModality(Modality.APPLICATION_MODAL);
        register_stage.initStyle(StageStyle.UNDECORATED);
        register_stage.setScene(new Scene(root));
        register_stage.showAndWait();
        
        if (vLcontroller.pp) {
            myController.setScreen(Assignment_MayaFOP.loginScreen);
            vLcontroller.setBoolean(false);
        }
        
    }
    
    public void swapScreen(String fxmlfile)throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource(fxmlfile));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    
    
    
    
}

