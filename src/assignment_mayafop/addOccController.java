/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class addOccController implements Initializable, ControlledScreen{
    @FXML
    private Button back_button;
    @FXML
    private Button confirmOccButton;
    @FXML
    private Button exit_button;    
    @FXML
    private ComboBox<String> lectEndTimeComboBox;
    @FXML
    private ComboBox<String> lectStartTimeComboBox;
    @FXML
    private ComboBox<String> lectureDayComboBox;
    @FXML
    private AnchorPane modulePane;
    @FXML
    private TextField occTextField;
    @FXML
    private TextField staffIDTextField;
    @FXML
    private ComboBox<String> tutoDayComboBox;
    @FXML
    private ComboBox<String> tutoEndTimeComboBox;
    @FXML
    private ComboBox<String> tutoStartTimeComboBox;    
    @FXML
    private AnchorPane occPane;
    
    ScreenController myController;
    animation Animation;


    boolean upScreenStatus = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(occPane);
        }
        
        ObservableList<String> lectDay = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        ObservableList<String> lectStart = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        ObservableList<String> lectEnd = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        ObservableList<String> tutoDay = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        ObservableList<String> tutoStart = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        ObservableList<String> tutoEnd = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        
        lectureDayComboBox.setItems(lectDay);
        lectStartTimeComboBox.setItems(lectStart);
        lectEndTimeComboBox.setItems(lectEnd);
        tutoDayComboBox.setItems(tutoDay);
        tutoStartTimeComboBox.setItems(tutoStart);
        tutoEndTimeComboBox.setItems(tutoEnd);
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
    
    public void confirmOcc(ActionEvent event) {
        getValues();
        System.out.println(occ);
        Stage stage = (Stage) confirmOccButton.getScene().getWindow();
        stage.close();
    }
    
    private static String occ;
    private static String staffID;
    private static String lectday;
    private static String lectstart;
    private static String lectend;
    private static String tutoday;
    private static String tutostart;
    private static String tutoend;
    
    public void getValues(){
        occ = occTextField.getText();
        staffID = staffIDTextField.getText();
        lectday = lectureDayComboBox.getValue();
        lectstart = lectStartTimeComboBox.getValue();
        lectend = lectEndTimeComboBox.getValue();
        tutoday = tutoDayComboBox.getValue();
        tutostart = tutoStartTimeComboBox.getValue();
        tutoend = tutoEndTimeComboBox.getValue();
    }

    public String getOcc() {
        return occ;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getLectday() {
        return lectday;
    }

    public String getLectstart() {
        return lectstart;
    }

    public String getLectend() {
        return lectend;
    }

    public String getTutoday() {
        return tutoday;
    }

    public String getTutostart() {
        return tutostart;
    }

    public String getTutoend() {
        return tutoend;
    }
    
    

}
