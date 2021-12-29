/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class registerControlStaff implements Initializable,ControlledScreen{
    ScreenController myController;
    animation Animation;
    
    @FXML
    private Label message_label;
    @FXML
    private Button back_button;
    @FXML
    private BorderPane signupScreenStaff;
    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField staffIDTextField;
    @FXML
    private TextField umMailTextField;
    @FXML
    private PasswordField passwordField;
    
    boolean upScreenStatus = false;
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(signupScreenStaff);
        }
    }
    
    public void cancel_button_on_action(ActionEvent event) {
        //Cancel button to back to login page
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    public void register_button_on_action(ActionEvent event) {
        //Check the validity of information
        if (nameTextField.getText() != null && umMailTextField.getText() != null && staffIDTextField.getText() != null && staffIDTextField.getText().length() >= 5) {
            
            if(passwordField.getText().equals(confirmPasswordField.getText())) {
            register_user();
            //message_label.setText("");
            message_label.setText("User register successfully. Please go back and sign in.");
            }else {
            message_label.setText("Password does not match");
            }
            
        } else {
            message_label.setText("Please enter correct information!");
        }
    }
    
    public void register_user() {
        //Collect the information at signup 
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();
     
        String fullname = nameTextField.getText();
        String staff_id = staffIDTextField.getText();
        String siswamail = umMailTextField.getText();
        String password = passwordField.getText();

        try {
            PreparedStatement statement = connectDB.prepareStatement("INSERT INTO staff (staff_id, staff_email, staff_name, staff_password) VALUES (?,?,?,?)");
        
            statement.setString(1,staff_id);
            statement.setString(2,siswamail);
            statement.setString(3,fullname);
            statement.setString(4,password);
            statement.executeUpdate();
            message_label.setText("User Register Successfully! Please go back and sign in.");
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switch_to_student(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        myController.setScreen(Assignment_MayaFOP.userStaffScreen);
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
