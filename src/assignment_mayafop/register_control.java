/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class register_control implements Initializable,ControlledScreen {
    ScreenController myController;
    @FXML
    private Button back_button;
    @FXML
    private Button register_button;
    @FXML 
    private Label state_label;
    
    //for student
    @FXML
    private TextField fullname_textfield;
    @FXML
    private TextField matric_id_text_field;
    @FXML
    private TextField siswamail_text_field;
    @FXML
    private TextField programme_text_field;  
    @FXML
    private TextField muet_band_text_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField confirm_password_field;
    @FXML
    private Label message_label;
    
    
    @FXML
    private TextField username_text_field;
    
    
    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
//      
    }
    
    
    public void cancel_button_on_action(ActionEvent event) {
        //Cancel button to back to login page

        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    public void register_button_on_action(ActionEvent event) {
        //Check the validity of information
        if (fullname_textfield.getText() != null && is_numeric(muet_band_text_field.getText())) {
            
            if(password_field.getText().equals(confirm_password_field.getText())) {
            register_user();
            //message_label.setText("");
            message_label.setText("User register successfully. Please go back and sign in.");
            }else {
            message_label.setText("Password does not match");
            }
            
        } else {
            message_label.setText("Please enter information!");
        }
    }
    
    public void register_user() {
        //Collect the information at signup 
//        database_connection connectNow = new database_connection();
//        Connection connectDB = connectNow.getConnection();
//        
//        String fullname = username_text_field.getText();
//        String matric_id = matric_id_text_field.getText();
//        String siswamail = siswamail_text_field.getText();
//        String phone_no = phone_no_text_field.getText();
//        String password = password_field.getText();
//        
//        String insert_fields = "INSERT INTO user_account (fullname, matric_id, siswamail, phone_number, password) VALUES ('";
//        String insert_values = fullname + "','" + matric_id + "','" + siswamail + "','" + phone_no + "','" + password + "');";
//        String insert_to_register = insert_fields + insert_values;
//        
//        try {
//            Statement statement = connectDB.createStatement();
//            statement.executeUpdate(insert_to_register);
//            registration_successful_label.setText("User Register Successfully! Please go back and sign in.");
//            
//        } catch(Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }
        
    }
    public static boolean is_numeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }

    
    //Below handles the switching between staff and student signup page
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switch_to_staff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupStaff.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switch_to_student(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
