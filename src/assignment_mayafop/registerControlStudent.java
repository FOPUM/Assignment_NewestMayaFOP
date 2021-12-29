/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(signupScreen);
        }
        
        //Populate the combo box with information
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        ObservableList<String> faculty = FXCollections.observableArrayList("Faculty of Computer Science and Information System");
        ObservableList<String> batch = FXCollections.observableArrayList("2020/2021", "2021/2022");
        ObservableList<String> programme = FXCollections.observableArrayList("SE", "Data", "AI");
        ObservableList<String> race = FXCollections.observableArrayList("Chinese", "Asian", "Black");
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
        //Check the validity of information
        if (fullNameTextField.getText() != null && siswamailTextField.getText() != null && matricNumberTextField.getText() != null && matricNumberTextField.getText().length() >= 8) {
            
            if(passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            register_user();
            //message_label.setText("");
            message_label.setText("User register successfully. Please go back and sign in.");
            }else {
            message_label.setText("Password does not match");
            }
            
        } else {
            message_label.setText("Please enter correct information!");
        }
    }
    
    public void register_user() {
        //Collect the information at signup 
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();
    
        

        
        String fullname = fullNameTextField.getText();
        String matric_id = matricNumberTextField.getText();
        String siswamail = siswamailTextField.getText();
        String password = passwordTextField.getText();
        String ic = ICTextField.getText();
        String sex = genderComboBox.getValue();
        String faculty = facultyComboBox.getValue();
        String batch = batchComboBox.getValue();
        String programme = programmeComboBox.getValue();
        String race = raceComboBox.getValue();
        String nationality = nationalityComboBox.getValue();
        
        java.util.Date date = java.util.Date.from(dateOfBirthPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        
        int number = 1;
        String gender = null;
        if(sex.equals("Male")){
            gender = "M";
        }else if(sex.equals("female")){
            gender = "F";
        }
        

        try {
            PreparedStatement statement = connectDB.prepareStatement("INSERT INTO student (matric_num, siswamail, password, student_name, student_batch, student_faculty, student_programme, "
                + "student_gender, student_race, student_date_of_birth, student_studyyear, student_studysem, student_nationality, student_ic_passport, "
                + "credit_hour) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
            statement.setString(1,matric_id);
            statement.setString(2,siswamail);
            statement.setString(3,password);
            statement.setString(4,fullname);
            statement.setString(5,batch);
            statement.setString(6,faculty);
            statement.setString(7,programme);
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
//    public static boolean is_numeric(String str) { 
//        try {  
//            Double.parseDouble(str);  
//            return true;
//        } catch(NumberFormatException e){  
//            return false;  
//        }  
//    }

    
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

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
