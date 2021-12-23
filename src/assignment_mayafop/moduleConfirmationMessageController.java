/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class moduleConfirmationMessageController implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    
    boolean upScreenStatus = false;
    
    @FXML
    private AnchorPane moduleConfirmationScreen;
    @FXML
    private Button confirmButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(moduleConfirmationScreen);
        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void confirmModule(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
