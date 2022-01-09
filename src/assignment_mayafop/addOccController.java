/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class addOccController implements Initializable, ControlledScreen {
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
    private ComboBox<String> labDayComboBox;
    @FXML
    private ComboBox<String> labEndTimeComboBox;
    @FXML
    private ComboBox<String> labStartTimeComboBox; 
    @FXML
    private TextField lectLocationTextField;
    @FXML
    private TextField tutoLocationTextField;
    @FXML
    private TextField labLocationTextField;
    
    @FXML
    private AnchorPane occPane;
    
    ScreenController myController;
    animation Animation;


    boolean upScreenStatus = false;
    
    boolean editingMode = false;
    int currentSelection = 0;
    
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
        ObservableList<String> labDay = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        ObservableList<String> labStart = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        ObservableList<String> labEnd = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm");
        
        lectureDayComboBox.setItems(lectDay);
        lectStartTimeComboBox.setItems(lectStart);
        lectEndTimeComboBox.setItems(lectEnd);
        tutoDayComboBox.setItems(tutoDay);
        tutoStartTimeComboBox.setItems(tutoStart);
        tutoEndTimeComboBox.setItems(tutoEnd);
        labDayComboBox.setItems(labDay);
        labStartTimeComboBox.setItems(labStart);
        labEndTimeComboBox.setItems(labEnd);
        
        if(editingMode){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleNext.fxml"));
                loader.load();
                ModuleNextController moduleNextController = loader.getController();
                
                moduleNextController.
                
                setOccTextField(moduleNextController.);
                setStaffIDTextField(super.staffID);

                setLectureDayComboBox(super.lectday);
                setLectStartTimeComboBox(super.lectstart);
                setLectEndTimeComboBox(super.lectend);
                setLectLocationTextField(super.lectlocation);

                setTutoDayComboBox(super.tutoday);
                setTutoStartTimeComboBox(super.tutostart);
                setTutoEndTimeComboBox(super.tutoend);
                setTutoLocationTextField(super.tutolocation);

                setLabDayComboBox(super.labday);
                setLabStartTimeComboBox(super.labstart);
                setLabEndTimeComboBox(super.labend);
                setLabLocationTextField(super.lablocation);

            } catch (Exception e) {
                try {
                    throw e;
                } catch (Exception ex) {
                    Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    private static String lectlocation;
    private static String tutoday;
    private static String tutostart;
    private static String tutoend;
    private static String tutolocation;
    private static String labday;
    private static String labstart;
    private static String labend;
    private static String lablocation;
    
    
    public void getValues(){
        occ = occTextField.getText();
        staffID = staffIDTextField.getText();
        
        lectday = lectureDayComboBox.getValue();
        lectstart = lectStartTimeComboBox.getValue();
        lectend = lectEndTimeComboBox.getValue();
        lectlocation = lectLocationTextField.getText();
        
        tutoday = tutoDayComboBox.getValue();
        tutostart = tutoStartTimeComboBox.getValue();
        tutoend = tutoEndTimeComboBox.getValue();
        tutolocation = tutoLocationTextField.getText();
        
        labday = labDayComboBox.getValue();
        labstart = labStartTimeComboBox.getValue();
        labend = labEndTimeComboBox.getValue();
        lablocation = labLocationTextField.getText();
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
    
    public String getLabday() {
        return labday;
    }

    public String getLabstart() {
        return labstart;
    }

    public String getLabend() {
        return labend;
    }

    public static String getLectlocation() {
        return lectlocation;
    }

    public static String getTutolocation() {
        return tutolocation;
    }

    public static String getLablocation() {
        return lablocation;
    }
    
    

    public void setLectEndTimeComboBox(String a) {
        lectEndTimeComboBox.setValue(a);
    }

    public void setLectStartTimeComboBox(String a) {
        lectStartTimeComboBox.setValue(a);
    }

    public void setLectureDayComboBox(String a) {
        lectureDayComboBox.setValue(a);
    }

    public void setOccTextField(String a) {
        occTextField.setText(a);
    }

    public void setStaffIDTextField(String a) {
        staffIDTextField.setText(a);
    }

    public void setTutoDayComboBox(String a) {
        tutoDayComboBox.setValue(a);
    }

    public void setTutoEndTimeComboBox(String a) {
        tutoEndTimeComboBox.setValue(a);
    }

    public void setTutoStartTimeComboBox(String a) {
        tutoStartTimeComboBox.setValue(a);
    }

    public void setLabDayComboBox(String a) {
        labDayComboBox.setValue(a);
    }

    public void setLabEndTimeComboBox(String a) {
        labEndTimeComboBox.setValue(a);
    }

    public void setLabStartTimeComboBox(String a) {
        labStartTimeComboBox.setValue(a);
    }

    public void setLectLocationTextField(String a) {
        lectLocationTextField.setText(a);
    }

    public void setTutoLocationTextField(String a) {
        tutoLocationTextField.setText(a);
    }

    public void setLabLocationTextField(String a) {
        labLocationTextField.setText(a);
    }
    
    

}
