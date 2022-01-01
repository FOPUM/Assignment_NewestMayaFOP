/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */



public class ModuleNextController implements Initializable, ControlledScreen{
    
    @FXML
    private Button addCourseButton;

    @FXML
    private Button back_button;

    @FXML
    private Button confirmButton;

    @FXML
    private Button exit_button;

    @FXML
    private AnchorPane moduleNextPane;
    @FXML
    private VBox vModuleContainer;
    
    private String courseID;
    private String coursename;
    private int credithour;
    private int muetband;
    private String programme;
    private String coursecategory;
    private int coursesem;
    private int courseyear;
    private String nationality;
    
    private String occ;
    private String staffID;
    private String lectday;
    private String lectstart;
    private String lectend;
    private String tutoday;
    private String tutostart;
    private String tutoend;
    private String labstart;
    private String labday;
    private String labend;
    
    
    ScreenController myController = new ScreenController();
    animation Animation;
    
    addOccController occController = new addOccController();
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    Node[] nodes = new Node[20];
    private int i =0;
    
    boolean upScreenStatus = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(moduleNextPane);
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
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void backButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Module.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    boolean showing = myController.getShowing();
    
    public void addNewOcc(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
            showing = myController.getShowing();   
        }         
        getValues();
        insertHbox();
    }
    
    //Not finish yet
    public void confirmAddCourse(ActionEvent event){
        getPreviousPageValues();
        try {
            PreparedStatement statement = connectDB.prepareStatement("INSERT INTO course VALUES (?,?,?,?,?,?,?,?,?)");
            
            statement.setString(1,courseID);
            statement.setString(2,coursename);
            statement.setInt(3,credithour);
            statement.setString(4,coursecategory);
            statement.setInt(5,courseyear);
            statement.setInt(6,coursesem);
            statement.setInt(7,muetband);
            statement.setString(8,nationality);
            statement.setString(9,programme);
            statement.executeUpdate();
//            message_label.setText("User Register Successfully! Please go back and sign in.");
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    
    public void getValues(){
        
            occ = occController.getOcc();
            staffID = occController.getStaffID();
            lectday = occController.getLectday();
            lectstart = occController.getLectstart();
            lectend = occController.getLectend();
            tutoday = occController.getTutoday();
            tutostart = occController.getTutostart();
            tutoend = occController.getTutoend(); 
            labday = occController.getLabday();
            labstart = occController.getLabstart();
            labend = occController.getLabend(); 

    }
    
    public void insertHbox(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));

            nodes[i] = loader.load();
            
            ModuleDetailsController moduleController = loader.getController();
            
            moduleController.occLabel.setText(occ);
            moduleController.staffIDLabel.setText(staffID);
            moduleController.lectDayLabel.setText(lectday);
            moduleController.lectStartTimeLabel.setText(lectstart);
            moduleController.lectEndTimeLabel.setText(lectend);
            moduleController.tutoDayLabel.setText(tutoday);
            moduleController.tutoStartTimeLabel.setText(tutostart);
            moduleController.tutoEndTimeLabel.setText(tutoend);
            moduleController.tutoDayLabel.setText(labday);
            moduleController.tutoStartTimeLabel.setText(labstart);
            moduleController.tutoEndTimeLabel.setText(labend);
            
            final int h = i;

            vModuleContainer.getChildren().add(nodes[i]);
            i++;
            
            nodes[h].setOnMouseEntered(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: #b4baca");
            });
            nodes[h].setOnMouseExited(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: transparent");
            });
            nodes[h].setOnMousePressed(evt -> {
                //add effect
                
                
            });


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public void getPreviousPageValues(){
        ModuleController previousController = new ModuleController();
        courseID = previousController.getCourseID().toUpperCase();
        coursename = previousController.getCoursename().toUpperCase();
        credithour = Integer.parseInt(previousController.getCredithour());
        
        if(previousController.getMuetband().equals("ALL")){
            muetband = 999;
        }else{
            muetband = Integer.parseInt(previousController.getMuetband());
        }
        
   
        if(previousController.getProgramme().equals("Software Engineer")){
            programme = "SE";
        }else if(previousController.getProgramme().equals("Data Science")){
            programme = "DS";
        }else if(previousController.getProgramme().equals("Artificial Intelligence")){
            programme = "AI";
        }else if(previousController.getProgramme().equals("Computer System and Networking")){
            programme = "CSN";
        }else if(previousController.getProgramme().equals("Information System")){
            programme = "IS";
        }else if(previousController.getProgramme().equals("Multimedia")){
            programme = "MM";
        }else if(previousController.getProgramme().equals("ALL")){
            programme = "ALL";
        }

        if(previousController.getCoursecategory().equals("University Course")){
            coursecategory = "UC";
        }else if(previousController.getCoursecategory().equals("KELF")){
            coursecategory = "KELF";
        }else if(previousController.getCoursecategory().equals("Programme Core Course")){
            coursecategory = "PCC";
        }else if(previousController.getCoursecategory().equals("Faculty Core Course")){
            coursecategory = "FCC";
        }else if(previousController.getCoursecategory().equals("Faculty Elective Course")){
            coursecategory = "FEC";
        }else if(previousController.getCoursecategory().equals("Specialisation Elective Course")){
            coursecategory = "SEC";
        }
        
        if(previousController.getCoursesem().equals("ALL")){
            coursesem = 999;
        }else{
            coursesem = Integer.parseInt(previousController.getCoursesem());
        }
        
        if(previousController.getCourseyear().equals("ALL")){
            courseyear = 999;
        }else{
            courseyear = Integer.parseInt(previousController.getCourseyear());
        }
        
        nationality = previousController.getNationality().toUpperCase();
    }
    
    
}
