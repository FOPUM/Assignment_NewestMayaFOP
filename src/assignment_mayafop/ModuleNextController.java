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
    private String muetband;
    private String programme;
    private String coursecategory;
    private String coursesem;
    private String courseyear;
    private String nationality;
    
    private static ArrayList<String> occ = new ArrayList<String>();
    private static ArrayList<String> staffID = new ArrayList<String>();
    private static ArrayList<String> lectday = new ArrayList<String>();
    private static ArrayList<String> lectstart = new ArrayList<String>();
    private static ArrayList<String> lectend = new ArrayList<String>();
    private static ArrayList<String> lectlocation = new ArrayList<String>();
    private static ArrayList<String> tutoday = new ArrayList<String>();
    private static ArrayList<String> tutostart = new ArrayList<String>();
    private static ArrayList<String> tutoend = new ArrayList<String>();
    private static ArrayList<String> tutolocation = new ArrayList<String>();
    private static ArrayList<String> labday = new ArrayList<String>();
    private static ArrayList<String> labstart = new ArrayList<String>();
    private static ArrayList<String> labend = new ArrayList<String>();
    private static ArrayList<String> lablocation = new ArrayList<String>();

    
    ScreenController myController = new ScreenController();
    animation Animation;
    
    addOccController occController = new addOccController();
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    //Max occ is 20?
    Node[] nodes = new Node[20];
    private int i = 0;
    
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
        openNewOccPage();
    }
    
    public void openNewOccPage(){
        if (!showing) {
            myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
            showing = myController.getShowing();   
        }         
        getValues();
        insertHbox();
    }
    
    public void getValues(){
        
            occ.add(occController.getOcc());
            staffID.add(occController.getStaffID());
            lectday.add(occController.getLectday());
            lectstart.add(occController.getLectstart());
            lectend.add(occController.getLectend());
            lectlocation.add(occController.getLectlocation());
            
            tutoday.add(occController.getTutoday());
            tutostart.add(occController.getTutostart());
            tutoend.add(occController.getTutoend()); 
            tutolocation.add(occController.getTutolocation());
            
            labday.add(occController.getLabday());
            labstart.add(occController.getLabstart());
            labend.add(occController.getLabend()); 
            lablocation.add(occController.getLablocation());

    }

    public void insertHbox(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));
            nodes[i] = loader.load();
            ModuleDetailsController moduleController = loader.getController();
            
            moduleController.staffNameLabel.setText("No such teacher");
            moduleController.occLabel.setText(occ.get(i));
            moduleController.staffIDLabel.setText(staffID.get(i));
            
            moduleController.lectIDLabel.setText(courseID + "_L" + i);
            moduleController.lectDayLabel.setText(lectday.get(i));
            moduleController.lectStartTimeLabel.setText(lectstart.get(i));
            moduleController.lectEndTimeLabel.setText(lectend.get(i));
            moduleController.lectLocationLabel.setText(lectlocation.get(i));
            
            moduleController.tutoIDLabel.setText(courseID + "_T" + i);
            moduleController.tutoDayLabel.setText(tutoday.get(i));
            moduleController.tutoStartTimeLabel.setText(tutostart.get(i));
            moduleController.tutoEndTimeLabel.setText(tutoend.get(i));
            moduleController.tutoLocationLabel.setText(tutolocation.get(i));
            
            moduleController.labIDLabel.setText(courseID + "_A" + i);
            moduleController.labDayLabel.setText(labday.get(i));
            moduleController.labStartTimeLabel.setText(labstart.get(i));
            moduleController.labEndTimeLabel.setText(labend.get(i));
            moduleController.labLocationLabel.setText(lablocation.get(i));
            
            final int h = i;

            vModuleContainer.getChildren().add(nodes[i]);
            i++;
            
            nodes[h].setOnMouseEntered(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: transparent");
            });
            nodes[h].setOnMouseExited(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: transparent");
            });
            nodes[h].setOnMousePressed(evt -> {
                //add effect
                try {
                    FXMLLoader occloader = new FXMLLoader();
                    occloader.setLocation(getClass().getResource("/Assignment_MayaFOP/addOcc.fxml"));
                    occloader.load();
                    addOccController o = occloader.getController();
                    o.editingMode = true;
                    o.currentSelection = h;
                } catch (IOException ex) {
                    Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                openNewOccPage();
                
//                
//                occController.setOccTextField(occ);
//                occController.setStaffIDTextField(staffID);
//                
//                occController.setLectureDayComboBox(lectday);
//                occController.setLectStartTimeComboBox(lectstart);
//                occController.setLectEndTimeComboBox(lectend);
//                occController.setLectLocationTextField(lectlocation);
//                
//                occController.setTutoDayComboBox(tutoday);
//                occController.setTutoStartTimeComboBox(tutostart);
//                occController.setTutoEndTimeComboBox(tutoend);
//                occController.setTutoLocationTextField(tutolocation);
//                
//                occController.setLabDayComboBox(labday);
//                occController.setLabStartTimeComboBox(labstart);
//                occController.setLabEndTimeComboBox(labend);
//                occController.setLabLocationTextField(lablocation);
                
                
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

        courseyear = previousController.getCourseyear();
        coursesem = previousController.getCoursesem();    
        muetband = previousController.getMuetband();
        nationality = previousController.getNationality().toUpperCase();

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
            statement.setString(5,courseyear);
            statement.setString(6,coursesem);
            statement.setString(7,muetband);
            statement.setString(8,nationality);
            statement.setString(9,programme);
            statement.executeUpdate();
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public static ArrayList<String> getOcc() {
        return occ;
    }

    public static ArrayList<String> getStaffID() {
        return staffID;
    }

    public static ArrayList<String> getLectday() {
        return lectday;
    }

    public static ArrayList<String> getLectstart() {
        return lectstart;
    }

    public static ArrayList<String> getLectend() {
        return lectend;
    }

    public static ArrayList<String> getLectlocation() {
        return lectlocation;
    }

    public static ArrayList<String> getTutoday() {
        return tutoday;
    }

    public static ArrayList<String> getTutostart() {
        return tutostart;
    }

    public static ArrayList<String> getTutoend() {
        return tutoend;
    }

    public static ArrayList<String> getTutolocation() {
        return tutolocation;
    }

    public static ArrayList<String> getLabday() {
        return labday;
    }

    public static ArrayList<String> getLabstart() {
        return labstart;
    }

    public static ArrayList<String> getLabend() {
        return labend;
    }

    public static ArrayList<String> getLablocation() {
        return lablocation;
    }
    
    
    

    
    
}
