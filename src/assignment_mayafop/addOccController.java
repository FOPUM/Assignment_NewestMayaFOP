/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
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
    
    private static String occSetter;
    private static String staffIdSetter;
    private static String LectStartTimeSetter;
    private static String LectEndTimeSetter;
    private static String LectDaySetter;
    private static String TutorDaySetter;
    private static String TutorStartTimeSetter;
    private static String TutorEndTimeSetter;
    private static String LabDaySetter;
    private static String LabStartTimeSetter;
    private static String LabEndTimeSetter;
    private static String LectLocationSetter;
    private static String tutorLocationSetter;
    private static String labLocationSetter;
    
    @FXML
    private Button back_button;
    @FXML
    private Button confirmOccButton;
    @FXML
    private Button deleteButton;
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

    private static boolean shouldAddOcc = false;
    boolean upScreenStatus = false;


//    addOccController(String occ, String staffID, String lectLocation, String tutoLocation, String labLocation) {
//         this.occTextField.setText(occ);
//        this.staffIDTextField.setText(staffID);
//        this.lectLocationTextField.setText(lectLocation);
//        this.tutoLocationTextField.setText(tutoLocation);
//        this.labLocationTextField.setText(labLocation);
//    }
//
//    addOccController() {
//        
//    }
//    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labDayComboBox.setValue(LabDaySetter);
        labEndTimeComboBox.setValue(LabEndTimeSetter);
        labStartTimeComboBox.setValue(LabStartTimeSetter);
        lectLocationTextField.setText(LectLocationSetter);
        tutoLocationTextField.setText(tutorLocationSetter);
        labLocationTextField.setText(labLocationSetter);
        lectStartTimeComboBox.setValue(LectStartTimeSetter);
        lectEndTimeComboBox.setValue(LectEndTimeSetter);
        lectureDayComboBox.setValue(LectDaySetter);
        occTextField.setText(occSetter);
        staffIDTextField.setText(staffIdSetter);
        tutoDayComboBox.setValue(TutorDaySetter);
        tutoStartTimeComboBox.setValue(TutorStartTimeSetter);
        tutoEndTimeComboBox.setValue(TutorEndTimeSetter);
        System.out.println("OccTextfield got: " + occTextField.getText());
        System.out.println("StaffIDTEXTFIELD got: " + staffIDTextField.getText());
        
        
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
        
        
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        shouldAddOcc = false;
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
    
    public void confirmOcc(ActionEvent event) {
        shouldAddOcc = true;
        getValues();
        Stage stage = (Stage) confirmOccButton.getScene().getWindow();
        stage.close();
    }

    
    public void delete(ActionEvent event) {
        
        try {
            FXMLLoader moduleloader = new FXMLLoader();
            moduleloader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleNext.fxml"));
            moduleloader.load();
            ModuleNextController moduleNextController = moduleloader.getController();
            moduleNextController.deleteOcc(moduleNextController.getOccIndex());
        } catch (IOException ex) {
            Logger.getLogger(addOccController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = (Stage) deleteButton.getScene().getWindow();
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
    
    public void setLectStartTimeComboBox(String a) {
        LectStartTimeSetter = a;
    }
    
    public void setLectEndTimeComboBox(String a) {
        LectEndTimeSetter = a;
    }


    public void setLectureDayComboBox(String a) {
        LectDaySetter = a;
    }

    public void setOccTextField(String a) {
        occSetter = a;
    }

    public void setStaffIDTextField(String a) {
        staffIdSetter = a;
    }

    public void setTutoDayComboBox(String a) {
        TutorDaySetter = a;
    }

    public void setTutoStartTimeComboBox(String a) {
        TutorStartTimeSetter = a;
    }
    
    public void setTutoEndTimeComboBox(String a) {
        TutorEndTimeSetter = a;
    }

    public void setLabDayComboBox(String a) {
        LabDaySetter = a;
        
    }

    public void setLabEndTimeComboBox(String a) {
        LabEndTimeSetter = a;
        
    }

    public void setLabStartTimeComboBox(String a) {
        LabStartTimeSetter = a;
    }

    public void setLectLocationTextField(String a) {
        LectLocationSetter = a;
    }

    public void setTutoLocationTextField(String a) {
        tutorLocationSetter = a;
    }

    public void setLabLocationTextField(String a) {
        labLocationSetter = a;
    }
    
    public boolean isShouldAddOcc() {
        return shouldAddOcc;
    }

    public void setShouldAddOcc(boolean shouldAddOcc) {
        addOccController.shouldAddOcc = shouldAddOcc;
    }

}
