/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import static assignment_mayafop.registerControlStudent.matric_id;
import com.sendemail.SendMail;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    login_controller LoginControl = new login_controller(); 
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
    private static String otp;
    
    
       static String fullname;
       static String staff_id;
       static String siswamail;
       static String password;

    
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
    
    public void register_button_on_action(ActionEvent event) throws SQLException {
            staff_id = staffIDTextField.getText();
            String checkStaffIdExist = "";
            databaseConnection connectNow = new databaseConnection();
            Connection connectDB = connectNow.getConnection();
            PreparedStatement statement = connectDB.prepareStatement("Select * from staff WHERE staff_id = ?");
            statement.setString(1,staff_id);
            System.out.println(staff_id);
            ResultSet query_checkForStudentDuplication = statement.executeQuery();
            //Check the validity of information
            if (query_checkForStudentDuplication.next()) {
                checkStaffIdExist = query_checkForStudentDuplication.getString("staff_id");
                System.out.println(checkStaffIdExist);
            }
        //Check the validity of information
        if (nameTextField.getText() != null && umMailTextField.getText() != null && staffIDTextField.getText() != null && staffIDTextField.getText().length() >= 5) {
            if (!checkStaffIdExist.equalsIgnoreCase(matric_id)) {
                if(passwordField.getText().equals(confirmPasswordField.getText())) {
                fullname = nameTextField.getText();
                staff_id = staffIDTextField.getText();
                siswamail = umMailTextField.getText();
                password = passwordField.getText();
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterSignUpOTP.fxml"));
                    loader.load();
                    enterSignUpOTPController signUpOTPController = loader.getController();
                    signUpOTPController.stopCountThread();
                    signUpOTPController.setAccStatus('T');
                } catch (IOException ex) {
                    Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    otp = otpGenerator();
                    sendOtpToSignUpUser(umMailTextField.getText(), nameTextField.getText(), otp);
                    root = FXMLLoader.load(getClass().getResource("enterSignUpOTP.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(registerControlStaff.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else {
                  message_label.setText("Password does not match");
                }
            }else{
                    message_label.setText("Account already existed. Consider log in?");
                }
            
            
        } else {
            message_label.setText("Please enter correct information!");
        }
    }
    
    public void register_user() {
        //Collect the information at signup 
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();
     
        password = LoginControl.caesarCipherEncrypt(password, 9); // Encrypt the password before storing

        try {
            PreparedStatement statement = connectDB.prepareStatement("INSERT INTO staff (staff_id, staff_email, staff_name, staff_password) VALUES (?,?,?,?)");
        
            statement.setString(1,staff_id);
            statement.setString(2,siswamail);
            statement.setString(3,fullname);
            statement.setString(4,password);
            statement.executeUpdate();
            
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
    
    public String otpGenerator () {
        Random randomNumber = new Random();
        String oneTimePassword = "";

        for (int i = 0; i < 10; i++) {
            int select = randomNumber.nextInt(3);
            switch (select) {
                case 0:
                    oneTimePassword += (char) (randomNumber.nextInt(123-97)+97);
                    break;
                case 1:
                    oneTimePassword += (char) (randomNumber.nextInt(91-65)+65);
                    break;
                case 2:
                    oneTimePassword += randomNumber.nextInt(10);
                    break;
                default:
                    throw new AssertionError();
            }
        }

       return oneTimePassword;
    }
    
    public void sendOtpToSignUpUser(String receiver, String name, String otp ){
         SendMail mailSender = new SendMail();
         String subject = "OTP for renew password - Maya";
         String mailMessage = "Dear  " + name + "\n\nYour otp is : " + otp + ".\nPlease kindly use it to renew your password.\nIf you didn't perform this action, please reply to this email immediately so we can take action.";
         String sender = "mayaFOPUM@gmail.com";
         final String pass = "U2102857@";
         
         mailSender.sendMail(receiver, subject, mailMessage, sender, pass);
         
     }
    
    public String getOtp() {
        return otp;
    }
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
