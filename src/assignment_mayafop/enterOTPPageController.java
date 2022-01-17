/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class enterOTPPageController implements Initializable, ControlledScreen{
    
    ScreenController myController;
    animation Animation;
    Random r = new Random();
    
    boolean upScreenStatus = false;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    @FXML
    private TextField OTPTextField;

    @FXML
    private Button back_button2;
    
    @FXML
    private Button exit_button;
    
    @FXML
    private Button resendOTPButton;
    
    @FXML
    private AnchorPane enterOTPPage;

    @FXML
    private Button next2Button;
    
    @FXML Label OTPLabel;
    
    String otptext;
    static int otpTimer;
    static Thread count;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resendOTPButton.setDisable(true);
        next2Button.setDisable(false);
        otpTimer = 45;
        int otpLimit = otpTimer;
        count = new Thread() {
                public void run() {
                    for(int i = 0 ; i<= otpLimit ; i++){
                        Platform.runLater(new Runnable() {
                            public void run() {
                                OTPLabel.setText("OTP time left: " + otpTimer);
                                if (otpTimer == 0) {
                                    next2Button.setDisable(true);
                                    OTPLabel.setText("Timeout! Please request for a new OTP.");
                                    resendOTPButton.setDisable(false);
                                }
                                
                            }
                        });
                        try {
                            Thread.sleep(1000); //wait 1 second then decrement the timer
                        }
                        catch(InterruptedException ex) {
                        }
                        Platform.runLater(new Runnable() {
                            public void run() {
                                otpTimer --;
                            }
                        });
                    }
                }
            };
        count.start();
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(enterOTPPage);
        }
        
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterEmailPage.fxml"));
            loader.load();
            enterEmailPageController emailController = loader.getController();
            otptext = emailController.getOtpText();
        } catch (IOException ex) {
            Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String otpEntered;
    public void next2ButtonClicked(ActionEvent event) {   
        //Verify otp enter is same or not
        otpEntered = OTPTextField.getText();
        
        if(otpEntered.equals(otptext)){
            try {
                root = FXMLLoader.load(getClass().getResource("/Assignment_MayaFOP/enterPasswordPage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            OTPLabel.setText("Wrong OTP!\nTry again!");
        }
    }
    
    @FXML
    void backButton2(ActionEvent event) {
        try {
            count.stop();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterEmailPage.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resendOTP(ActionEvent event) throws IOException{
//        myController.loadScreen("EnterOTP", "/Assignment_MayaFOP/enterOTPPage.fxml");
        count.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterEmailPage.fxml"));
        loader.load();
        enterEmailPageController eepcController = loader.getController();
        String receiverMail = eepcController.getSiswamail();
        eepcController.setidOrEmailTextField(receiverMail);
        eepcController.next1ButtonClicked(event);
        resendOTPButton.setDisable(true);
        
    }
}
