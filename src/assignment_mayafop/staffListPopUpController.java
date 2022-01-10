/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class staffListPopUpController extends userAccount implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    login_controller loginControl = new login_controller();
    
    @FXML
    private Button exit_button;
    @FXML
    private VBox vContainerStaff;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    @FXML
    private ScrollPane staffPane;
    
    boolean upScreenStatus = false;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(staffPane);
        }
        String staffQueryText="SELECT staff_id AS staffID, staff_email AS umMail, staff_name AS staffName FROM staff LIMIT 7";
        super.getStaffs(staffQueryText);
        super.insertStaffs("/Assignment_MayaFOP/staffListPopUpText.fxml");
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 

}
