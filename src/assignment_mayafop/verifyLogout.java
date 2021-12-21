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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class verifyLogout implements Initializable, ControlledScreen{
    @FXML
    private Button no_button;
    
    @FXML
    private Button yes_button;
    
    ScreenController myController;
    Stage primaryStage;
    boolean pp = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
    public void setBoolean(boolean boo){
        pp = boo;
    }
    
    public  boolean getBoolean(boolean boo){
        return pp;
    }
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void button_handler(ActionEvent event) {
        if (event.getSource()== no_button) {
            Stage stage = (Stage) no_button.getScene().getWindow();
            stage.close();
        }
        if (event.getSource()== yes_button) {
            Stage stage = (Stage) yes_button.getScene().getWindow();
            stage.close();
            setBoolean(true);
//            myController.setScreen(Assignment_MayaFOP.loginScreen);
        }
        //Click on back button to exit 
        
    }
    
//    public void yes_on_verify_logout_button(ActionEvent event) throws IOException {
        
//            Stage stage = (Stage) yes_button.getScene().getWindow();
//            stage.close();
//            myController.setScreen(Assignment_MayaFOP.loginScreen);
//            setBoolean(true);
//            try {
//                myController.setScreen(Assignment_MayaFOP.loginScreen);
//
//                } catch (Exception e) {
//                    System.out.println(e);
//                System.out.println("Underlying exception: " + e.getMessage());  
//                }
//            try {
//                fdsa.goToLogin(event);
//            
//        } catch (Exception e) {
//                System.out.println(e);
//        }
//        return true;
//        Group root = new Group();
//        root.getChildren().addAll(myController);
//        Scene scene = new Scene(root);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setScene(scene);
//        primaryStage.show(); 
        
    }

