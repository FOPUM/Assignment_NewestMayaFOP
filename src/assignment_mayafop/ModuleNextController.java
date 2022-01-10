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
    
    int selectedNode = -1;
    
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
    
    private ArrayList<String> occ = new ArrayList<String>();
    private ArrayList<String> staffID = new ArrayList<String>();
    private ArrayList<String> lectday = new ArrayList<String>();
    private ArrayList<String> lectstart = new ArrayList<String>();
    private ArrayList<String> lectend = new ArrayList<String>();
    private ArrayList<String> lectlocation = new ArrayList<String>();
    private ArrayList<String> tutoday = new ArrayList<String>();
    private ArrayList<String> tutostart = new ArrayList<String>();
    private ArrayList<String> tutoend = new ArrayList<String>();
    private ArrayList<String> tutolocation = new ArrayList<String>();
    private ArrayList<String> labday = new ArrayList<String>();
    private ArrayList<String> labstart = new ArrayList<String>();
    private ArrayList<String> labend = new ArrayList<String>();
    private ArrayList<String> lablocation = new ArrayList<String>();

    
    ScreenController myController = new ScreenController();
    animation Animation;
    
    addOccController occController = new addOccController();
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    //Max occ is 20?
    Node[] nodes = new Node[20];
    private int i = 0;
    
    
    boolean upScreenStatus = false;
    
    boolean confirmDelete = false;
    int occIndex;
    
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
        selectedNode = -1;
        System.out.println("selectedNode has been reset to: " +selectedNode);
        openNewOccPage();
    }
    
    public void openNewOccPage(){
        if (!showing) {
            try {
                    FXMLLoader occloader = new FXMLLoader();
                    occloader.setLocation(getClass().getResource("/Assignment_MayaFOP/addOcc.fxml"));
                    occloader.load();
                    addOccController occControl = occloader.getController();
            if (selectedNode != -1) {
                    occControl.setOccTextField(occ.get(selectedNode));
                    occControl.setStaffIDTextField(staffID.get(selectedNode));
                    occControl.setLectStartTimeComboBox(lectstart.get(selectedNode));
                    occControl.setLectEndTimeComboBox(lectend.get(selectedNode));
                    occControl.setLectureDayComboBox(lectday.get(selectedNode));
                    occControl.setLectLocationTextField(lectlocation.get(selectedNode));
                    occControl.setTutoDayComboBox(tutoday.get(selectedNode));
                    occControl.setTutoStartTimeComboBox(tutostart.get(selectedNode));
                    occControl.setTutoEndTimeComboBox(tutoend.get(selectedNode));
                    occControl.setTutoLocationTextField(tutolocation.get(selectedNode));
                    occControl.setLabDayComboBox(labday.get(selectedNode));
                    occControl.setLabStartTimeComboBox(labstart.get(selectedNode));
                    occControl.setLabEndTimeComboBox(labend.get(selectedNode));
                    occControl.setLabLocationTextField(lablocation.get(selectedNode));
                    myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
                    showing = myController.getShowing();   
                    if (occControl.isShouldAddOcc()) {
                        editValues(selectedNode);
                    }
            }else{
                    occControl.setOccTextField("");
                    occControl.setStaffIDTextField("");
                    occControl.setLectStartTimeComboBox("");
                    occControl.setLectEndTimeComboBox("");
                    occControl.setLectureDayComboBox("");
                    occControl.setLectLocationTextField("");
                    occControl.setTutoDayComboBox("");
                    occControl.setTutoStartTimeComboBox("");
                    occControl.setTutoEndTimeComboBox("");
                    occControl.setTutoLocationTextField("");
                    occControl.setLabDayComboBox("");
                    occControl.setLabStartTimeComboBox("");
                    occControl.setLabEndTimeComboBox("");
                    occControl.setLabLocationTextField("");
                    myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
                    showing = myController.getShowing();   
                    if (occControl.isShouldAddOcc()) {
                        getValues();
                        insertHbox();
                    }
            }
                
            
            } catch (Exception e) {
                System.out.println(e);
            }
        }
            
    }
    
    public void editValues(int selectedNode){
            occ.set(selectedNode, occController.getOcc());
            staffID.set(selectedNode, occController.getStaffID());
            lectday.set(selectedNode,occController.getLectday());
            lectstart.set(selectedNode,occController.getLectstart());
            lectend.set(selectedNode,occController.getLectend());
            lectlocation.set(selectedNode,occController.getLectlocation());
            
            tutoday.set(selectedNode,occController.getTutoday());
            tutostart.set(selectedNode,occController.getTutostart());
            tutoend.set(selectedNode,occController.getTutoend()); 
            tutolocation.set(selectedNode,occController.getTutolocation());
            
            labday.set(selectedNode,occController.getLabday());
            labstart.set(selectedNode,occController.getLabstart());
            labend.set(selectedNode,occController.getLabend()); 
            lablocation.set(selectedNode,occController.getLablocation());
            nodes[selectedNode] = null;
            
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));
            nodes[selectedNode] = loader.load();
            ModuleDetailsController moduleController = loader.getController();
            
            moduleController.staffNameLabel.setText("No such teacher");
            moduleController.occLabel.setText(occ.get(selectedNode));
            moduleController.staffIDLabel.setText(staffID.get(selectedNode));
            
            moduleController.lectIDLabel.setText(courseID + "_L" + selectedNode);
            moduleController.lectDayLabel.setText(lectday.get(selectedNode));
            moduleController.lectStartTimeLabel.setText(lectstart.get(selectedNode));
            moduleController.lectEndTimeLabel.setText(lectend.get(selectedNode));
            moduleController.lectLocationLabel.setText(lectlocation.get(selectedNode));
            
            moduleController.tutoIDLabel.setText(courseID + "_T" + selectedNode);
            moduleController.tutoDayLabel.setText(tutoday.get(selectedNode));
            moduleController.tutoStartTimeLabel.setText(tutostart.get(selectedNode));
            moduleController.tutoEndTimeLabel.setText(tutoend.get(selectedNode));
            moduleController.tutoLocationLabel.setText(tutolocation.get(selectedNode));
            
            moduleController.labIDLabel.setText(courseID + "_A" + selectedNode);
            moduleController.labDayLabel.setText(labday.get(selectedNode));
            moduleController.labStartTimeLabel.setText(labstart.get(selectedNode));
            moduleController.labEndTimeLabel.setText(labend.get(selectedNode));
            moduleController.labLocationLabel.setText(lablocation.get(selectedNode));
            
            final int h = selectedNode;
            
            vModuleContainer.getChildren().set(selectedNode, nodes[selectedNode]);
            
            
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
                    addOccController occControl = occloader.getController();

//                    occControl.setOccTextField(occ.get(h));
//                    occControl.setStaffIDTextField(staffID.get(h));
//
//                    occControl.setLectureDayComboBox(lectday.get(h));
//                    occControl.setLectStartTimeComboBox(lectstart.get(h));
//                    occControl.setLectEndTimeComboBox(lectend.get(h));
//                    occControl.setLectLocationTextField(lectlocation.get(h));
//
//                    occControl.setTutoDayComboBox(tutoday.get(h));
//                    occControl.setTutoStartTimeComboBox(tutostart.get(h));
//                    occControl.setTutoEndTimeComboBox(tutoend.get(h));
//                    occControl.setTutoLocationTextField(tutolocation.get(h));
//
//                    occControl.setLabDayComboBox(labday.get(h));
//                    occControl.setLabStartTimeComboBox(labstart.get(h));
//                    occControl.setLabEndTimeComboBox(labend.get(h));
//                    occControl.setLabLocationTextField(lablocation.get(h));
//                    
                    this.selectedNode = h;
                    System.out.println("Now occ contains " + occ.size());
                    System.out.println("Here clicked = " + this.selectedNode);
                    occIndex = this.selectedNode;
                    openNewOccPage();

//                    if(confirmDelete){
//                        deleteOcc(h);
//                        System.out.println("After delete, Now occ contains " + occ.size());
//                    }
                } catch (IOException ex) {
                    Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            occIndex  = h;
            
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
                    addOccController occControl = occloader.getController();
                   
                    selectedNode = h;
                    System.out.println("Now occ contains " + occ.size());
                    System.out.println("Here clicked = " + selectedNode);
                    occIndex = this.selectedNode;
                    openNewOccPage();

                    
                } catch (IOException ex) {
                    Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteOcc(int occIndex){
        occ.remove(occIndex);
        staffID.remove(occIndex);
        
        lectday.remove(occIndex);
        lectstart.remove(occIndex);
        lectend.remove(occIndex);
        lectlocation.remove(occIndex);
        
        tutoday.remove(occIndex);
        tutostart.remove(occIndex);
        tutoend.remove(occIndex);
        tutolocation.remove(occIndex);
        
        labday.remove(occIndex);
        labstart.remove(occIndex);
        labend.remove(occIndex);
        lablocation.remove(occIndex);
        
        vModuleContainer.getChildren().add(nodes[occIndex]);
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
    
    public ArrayList<String> getOcc() {
        return occ;
    }

    public ArrayList<String> getStaffID() {
        return staffID;
    }

    public ArrayList<String> getLectday() {
        return lectday;
    }

    public ArrayList<String> getLectstart() {
        return lectstart;
    }

    public ArrayList<String> getLectend() {
        return lectend;
    }

    public ArrayList<String> getLectlocation() {
        return lectlocation;
    }

    public ArrayList<String> getTutoday() {
        return tutoday;
    }

    public ArrayList<String> getTutostart() {
        return tutostart;
    }

    public ArrayList<String> getTutoend() {
        return tutoend;
    }

    public ArrayList<String> getTutolocation() {
        return tutolocation;
    }

    public ArrayList<String> getLabday() {
        return labday;
    }

    public ArrayList<String> getLabstart() {
        return labstart;
    }

    public ArrayList<String> getLabend() {
        return labend;
    }

    public ArrayList<String> getLablocation() {
        return lablocation;
    }

    public int getSelectedNode() {
        return selectedNode;
    }

    public int getOccIndex() {
        return occIndex;
    }
    
    
    
    
    

    
    
}
