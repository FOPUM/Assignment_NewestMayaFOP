/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import com.sendemail.SendMail;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class enterEmailPageController implements Initializable, ControlledScreen{
    
    ScreenController myController;
    animation Animation;
    
    boolean upScreenStatus = false;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    @FXML
    private AnchorPane enterEmailPage;

    @FXML
    private Button exit_button;

    @FXML
    private TextField idOrEmailTextField;

    @FXML
    private Button next1Button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isStaff = true;
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(enterEmailPage);
        }
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
    
    private static String siswamail;
    private static String matricnumOrStaffId;
    private static String forgotterName;
    private static String otpText;
    private static char accStatus;
    private static boolean isStaff = true;
    public void next1ButtonClicked(ActionEvent event) {   

        siswamail = idOrEmailTextField.getText();
        matricnumOrStaffId = idOrEmailTextField.getText();;
        
        //If user enter matric_num instead of siswamail
        try {
            //query for student
            if(!idOrEmailTextField.getText().isEmpty()){
                String queryForMail = "SELECT siswamail, student_name FROM student WHERE siswamail='"+siswamail+"' OR matric_num = '"+  matricnumOrStaffId + "';";
                ResultSet mailQueryOutput = connectDB.createStatement().executeQuery(queryForMail);
                if (mailQueryOutput.next()) {
                    siswamail = mailQueryOutput.getString("siswamail");
                    forgotterName = mailQueryOutput.getString("student_name");
                    otpText = otpGenerator();
                    sendOtpToForgotter(siswamail,forgotterName, otpText);
                    System.out.println("OTPText is " + otpText);
                    accStatus = 'S';
                }else if (isStaff) {
                     queryForMail = "SELECT staff_email, staff_name FROM staff WHERE staff_email='"+siswamail+"' OR staff_id = '"+  matricnumOrStaffId + "';";         
                     mailQueryOutput = connectDB.createStatement().executeQuery(queryForMail);
                    if (mailQueryOutput.next()) {
                        siswamail = mailQueryOutput.getString("staff_email");
                        otpText = otpGenerator();
                        sendOtpToForgotter(siswamail,forgotterName, otpText);
                        System.out.println("OTPText is " + otpText);
                        accStatus = 'T';
                    }else if (true) {
                        //set error label *******************************************************************************************************
                    }
                }
                
            //query for staff
            }else{
                //set error label****************************************************************************************************************
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(enterEmailPageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //Send email function here
        
        
        
        
        
        
        
        
        
        
        //update database with temporary OTP
        try {
            PreparedStatement updateOTPIntoDatabase = connectDB.prepareStatement("UPDATE student SET student_password=? WHERE siswamail=?");
            updateOTPIntoDatabase.setString(1, String.valueOf(otpText));
            updateOTPIntoDatabase.setString(2, siswamail);
            System.out.println(updateOTPIntoDatabase);
            updateOTPIntoDatabase.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(enterEmailPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Go to next page
        try {
            root = FXMLLoader.load(getClass().getResource("/Assignment_MayaFOP/enterOTPPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    public void sendOtpToForgotter(String receiver, String name, String otp ){
         SendMail mailSender = new SendMail();
         String subject = "OTP for renew password - Maya";
         String mailMessage = "Dear  " + name + "\n\nYour otp is : " + otp + ".\nPlease kindly use it to renew your password.\nIf you didn't perform this action, please reply to this email immediately so we can take action.";
         String sender = "mayaFOPUM@gmail.com";
         final String pass = "U2102857@";
         
         mailSender.sendMail(receiver, subject, mailMessage, sender, pass);
         
     }

    public String getOtpText() {
        return otpText;
    }

    public String getSiswamail() {
        return siswamail;
    }

    public static char getAccStatus() {
        return accStatus;
    }

}
