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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
/**
 *
 * @author Ming
 */
public class login_controller implements Initializable{
    
    @FXML
    private Button exit_button;
    @FXML
    private Label login_message_label;
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField username_text_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button signup_button;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File logoFile = new File("loginImage/Logo.png");
//        Image logoImage =new Image(logoFile.toURI().toString());
//        logoImageView.setImage(logoImage);
    }
    
    public void login_button_on_action(ActionEvent event) {
        //Click on login button
        if(username_text_field.getText().isBlank() == false && password_field.getText().isBlank() == false) {
            validate_login();
        } else {
            login_message_label.setText("Please enter username and password.");
        }
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    
    public void signup_button(ActionEvent event) {
        //Click on signup button 
        Stage stage = (Stage) signup_button.getScene().getWindow();
        create_account_stage();
    }
    
    public void validate_login(){
        //Verify the information match with database ot not
        database_connection connectNow = new database_connection();
        Connection connectDB = connectNow.getConnection();
        
        String verify_login = "SELECT COUNT(1) FROM user_account WHERE matric_id='" + username_text_field.getText() + "' AND password='" + password_field.getText() + "';";
        
        try {
            Statement statement = connectDB.createStatement();
            ResultSet query_result = statement.executeQuery(verify_login);
            
            while(query_result.next()) {
                if(query_result.getInt(1) == 1) {
                    login_message_label.setText("Congratulations!");
                    //create_account_stage();
                }else {
                    login_message_label.setText("Invalid login. Please try again.");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void create_account_stage() {
        //Codes to open signup page
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/assignment_MayaFOP/signup.fxml"));
            Stage register_stage = new Stage();
            register_stage.initStyle(StageStyle.UNDECORATED);
            register_stage.setTitle("Signup");
            register_stage.setScene(new Scene(root));
            register_stage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
}
