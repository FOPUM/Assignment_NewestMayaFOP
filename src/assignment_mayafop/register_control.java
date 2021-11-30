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
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class register_control implements Initializable {
    
    @FXML
    private Button back_button;
    @FXML
    private Label registration_successful_label;
    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField confirm_password_field;
    @FXML
    private Label confirm_password_label;
    @FXML
    private TextField username_text_field;
    @FXML
    private TextField matric_id_text_field;
    @FXML
    private TextField siswamail_text_field;
    @FXML
    private TextField phone_no_text_field;  
    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File logoFile = new File("loginImage/Logo.png");
//        Image logoImage =new Image(logoFile.toURI().toString());
//        logoImageView.setImage(logoImage);
    }
    
    public void cancel_button_on_action(ActionEvent event) {
        //Cancel button to back to login page
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    public void register_button_on_action(ActionEvent event) {
        //Check the validity of password
        if(password_field.getText().equals(confirm_password_field.getText())) {
            register_user();
            confirm_password_label.setText("");
            registration_successful_label.setText("User Register Successfully!");
        }else {
            confirm_password_label.setText("Password does not match");
        }
        registration_successful_label.setText("User Register Successfully!");
        
    }
    
    public void register_user() {
        //Collect the information at signup 
        database_connection connectNow = new database_connection();
        Connection connectDB = connectNow.getConnection();
        
        String fullname = username_text_field.getText();
        String matric_id = matric_id_text_field.getText();
        String siswamail = siswamail_text_field.getText();
        String phone_no = phone_no_text_field.getText();
        String password = password_field.getText();
        
        String insert_fields = "INSERT INTO user_account (fullname, matric_id, siswamail, phone_number, password) VALUES ('";
        String insert_values = fullname + "','" + matric_id + "','" + siswamail + "','" + phone_no + "','" + password + "');";
        String insert_to_register = insert_fields + insert_values;
        
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert_to_register);
            registration_successful_label.setText("User Register Successfully! Please go back and sign in.");
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
    }
}
