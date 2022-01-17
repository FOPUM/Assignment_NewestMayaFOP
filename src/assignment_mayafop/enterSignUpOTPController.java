/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import static assignment_mayafop.enterOTPPageController.count;
import static assignment_mayafop.enterOTPPageController.otpTimer;
import java.io.IOException;
import java.net.URL;
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
public class enterSignUpOTPController implements Initializable, ControlledScreen{
    
    ScreenController myController;
    animation Animation;
    Random r = new Random();
    
    boolean upScreenStatus = false;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button OTPButton;

    @FXML
    private TextField OTPTextField;

    @FXML
    private Button back_button;

    @FXML
    private Label emailVerifiedLabel;

    @FXML
    private Button exit_button;
    
    @FXML 
    private AnchorPane enterOTPPage;
    
    String otpGenerated;
    private static char accStatus;
    registerControlStudent rcsController = new registerControlStudent();
    static Thread count;
    static int otpTimer =45;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int otpLimit = otpTimer;
        count = new Thread() {
                public void run() {
                    for(int i = 0 ; i<= otpLimit ; i++){
                        Platform.runLater(new Runnable() {
                            public void run() {
                                emailVerifiedLabel.setText("OTP time left: " + otpTimer);
                                if (otpTimer == 0) {
                                    OTPButton.setDisable(true);
                                    emailVerifiedLabel.setText("Timeout! Please request for a new OTP.");
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
        
        //send email function here
        
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exitButton(ActionEvent event) {
        count.stop();
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }
 
    @FXML
    void backButton(ActionEvent event) throws IOException{
        count.stop();
        root = FXMLLoader.load(getClass().getResource("/Assignment_MayaFOP/signupStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void OTPButtonClicked(ActionEvent event) throws IOException {
        String otpEntered = OTPTextField.getText();
        
        switch (accStatus) {
            case 'S':
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Assignment_MayaFOP/signupStudent.fxml"));
                    loader.load();
                    registerControlStudent studentController = loader.getController();
                    otpGenerated = studentController.getOtp();
                    if(otpEntered.equals(otpGenerated)){
                        studentController.register_user();
                        count.stop();
                        emailVerifiedLabel.setText("Signup successfully! Please exit and sign in.");
                        
                    }else{
                        emailVerifiedLabel.setText("Wrong OTP");
                    }
                    break;
                }
            case 'T':
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Assignment_MayaFOP/signupStaff.fxml"));
                    loader.load();
                    registerControlStaff staffController = loader.getController();
                    otpGenerated = staffController.getOtp();
                    if(otpEntered.equals(otpGenerated)){
                        staffController.register_user();
                        count.stop();
                        emailVerifiedLabel.setText("Signup successfully! Please exit and sign in.");
                    }else{
                        emailVerifiedLabel.setText("Wrong OTP");
                    }
                    break;
                }
            default:
                emailVerifiedLabel.setText("Something wrong, please try again.");
                break;
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

    public void setAccStatus(char accStatus) {
        enterSignUpOTPController.accStatus = accStatus;
    }
    
    public void stopCountThread(){
        count.stop();
    }
    
    
}
