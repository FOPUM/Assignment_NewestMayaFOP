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
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class home_page implements Initializable, ControlledScreen{
    
    private Button exit_button;
    ScreenController myController;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
    }
    
//    public void translateAnimation (double duration, Node node, double width) {
//        TranslateTransition translateTransition = new TranslateTransition (Duration.seconds(duration), node);
//        translateTransition.setByX(width);
//        translateTransition.play();
//    }
//    
//    public void slideshow(){
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
//            translateAnimation(0.5, pane2, 829);
//            translateAnimation(0.5, pane3, 829);
//            translateAnimation(0.5, pane1, 829);
//        }));
//        timeline.setCycleCount(timeline.INDEFINITE);
//        timeline.play();
//    }
    
    
    
    public void slider_animation(Pane pane2, Pane pane3, Pane pane4) {
        FadeTransition fade_transition = new FadeTransition(Duration.seconds(1),pane4);
        fade_transition.setFromValue(1);
        fade_transition.setToValue(0);
        fade_transition.play();
        
        fade_transition.setOnFinished(event -> {
            
            FadeTransition fade_transition1 = new FadeTransition(Duration.seconds(1),pane3);
            fade_transition1.setFromValue(1);
            fade_transition1.setToValue(0);
            fade_transition1.play();
            
                fade_transition1.setOnFinished(event1 -> {

                FadeTransition fade_transition2 = new FadeTransition(Duration.seconds(1),pane2);
                fade_transition2.setFromValue(1);
                fade_transition2.setToValue(0);
                fade_transition2.play();
                
                fade_transition2.setOnFinished(event2 -> {

                    FadeTransition fade_transition3 = new FadeTransition(Duration.seconds(1),pane2);
                    fade_transition3.setToValue(1);
                    fade_transition3.setFromValue(0);
                    fade_transition3.play();
                    
                    fade_transition3.setOnFinished(event3 -> {
                        
                        FadeTransition fade_transition4 = new FadeTransition(Duration.seconds(1),pane3);
                        fade_transition4.setToValue(1);
                        fade_transition4.setFromValue(0);
                        fade_transition4.play();

                        fade_transition4.setOnFinished(event4 -> {

                            FadeTransition fade_transition5 = new FadeTransition(Duration.seconds(1),pane4);
                            fade_transition5.setToValue(1);
                            fade_transition5.setFromValue(0);
                            fade_transition5.play();

                            fade_transition5.setOnFinished(event5 -> {

                                slider_animation(pane2, pane3, pane4);

                            });
                        });
                    });
                });
            });
        });
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
