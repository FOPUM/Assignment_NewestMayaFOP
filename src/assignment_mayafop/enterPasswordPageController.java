/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class enterPasswordPageController implements Initializable, ControlledScreen{
    
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
    private AnchorPane enterPasswordPage;
    
    @FXML
    private Button PasswordConfirmationField;

    @FXML
    private Button back_button3;
    
    @FXML
    private Button exit_button;

    @FXML
    private PasswordField confirmPaswordTextField;

    @FXML
    private Label passwordSuccessLabel;

    @FXML
    private PasswordField passwordTextField;

    String siswa;
    char accStats;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(enterPasswordPage);
        }
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterEmailPage.fxml"));
            loader.load();
            enterEmailPageController emailController = loader.getController();
            siswa = emailController.getSiswamail();
            accStats = emailController.getAccStatus();
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
    
    @FXML
    void backButton3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Assignment_MayaFOP/enterOTPPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void confirmationButtonClicked(ActionEvent event) {
        if(passwordTextField.getText().equals(confirmPaswordTextField.getText())){
            try {
                if(accStats == 'S'){
                    PreparedStatement updateNewPasswordIntoDatabase = connectDB.prepareStatement("UPDATE student SET student_password=? WHERE siswamail=?");
                    updateNewPasswordIntoDatabase.setString(1, passwordTextField.getText());
                    updateNewPasswordIntoDatabase.setString(2, siswa);
                    System.out.println(updateNewPasswordIntoDatabase);
                    updateNewPasswordIntoDatabase.executeUpdate();
                }else if(accStats == 'T'){
                    PreparedStatement updateNewPasswordIntoDatabase = connectDB.prepareStatement("UPDATE staff SET staff_password=? WHERE siswamail=?");
                    updateNewPasswordIntoDatabase.setString(1, passwordTextField.getText());
                    updateNewPasswordIntoDatabase.setString(2, siswa);
                    System.out.println(updateNewPasswordIntoDatabase);
                    updateNewPasswordIntoDatabase.executeUpdate();
                } 
                
            } catch (SQLException ex) {
                Logger.getLogger(enterEmailPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            passwordSuccessLabel.setText("Password change sucessfully! Please go back and sign in.");
        }
    }
}
