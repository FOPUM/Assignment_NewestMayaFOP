/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class verify_logout {
    
    @FXML
    private Button back_button;
    
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
