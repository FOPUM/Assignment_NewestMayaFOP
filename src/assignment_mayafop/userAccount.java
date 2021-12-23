/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class userAccount implements Initializable, ControlledScreen{
    
    ScreenController myController;
    @FXML
    private Button exit_button;
    @FXML
    private BorderPane userScreen;
    boolean showing;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
    }

    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
//    public void goToRegisteredModule(ActionEvent event)throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("registeredModule.fxml"));
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
    
    public void goToRegisteredModule(ActionEvent event){
        myController = new ScreenController();
        myController.showPopupStage(userScreen, "/assignment_MayaFOP/registeredModule.fxml");
    } 

    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
}
