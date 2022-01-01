/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class ModuleController implements Initializable, ControlledScreen{
    ScreenController myController = new ScreenController();
    animation Animation;
    
    @FXML
    private ComboBox<String> MuetBandComboBox;

    @FXML
    private ComboBox<String> ProgrammeComboBox;

    @FXML
    private ComboBox<String> courseCategoryComboBox;

    @FXML
    private TextField courseIDTextField;

    @FXML
    private TextField courseNameTextField;

    @FXML
    private ComboBox<String> courseSemComboBox;

    @FXML
    private ComboBox<String> courseYearComboBox;

    @FXML
    private TextField creditHourTextField;

    @FXML
    private AnchorPane modulePane;

    @FXML
    private ComboBox<String> nationalityComboBox;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button exit_button;
    
    @FXML
    private Label errorLabel;
    
    boolean upScreenStatus = false;
    
    private String courseID;
    private String coursename;
    private String credithour;
    private String muetband;
    private String programme;
    private String coursecategory;
    private String coursesem;
    private String courseyear;
    private String nationality;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(modulePane);
        }
        
        //Populate the combo box with information
        ObservableList<String> category = FXCollections.observableArrayList("University Course", "KELF", "Programme Core Course", "Faculty Core Course", "Faculty Elective Course", "Specialisation Elective Course");
        ObservableList<String> year = FXCollections.observableArrayList("ALL", "1","2");
        ObservableList<String> sem = FXCollections.observableArrayList("ALL", "1", "2");
        ObservableList<String> muet = FXCollections.observableArrayList("ALL", "1", "2", "3", "4", "5", "6");
        ObservableList<String> nationality = FXCollections.observableArrayList("ALL", "Malaysian", "Foreigner");
        ObservableList<String> programme = FXCollections.observableArrayList("ALL", "Software Engineer", "Artificial Intelligence", "Data Science", "Computer System and Networking", "Information System", "Multimedia");
        
        MuetBandComboBox.setItems(muet);
        ProgrammeComboBox.setItems(programme);
        courseCategoryComboBox.setItems(category);
        courseSemComboBox.setItems(sem);
        courseYearComboBox.setItems(year);
        nationalityComboBox.setItems(nationality);
        
        MuetBandComboBox.getSelectionModel().selectFirst();
        ProgrammeComboBox.getSelectionModel().selectFirst();
        courseCategoryComboBox.getSelectionModel().selectFirst();
        courseSemComboBox.getSelectionModel().selectFirst();
        courseYearComboBox.getSelectionModel().selectFirst();
        nationalityComboBox.getSelectionModel().selectFirst();
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
    
    public void getValues(){
        courseID = courseIDTextField.getText();
        coursename = courseNameTextField.getText();
        credithour = creditHourTextField.getText();
        muetband = MuetBandComboBox.getValue();
        programme = ProgrammeComboBox.getValue();
        coursecategory = courseCategoryComboBox.getValue();
        coursesem = courseSemComboBox.getValue();
        courseyear = courseYearComboBox.getValue();
        nationality = nationalityComboBox.getValue();
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void goToNextModule(ActionEvent event) throws IOException{
        getValues();
        if(courseID != null && coursename != null && credithour != null){
            root = FXMLLoader.load(getClass().getResource("ModuleNext.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            errorLabel.setText("Please fill in all the information");
        }
        
    }
    
    public String getCourseID() {
        return courseID;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCredithour() {
        return credithour;
    }

    public String getMuetband() {
        return muetband;
    }

    public String getProgramme() {
        return programme;
    }

    public String getCoursecategory() {
        return coursecategory;
    }

    public String getCoursesem() {
        return coursesem;
    }

    public String getCourseyear() {
        return courseyear;
    }

    public String getNationality() {
        return nationality;
    }
    
    
    
}
