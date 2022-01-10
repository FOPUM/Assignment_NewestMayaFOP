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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class homePage implements Initializable, ControlledScreen{
    
    private Button exit_button;
    ScreenController myController;

    @FXML
    private BorderPane homeScreen;
    boolean showing;
    
    @FXML
    private Pane image2;
    @FXML
    private Pane image3;
    @FXML
    private Pane image4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider_animation(image2, image3, image4);
        myController = new ScreenController();
        showing = myController.getShowing();
   
    }

    public void slider_animation(Pane pane2, Pane pane3, Pane pane4) {
        int duration = 5;
        FadeTransition fade_transition = new FadeTransition(Duration.seconds(duration),pane4);
        fade_transition.setFromValue(1);
        fade_transition.setToValue(0);
        fade_transition.play();
        
        fade_transition.setOnFinished(event -> {
            
            FadeTransition fade_transition1 = new FadeTransition(Duration.seconds(duration),pane3);
            fade_transition1.setFromValue(1);
            fade_transition1.setToValue(0);
            fade_transition1.play();
            
                fade_transition1.setOnFinished(event1 -> {

                FadeTransition fade_transition2 = new FadeTransition(Duration.seconds(duration),pane2);
                fade_transition2.setFromValue(1);
                fade_transition2.setToValue(0);
                fade_transition2.play();
                
                fade_transition2.setOnFinished(event2 -> {

                    FadeTransition fade_transition3 = new FadeTransition(Duration.seconds(duration),pane2);
                    fade_transition3.setToValue(1);
                    fade_transition3.setFromValue(0);
                    fade_transition3.play();
                    
                    fade_transition3.setOnFinished(event3 -> {
                        
                        FadeTransition fade_transition4 = new FadeTransition(Duration.seconds(duration),pane3);
                        fade_transition4.setToValue(1);
                        fade_transition4.setFromValue(0);
                        fade_transition4.play();

                        fade_transition4.setOnFinished(event4 -> {

                            FadeTransition fade_transition5 = new FadeTransition(Duration.seconds(duration),pane4);
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
    
    public void goToAnnouncement(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(homeScreen, "/assignment_MayaFOP/announcement.fxml");
            showing = myController.getShowing();
        }
    } 
}
