/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import com.sendemail.SendMail;
import java.io.EOFException;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author Ming
 */
public class login_controller implements Initializable,ControlledScreen{
    ScreenController myController;
    SendMail mailSender = new SendMail();
    @FXML
    private Button exit_button;
    @FXML
    private Button login_button;
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
    @FXML
    private BorderPane loginScreen;
    
    Stage home_stage;
    Scene home_scene;
    Parent home_root;
    
    private static String username;
    private static char accStatus;
    private static int loginAttempt;
    private File loginAttemptFile = new File("loginAttempt.dat");

    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String otp = otpGenerator();  //testing ******************************************************************************************************
//        sendOtpToForgotter("kwanyang29@gmail.com","Kwan Yang", otp); // testing **********************************************************************
        username_text_field.setText("u2102857");
        password_field.setText("U2102857");
        if (loginAttemptFile.exists()) {
            try {
            
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(loginAttemptFile));
            while(true){
                loginAttempt = in.readInt();
                loginAttempt = 0;
                System.out.println("Attempts: " + loginAttempt);
                in.close();
            }
        }   catch(EOFException ex){
            ex.getLocalizedMessage();
                System.out.println(ex);
        }
            catch(FileNotFoundException ex){
            Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch (IOException ex) {
            Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                loginAttemptFile.createNewFile();
                ObjectOutputStream oFile = new ObjectOutputStream(new FileOutputStream(loginAttemptFile));
                oFile.writeInt(0);
                oFile.close();
            } catch (IOException ex1) {
                Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
        password_field.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                try {
                    try {
                        checkConditionForLogin();
                    } catch (IOException ex) {
                        Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    int validated = 0; 
    public void login_button_on_action(ActionEvent event) throws IOException, SQLException {
        checkConditionForLogin();
    }
    
    public void checkConditionForLogin() throws SQLException, IOException{
        //Click on login button
        loginAttempt++;
        if (loginAttempt > 4) {
            login_message_label.setText("You have tried too many times.\nPlease wait for 1 minute");
            //Set disable timer
            new Thread() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            login_button.setDisable(true); // disable the button
                        }
                    });
                    try {
                        Thread.sleep(60000); //wait 1 minute
                    }
                    catch(InterruptedException ex) {
                    }
                    Platform.runLater(new Runnable() {
                        public void run() {
                            login_button.setDisable(false); //enable the button
                            loginAttempt = 4;
                            login_message_label.setText("You have " + (5-loginAttempt) +" chances left");
                        }
                    });
                }
            }.start();
            //
        }else{
            if(username_text_field.getText().isEmpty() == false && password_field.getText().isEmpty() == false) {
                validate_login();
            } else {
                UpdateloginAttemptFile();
                login_message_label.setText("Please enter username and password.\nYou have " + (5-loginAttempt) +" chances left");
            }
            //Create home page
            if(validated == 1){
                myController.loadScreen(Assignment_MayaFOP.controlCenter, Assignment_MayaFOP.navigationFile);
                myController.setScreen(Assignment_MayaFOP.controlCenter);
                validated =0;
                loginAttempt=0;
                loginAttemptFile.delete();
    //            myController.unloadScreen(Assignment_MayaFOP.loginScreen);
            }
        }   
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        
    }
    

    public void validate_login() throws SQLException, IOException{
        login_message_label.setText("");
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement = connectDB.createStatement();
        ResultSet query_result;
        int encryptShift = 9;
        String verify_login_studentInfo = "SELECT * FROM student WHERE matric_num='" + username_text_field.getText()+"'";;
        String verify_login_staffInfo = "SELECT * FROM staff WHERE staff_id='" + username_text_field.getText()+"'";;
        String verify_login_admin = "SELECT * FROM admin WHERE staff_id='" + username_text_field.getText() + "'";
        if (username_text_field.getText().toLowerCase().startsWith("a")) {//Staff validation
            query_result = statement.executeQuery(verify_login_admin);
            if (query_result.next()) {
                query_result = statement.executeQuery(verify_login_staffInfo);
                verifyAccountWithDatabase(query_result,encryptShift, 'A');
            }
            else{
                query_result = statement.executeQuery(verify_login_staffInfo);
                verifyAccountWithDatabase(query_result,encryptShift, 'T');
            }
        }//Staff validation
        else{//Student Validation
            query_result = statement.executeQuery(verify_login_studentInfo);
            verifyAccountWithDatabase(query_result,encryptShift, 'S');
        }//Student Validation

    }
    
    public void sign_up_button_on_action(ActionEvent event) {
        //Codes to open signup page
        try {
            myController.showPopupStage(loginScreen, "/assignment_MayaFOP/signupStudent.fxml" );
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static char getAccStatus() {
        return accStatus;
    }

    public static void setAccStatus(char accStatus) {
        login_controller.accStatus = accStatus;
    }
    
    public void verifyAccountWithDatabase(ResultSet queryResult, int encryptShift, char accType) throws SQLException, IOException{
        
        String validatePassword = "";
                if(queryResult.next()){
//Account exits, down here is password validation
                    if (accType == 'A' || accType == 'T') {
                        validatePassword = queryResult.getString("staff_password");
                    }
                    else if(accType == 'S'){
                        validatePassword = queryResult.getString("student_password");
                    }
                    
                    validatePassword = caesarCipherDecrypt(validatePassword,encryptShift);
                    if (validatePassword.equals(password_field.getText()) ) {
                        validated = 1;
                        username = username_text_field.getText();
                        switch (accType) {
                            case 'A':
                                accStatus = 'A';
                                break;
                            case 'T':
                                accStatus = 'T';
                                break;
                            case 'S':
                                accStatus = 'S';
                                break;
                            default:
                                break;
                        }
                        login_message_label.setText("");
                    }
                    else{ // Password is wrong
                        login_message_label.setText("Wrong Password.\nDid you forget your password?\nYou have " + (5-loginAttempt) +" chances left");
                        UpdateloginAttemptFile();
                    }
//Password validation                    
                }
                else{ //User not Exist
                    login_message_label.setText("User not exist. Please sign up.");
                }
    }
    
    public String caesarCipherEncrypt(String plainPassword, int shift) {
        String result = "";
 
        for (int i = 0; i < plainPassword.length(); i++) {
            if (Character.isDigit(plainPassword.charAt(i))) {
                char ch = (char) (((int) plainPassword.charAt(i) + shift - 48) % 10 +48);
                result += ch;
            }
            else if (Character.isUpperCase(plainPassword.charAt(i))) {
                char ch = (char) (((int) plainPassword.charAt(i) +
                        shift - 65) % 26 + 65);
                result += ch;
            } else if(Character.isLowerCase(plainPassword.charAt(i))){
                char ch = (char) (((int) plainPassword.charAt(i) +
                        shift - 97) % 26 + 97);
                result += ch;
            }
        }
        return result;
    }
   
     public String caesarCipherDecrypt(String encryptedPassword, int shift) {
        String result = "";
        int encryptShift = shift;
        for (int i = 0; i < encryptedPassword.length(); i++) {
            shift = encryptShift;
            if (Character.isDigit(encryptedPassword.charAt(i))) {
                shift = 10 -shift;
                if (shift <= 0) {
                    shift += 10;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 48) % 10 +48);
                result += ch;
            }
            else if (Character.isUpperCase(encryptedPassword.charAt(i))) {
                shift = 26 - shift;
                if (shift<=0) {
                     shift+=26;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 65) % 26 + 65);
                result += ch;
            } else if(Character.isLowerCase(encryptedPassword.charAt(i))){
                shift = 26 - shift;
                if (shift<=0) {
                     shift+=26;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 97) % 26 + 97);
                result += ch;
            }
        }
        return result;
    }
     
     public void UpdateloginAttemptFile() throws IOException{
         ObjectOutputStream oFile = null;
         try {
                    
                    loginAttemptFile.delete();
                    loginAttemptFile.createNewFile();
                    oFile = new ObjectOutputStream(new FileOutputStream(loginAttemptFile));
                    oFile.writeInt(loginAttempt);
                    oFile.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        oFile.close();
                    } catch (IOException ex) {
                        Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
     }
     
     
     public void forgotPasswordClicked(ActionEvent event){
        try {
            myController.showPopupStage(loginScreen, "/assignment_MayaFOP/enterEmailPage.fxml" );
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
     }
     
     
    
}
