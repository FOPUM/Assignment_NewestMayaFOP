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
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    
    Stage home_stage;
    Scene home_scene;
    Parent home_root;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File logoFile = new File("loginImage/Logo.png");
//        Image logoImage =new Image(logoFile.toURI().toString());
//        logoImageView.setImage(logoImage);
    }
    int validated = 0;
    public void login_button_on_action(ActionEvent event) throws IOException {
        //Click on login button
        if(username_text_field.getText().isBlank() == false && password_field.getText().isBlank() == false) {
            validate_login();
        } else {
            login_message_label.setText("Please enter username and password.");
        }
        //Create home page
        
        if(validated == 1){
            home_root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
            home_stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
            home_scene = new Scene(home_root);
            home_stage.setScene(home_scene);
            home_stage.centerOnScreen();
            home_stage.show();
        }
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        
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
                    //login_message_label.setText("Congratulations!");
                    validated = 1;
                }else {
                    login_message_label.setText("Invalid login. Please try again.");
                }
                
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void sign_up_button_on_action(ActionEvent event) {
        //Codes to open signup page
        try {          
            Parent root = FXMLLoader.load(getClass().getResource("/assignment_MayaFOP/signup_student.fxml"));
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
    
//    public void create_home_page(int closex) {
//        //Codes to open home page
//        try {   
//            Assignment_MayaFOP maya = new Assignment_MayaFOP();
//            Parent root = FXMLLoader.load(getClass().getResource("/assignment_MayaFOP/homepage.fxml"));
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("Home Page");
//            stage.setScene(new Scene(root));
//            
//            if(closex == 0) {
//                stage.show();
//            }else if(closex == 1){
//                stage.close();
//            }
//            
//            
//            
//        } catch(Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }
//    }
    
    
}
