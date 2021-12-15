/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class verify_logout implements Initializable, ControlledScreen{
    
    @FXML
    private Button back_button;
    
    ScreenController myController;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void no_on_verify_logout_button(ActionEvent event) {
        //Click on back button to exit 
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    public void yes_on_verify_logout_button(ActionEvent event) throws IOException {
        myController.setScreen(Assignment_MayaFOP.loginScreen); 
    }
}
