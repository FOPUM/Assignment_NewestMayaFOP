/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

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
    private TextField matricidTextField;

    @FXML
    private Button next1Button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private static String otptext;
    private static char accStatus;
    public void next1ButtonClicked(ActionEvent event) {      
        siswamail = matricidTextField.getText();
        
        //If user enter matric_num instead of siswamail
        try {
            //query for student
            if(siswamail.length() == 8){
                String queryForMail = "SELECT siswamail FROM student WHERE matric_num='"+siswamail+"'";
                ResultSet mailQueryOutput = connectDB.createStatement().executeQuery(queryForMail);
                while (mailQueryOutput.next()) {
                    siswamail = mailQueryOutput.getString("siswamail");
                }
                accStatus = 'S';
                
            //query for staff
            }else if(siswamail.length() == 5){
                String queryForMail = "SELECT staff_email FROM staff WHERE staff_id='"+siswamail+"'";           
                ResultSet mailQueryOutput = connectDB.createStatement().executeQuery(queryForMail);
                while (mailQueryOutput.next()) {
                    siswamail = mailQueryOutput.getString("staff_email");
                }
                accStatus = 'T';
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(enterEmailPageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //Send email function here
        otptext = otpGenerator();
        System.out.println("OTPText is " + otptext);
        
        
        
        
        
        
        
        
        
        //update database with temporary OTP
        try {
            PreparedStatement updateOTPIntoDatabase = connectDB.prepareStatement("UPDATE student SET student_password=? WHERE siswamail=?");
            updateOTPIntoDatabase.setString(1, String.valueOf(otptext));
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

    public String getOtptext() {
        return otptext;
    }

    public String getSiswamail() {
        return siswamail;
    }

    public static char getAccStatus() {
        return accStatus;
    }

}
