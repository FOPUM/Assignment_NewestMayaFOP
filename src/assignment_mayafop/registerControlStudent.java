/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import com.sendemail.SendMail;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 *
 * @author Ming
 */
public class registerControlStudent implements Initializable,ControlledScreen {
    ScreenController myController;
    animation Animation;
    login_controller LoginControl = new login_controller(); 
    @FXML
    private Button back_button;
    @FXML
    private Button register_button;
    @FXML 
    private Label state_label;
    
    //for student
    
    
    @FXML
    private Label message_label;
    @FXML
    private BorderPane signupScreen;
    
    
    @FXML
    private TextField siswamailTextField;
    @FXML
    private TextField matricNumberTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    
    @FXML
    private TextField ICTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private ComboBox<String> facultyComboBox;
    @FXML
    private DatePicker dateOfBirthPicker;
    @FXML
    private ComboBox<String> batchComboBox;
    @FXML
    private ComboBox<String> programmeComboBox;
    @FXML
    private ComboBox<String> raceComboBox;
    @FXML
    private ComboBox<String> nationalityComboBox;

    boolean upScreenStatus = false;
    private final String pattern = "yyyy-MM-dd";
    
    Date date = new Date();
    StringConverter converter;  
    private static String otp;

    
       static String fullname;
       static String matric_id;
       static  String siswamail;
       static  String password;
       static  String ic;
       static  String sex ;
       static  String faculty;
       static  String batch;
       static  String programme;
       static  String race;
       static  String nationality;
        
       static java.sql.Date sqlDate;
    
    MiscFunc misc = new MiscFunc();
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(signupScreen);
        }
        
        //Populate the combo box with information
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        ObservableList<String> faculty = FXCollections.observableArrayList("Faculty of Computer Science and Information System");
        ObservableList<String> batch = FXCollections.observableArrayList("2020/2021", "2021/2022");
        ObservableList<String> programme = FXCollections.observableArrayList("Software Engineer", "Artificial Intelligence", "Data Science", "Computer System and Networking", "Information System", "Multimedia");
        ObservableList<String> race = FXCollections.observableArrayList("Malay", "Indian", "Chinese");
        ObservableList<String> nationality = FXCollections.observableArrayList("Malaysian", "Others");
        
        genderComboBox.setItems(gender);
        facultyComboBox.setItems(faculty);
        batchComboBox.setItems(batch);
        programmeComboBox.setItems(programme);
        raceComboBox.setItems(race);
        nationalityComboBox.setItems(nationality);
        
    }    
    
    public void cancel_button_on_action(ActionEvent event) {
        //Cancel button to back to login page
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    
    public void register_button_on_action(ActionEvent event) {
        try {
            matric_id = matricNumberTextField.getText();
            String checkMatricNumExist = "";
            databaseConnection connectNow = new databaseConnection();
            Connection connectDB = connectNow.getConnection();
            PreparedStatement statement = connectDB.prepareStatement("Select * from student WHERE matric_num = ?");
            statement.setString(1,matric_id);
            System.out.println(matric_id);
            ResultSet query_checkForStudentDuplication = statement.executeQuery();
            //Check the validity of information
            if (query_checkForStudentDuplication.next()) {
                checkMatricNumExist = query_checkForStudentDuplication.getString("matric_num");
                System.out.println(checkMatricNumExist);
            }
            if (fullNameTextField.getText() != null && siswamailTextField.getText() != null && matricNumberTextField.getText() != null && matricNumberTextField.getText().length() >= 8) {
                if (!checkMatricNumExist.equalsIgnoreCase(matric_id)) {
                    if(passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
                    fullname = fullNameTextField.getText();
                    matric_id = matricNumberTextField.getText();
                    siswamail = siswamailTextField.getText();
                    password = passwordTextField.getText();
                    password = LoginControl.caesarCipherEncrypt(password, 9); // Encrypt the password before storing
                    ic = ICTextField.getText();
                    sex = genderComboBox.getValue();
                    faculty = facultyComboBox.getValue();
                    batch = batchComboBox.getValue();
                    programme = programmeComboBox.getValue();
                    race = raceComboBox.getValue();
                    nationality = nationalityComboBox.getValue();
                    date = java.util.Date.from(dateOfBirthPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    sqlDate = new java.sql.Date(date.getTime());
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/Assignment_MayaFOP/enterSignUpOTP.fxml"));
                        loader.load();
                        enterSignUpOTPController signUpOTPController = loader.getController();
                        signUpOTPController.stopCountThread();
                        signUpOTPController.setAccStatus('S');
                    } catch (IOException ex) {
                        Logger.getLogger(enterOTPPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        otp = otpGenerator();
                        sendOtpToSignUpUser(siswamailTextField.getText(), fullNameTextField.getText(), otp);
                        root = FXMLLoader.load(getClass().getResource("enterSignUpOTP.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(registerControlStaff.class.getName()).log(Level.SEVERE, null, ex);
                    }catch(Exception ex){
                        System.out.println("This is the bug: " + ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(registerControlStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void register_user() {
        //Collect the information at signup 
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();


        
        int number = 1;
        String gender = null;
        if(sex.equals("Male")){
            gender = "M";
        }else if(sex.equals("female")){
            gender = "F";
        }

        try {
            PreparedStatement statement = connectDB.prepareStatement("INSERT INTO student (matric_num, siswamail, student_password, student_name, student_batch, student_faculty, student_programme, "
                + "student_gender, student_race, student_date_of_birth, student_studyyear, student_studysem, student_nationality, student_ic_passport, "
                + "credit_hour) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
            statement.setString(1,matric_id);
            statement.setString(2,siswamail);
            statement.setString(3,password);
            statement.setString(4,fullname);
            statement.setString(5,batch);
            statement.setString(6,faculty);
            statement.setString(7,misc.formatToShortProgramme(programme));
            statement.setString(8,gender);
            statement.setString(9,race);
            statement.setDate(10,sqlDate);
            statement.setInt(11,number);
            statement.setInt(12,number);
            statement.setString(13,nationality);
            statement.setString(14,ic);
            statement.setInt(15,0);

            statement.executeUpdate();
            message_label.setText("User Register Successfully! Please go back and sign in.");
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
    }
    
    //Below handles the switching between staff and student signup page
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switch_to_staff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupStaff.fxml"));
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
    
    public void resendOTP(){
        otp = otpGenerator();
        sendOtpToSignUpUser(siswamail, fullname, otp);
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
     public String getSiswamail() {
        return siswamail;
    }
    
    public String getOtp() {
        return otp;
    }
}
