/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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




public class ModuleNextController implements Initializable, ControlledScreen{
    MiscFunc misc = new MiscFunc();
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
    private ArrayList<String> occCapacity = new ArrayList<String>();
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
    private ArrayList<String> lectstaffid = new ArrayList<String>();
    private ArrayList<String> tutostaffid = new ArrayList<String>();
    private ArrayList<String> labstaffid = new ArrayList<String>();

    
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
        getPreviousPageValues();
        System.out.println(coursecategory);
        try {
            ResultSet checkForEditOrNewModule = connectDB.createStatement().executeQuery("SELECT COUNT(course_id) AS courseIDPresentOrNot FROM course WHERE course_id='"+courseID+"'");
            while(checkForEditOrNewModule.next()) {
                String presentOfCourseID = checkForEditOrNewModule.getString("courseIDPresentOrNot");
                System.out.println(presentOfCourseID + " is rhis");
                if(presentOfCourseID.equals("1")){
                    getValuesFromDatabaseOnOcc();
                    System.out.println(occ.size() + " occ");
                    for (int j = 0; j < occ.size(); j++) {
                        insertHbox();
                    }
                }
            }
        } catch (SQLException ex) { 
            Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
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
//        getPreviousPageValues();
        selectedNode = -1;
        System.out.println("selectedNode has been reset to: " + selectedNode);
        openNewOccPage();
    }
    
    public void openNewOccPage(){
        if (!showing) {
            try {
                    FXMLLoader occloader = new FXMLLoader();
                    occloader.setLocation(getClass().getResource("/Assignment_MayaFOP/addOcc.fxml"));
                    occloader.load();
                    addOccController occControl = occloader.getController();
                    System.out.println("This node is selected: " +selectedNode);
            if (selectedNode != -1) {
                    occControl.setOccTextField(occ.get(selectedNode));
                    occControl.setCapacityTextField(occCapacity.get(selectedNode));
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
                    occControl.setLectStaffIDTextField(lectstaffid.get(selectedNode));
                    occControl.setTutoStaffIDTextField(tutostaffid.get(selectedNode));
                    occControl.setLabStaffIDTextField(labstaffid.get(selectedNode));
                    myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
                    showing = myController.getShowing();   
                    if (occControl.isShouldAddOcc()) {
                        editValues(selectedNode);
                        occControl.setShouldAddOcc(false);
                    }
                    if (occControl.isDeleteOcc()) {
                        deleteOcc();
                        occControl.setDeleteOcc(false);
                }
                    
            }else{
                    occControl.setOccTextField("");
                    occControl.setCapacityTextField("");
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
                    occControl.setLectStaffIDTextField("");
                    occControl.setTutoStaffIDTextField("");
                    occControl.setLabStaffIDTextField("");
                    myController.showPopupStage(moduleNextPane, "/assignment_MayaFOP/addOcc.fxml");
                    showing = myController.getShowing();   
                    if (occControl.isShouldAddOcc()) {
                        getValuesfromAddOccController();
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
            occCapacity.set(selectedNode, occController.getCapacity());
            
            lectday.set(selectedNode,occController.getLectday());
            lectstart.set(selectedNode,occController.getLectstart());
            lectend.set(selectedNode,occController.getLectend());
            lectlocation.set(selectedNode,occController.getLectlocation());
            lectstaffid.set(selectedNode, occController.getLectstaffid());
            
            tutoday.set(selectedNode,occController.getTutoday());
            tutostart.set(selectedNode,occController.getTutostart());
            tutoend.set(selectedNode,occController.getTutoend()); 
            tutolocation.set(selectedNode,occController.getTutolocation());
            tutostaffid.set(selectedNode, occController.getTutostaffid());
            
            labday.set(selectedNode,occController.getLabday());
            labstart.set(selectedNode,occController.getLabstart());
            labend.set(selectedNode,occController.getLabend()); 
            lablocation.set(selectedNode,occController.getLablocation());
            labstaffid.set(selectedNode, occController.getLabstaffid());
            
            
            nodes[selectedNode] = null;
            
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));
            nodes[selectedNode] = loader.load();
            ModuleDetailsController moduleController = loader.getController();

        try {
            ResultSet lectStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ lectstaffid.get(selectedNode) + "'");
            while(lectStaffQuery.next()) {
                moduleController.lectStaffNameLabel.setText(misc.upperLetter(lectStaffQuery.getString("staff_name")));
            }     
            ResultSet tutoStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ tutostaffid.get(selectedNode) + "'");
            while(tutoStaffQuery.next()) {
                moduleController.tutoStaffNameLabel.setText(misc.upperLetter(tutoStaffQuery.getString("staff_name")));
            }   
            ResultSet labStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ labstaffid.get(selectedNode) + "'");
            while(labStaffQuery.next()) {
                moduleController.labStaffNameLabel.setText(misc.upperLetter(labStaffQuery.getString("staff_name")));
            }   
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
            
            String actOcc = "";
            if (selectedNode != -1) {
                if(occ.get(i).startsWith("o") || occ.get(i).startsWith("O")){
                    if (Character.isDigit(occ.get(i).charAt(occ.get(i).length()-2))) {
                         actOcc = occ.get(i).substring(occ.get(i).length()-2);
                    }
                    else if (Character.isDigit(occ.get(i).charAt(occ.get(i).length()-1))) {
                         actOcc = occ.get(i).substring(occ.get(i).length()-1);
                    }
                }else{
                    actOcc = occ.get(i);
                } 
            }
            moduleController.occLabel.setText(occ.get(selectedNode));
            moduleController.capacityLabel.setText(occCapacity.get(selectedNode));
            
            moduleController.lectIDLabel.setText(courseID + "_L" + actOcc);
            moduleController.lectDayLabel.setText(lectday.get(selectedNode));
            moduleController.lectStartTimeLabel.setText(lectstart.get(selectedNode));
            moduleController.lectEndTimeLabel.setText(lectend.get(selectedNode));
            moduleController.lectLocationLabel.setText(lectlocation.get(selectedNode));
            moduleController.lectStaffIDLabel.setText(lectstaffid.get(selectedNode));
            
            moduleController.tutoIDLabel.setText(courseID + "_T" + actOcc);
            moduleController.tutoDayLabel.setText(tutoday.get(selectedNode));
            moduleController.tutoStartTimeLabel.setText(tutostart.get(selectedNode));
            moduleController.tutoEndTimeLabel.setText(tutoend.get(selectedNode));
            moduleController.tutoLocationLabel.setText(tutolocation.get(selectedNode));
            moduleController.tutoStaffIDLabel.setText(tutostaffid.get(selectedNode));
            
            moduleController.labIDLabel.setText(courseID + "_A" + actOcc);
            moduleController.labDayLabel.setText(labday.get(selectedNode));
            moduleController.labStartTimeLabel.setText(labstart.get(selectedNode));
            moduleController.labEndTimeLabel.setText(labend.get(selectedNode));
            moduleController.labLocationLabel.setText(lablocation.get(selectedNode));
            moduleController.labStaffIDLabel.setText(labstaffid.get(selectedNode));
            
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
    
    public void getValuesfromAddOccController(){
        occ.add(occController.getOcc());
        occCapacity.add(occController.getCapacity());

        lectday.add(occController.getLectday());
        lectstart.add(occController.getLectstart());
        lectend.add(occController.getLectend());
        lectlocation.add(occController.getLectlocation());
        lectstaffid.add(occController.getLectstaffid());

        tutoday.add(occController.getTutoday());
        tutostart.add(occController.getTutostart());
        tutoend.add(occController.getTutoend()); 
        tutolocation.add(occController.getTutolocation());
        tutostaffid.add(occController.getTutostaffid());

        labday.add(occController.getLabday());
        labstart.add(occController.getLabstart());
        labend.add(occController.getLabend()); 
        lablocation.add(occController.getLablocation());
        labstaffid.add(occController.getLabstaffid());
    }
    
    public void clearMemory(){
        occ.clear();
        occCapacity.clear();

        lectday.clear();
        lectstart.clear();
        lectend.clear();
        lectlocation.clear();
        lectstaffid.clear();

        tutoday.clear();
        tutostart.clear();
        tutoend.clear();
        tutolocation.clear();
        tutostaffid.clear();

        labday.clear();
        labstart.clear();
        labend.clear();
        lablocation.clear();
        labstaffid.clear();        
    }
    
    public void getValuesFromDatabaseOnOcc(){
        String databaseOccQueryText = "SELECT occ.occ_name, occ.occ_capacity,\n" +
                                        "lecture.lecture_id, lecture.lecture_day, lecture.lecture_start_time, lecture.lecture_end_time, lecture.lecture_location, staff_teach_lecture.staff_id AS lectstaff,\n" +
                                        "tutorial.tutorial_id, tutorial.tutorial_day, tutorial.tutorial_start_time, tutorial.tutorial_end_time, tutorial.tutorial_location, staff_teach_tutorial.staff_id AS tutostaff,\n" +
                                        "lab.lab_id, lab.lab_day, lab.lab_start_time, lab.lab_start_time, lab.lab_end_time, lab.lab_location, staff_teach_lab.staff_id as labstaff\n" +
                                        "FROM course\n" +
                                        "INNER JOIN course_occ ON course.course_id=course_occ.course_id\n" +
                                        "RIGHT JOIN occ ON course_occ.occ_id=occ.occ_id\n" +
                                        "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                                        "INNER JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                                        "INNER JOIN tutorial ON tutorial.tutorial_id=occ.tutorial_id\n" +
                                        "INNER JOIN staff_teach_tutorial ON staff_teach_tutorial.tutorial_id=tutorial.tutorial_id\n" +
                                        "INNER JOIN lab ON lab.lab_id=occ.lab_id\n" +
                                        "INNER JOIN staff_teach_lab ON staff_teach_lab.lab_id=occ.lab_id\n" +
                                        "WHERE course.course_id='"+courseID+"'";
        
        try {
            ResultSet DatabaseOccQuery = connectDB.createStatement().executeQuery(databaseOccQueryText);
            while(DatabaseOccQuery.next()) {
                occ.add(DatabaseOccQuery.getString("occ_name").substring(3));
                occCapacity.add(DatabaseOccQuery.getString("occ_capacity"));

                lectday.add(misc.formatDay(DatabaseOccQuery.getString("lecture_day")));
                lectstart.add(misc.formatTime(DatabaseOccQuery.getString("lecture_start_time")));
                lectend.add(misc.formatTime(DatabaseOccQuery.getString("lecture_end_time")));
                lectlocation.add(DatabaseOccQuery.getString("lecture_location"));
                lectstaffid.add(DatabaseOccQuery.getString("lectstaff"));

                tutoday.add(misc.formatDay(DatabaseOccQuery.getString("tutorial_day")));
                tutostart.add(misc.formatTime(DatabaseOccQuery.getString("tutorial_start_time")));
                tutoend.add(misc.formatTime(DatabaseOccQuery.getString("tutorial_end_time"))); 
                tutolocation.add(DatabaseOccQuery.getString("tutorial_location"));
                tutostaffid.add(DatabaseOccQuery.getString("tutostaff"));

                labday.add(misc.formatDay(DatabaseOccQuery.getString("lab_day")));
                labstart.add(misc.formatTime(DatabaseOccQuery.getString("lab_start_time")));
                labend.add(misc.formatTime(DatabaseOccQuery.getString("lab_end_time"))); 
                lablocation.add(misc.upperLetter(DatabaseOccQuery.getString("lab_location")));
                labstaffid.add(DatabaseOccQuery.getString("labstaff"));
            }     
            
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void insertHbox(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));
            nodes[i] = loader.load();
            ModuleDetailsController moduleController = loader.getController();
            
//            String staffNameQueryText = "SELECT staff_name FROM staff where staff_id='"+ staffID.get(i) + "'";
        try {
            ResultSet lectStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ lectstaffid.get(i) + "'");
            while(lectStaffQuery.next()) {
                moduleController.lectStaffNameLabel.setText(misc.upperLetter(lectStaffQuery.getString("staff_name")));
            }     
            ResultSet tutoStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ tutostaffid.get(i) + "'");
            while(tutoStaffQuery.next()) {
                moduleController.tutoStaffNameLabel.setText(misc.upperLetter(tutoStaffQuery.getString("staff_name")));
            }   
            ResultSet labStaffQuery = connectDB.createStatement().executeQuery("SELECT staff_name FROM staff where staff_id='"+ labstaffid.get(i) + "'");
            while(labStaffQuery.next()) {
                moduleController.labStaffNameLabel.setText(misc.upperLetter(labStaffQuery.getString("staff_name")));
            }
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
            
            String actOcc = "";
                    
            if(occ.get(i).startsWith("o") || occ.get(i).startsWith("O")){
                if (Character.isDigit(occ.get(i).charAt(occ.get(i).length()-2))) {
                     actOcc = occ.get(i).substring(occ.get(i).length()-2);
                }
                else if (Character.isDigit(occ.get(i).charAt(occ.get(i).length()-1))) {
                     actOcc = occ.get(i).substring(occ.get(i).length()-1);
                }
            }else{
                actOcc = occ.get(i);
            } 
            
            moduleController.occLabel.setText("Occurence: " + occ.get(i));
            moduleController.capacityLabel.setText("Capacity: " + occCapacity.get(i));
            
            moduleController.lectIDLabel.setText(courseID + "_L" + actOcc);
            moduleController.lectDayLabel.setText(lectday.get(i));
            moduleController.lectStartTimeLabel.setText(lectstart.get(i));
            moduleController.lectEndTimeLabel.setText(lectend.get(i));
            moduleController.lectLocationLabel.setText(lectlocation.get(i));
            moduleController.lectStaffIDLabel.setText(lectstaffid.get(i));
            
            moduleController.tutoIDLabel.setText(courseID + "_T" + actOcc);
            moduleController.tutoDayLabel.setText(tutoday.get(i));
            moduleController.tutoStartTimeLabel.setText(tutostart.get(i));
            moduleController.tutoEndTimeLabel.setText(tutoend.get(i));
            moduleController.tutoLocationLabel.setText(tutolocation.get(i));
            moduleController.tutoStaffIDLabel.setText(tutostaffid.get(i));
            
            moduleController.labIDLabel.setText(courseID + "_A" + actOcc);
            moduleController.labDayLabel.setText(labday.get(i));
            moduleController.labStartTimeLabel.setText(labstart.get(i));
            moduleController.labEndTimeLabel.setText(labend.get(i));
            moduleController.labLocationLabel.setText(lablocation.get(i));
            moduleController.labStaffIDLabel.setText(labstaffid.get(i));
            
            
            
            final int h = i;
            
            vModuleContainer.getChildren().add(nodes[i]);
            i++;
            
            System.out.println(i);
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
                   
                    this.selectedNode = h;
                    System.out.println("Now occ contains " + occ.size());
                    System.out.println("Here clicked = " + selectedNode);
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
    
    public void deleteOcc(){
        occ.remove(selectedNode);
        occCapacity.remove(selectedNode);
        
        lectday.remove(selectedNode);
        lectstart.remove(selectedNode);
        lectend.remove(selectedNode);
        lectlocation.remove(selectedNode);
        lectstaffid.remove(selectedNode);
        
        tutoday.remove(selectedNode);
        tutostart.remove(selectedNode);
        tutoend.remove(selectedNode);
        tutolocation.remove(selectedNode);
        tutostaffid.remove(selectedNode);
        
        labday.remove(selectedNode);
        labstart.remove(selectedNode);
        labend.remove(selectedNode);
        lablocation.remove(selectedNode);
        labstaffid.remove(selectedNode);
        
        vModuleContainer.getChildren().remove(selectedNode);
        System.out.println("Delete success bruh");
        
        
        for (int j = selectedNode; j <= vModuleContainer.getChildren().size(); j++) {
        try {
            this.i = vModuleContainer.getChildren().size();
            System.out.println("Now i is: " + this.i);
            final int h = j;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleDetails.fxml"));
            
            ModuleDetailsController moduleController = loader.getController();
            nodes[h] = loader.load();
            nodes[j] = nodes[j+1];
            nodes[j].setOnMouseEntered(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: transparent");
            });
            nodes[h].setOnMouseExited(evt -> {
                //add effect
                nodes[h].setStyle("-fx-background-color: transparent");
            });
            nodes[h].setOnMousePressed(evt -> {
                //add effect
                    FXMLLoader occloader = new FXMLLoader();
                    occloader.setLocation(getClass().getResource("/Assignment_MayaFOP/addOcc.fxml"));
                try {
                    occloader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    addOccController occControl = occloader.getController();
                    occControl.setDeleteOcc(false);
                   
                    this.selectedNode = h;
                    System.out.println("Now occ contains " + occ.size());
                    System.out.println("Here clicked = " + selectedNode);
                    openNewOccPage();
            });
                    
            } catch (IOException ex) {
                Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    
    public void getPreviousPageValues(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/Module.fxml"));
            loader.load();
            ModuleController previousController = loader.getController();
            
            courseID = previousController.getCourseID().toUpperCase();
            coursename = previousController.getCoursename().toUpperCase();
            credithour = Integer.parseInt(previousController.getCredithour());

            if(previousController.getCourseCategorySetter().equals("University Course")){
                coursecategory = "UC";
            }else if(previousController.getCourseCategorySetter().equals("KELF")){
                coursecategory = "KELF";
            }else if(previousController.getCourseCategorySetter().equals("Programme Core Course")){
                coursecategory = "PCC";
            }else if(previousController.getCourseCategorySetter().equals("Faculty Core Course")){
                coursecategory = "FCC";
            }else if(previousController.getCourseCategorySetter().equals("Faculty Elective Course")){
                coursecategory = "FEC";
            }else if(previousController.getCourseCategorySetter().equals("Specialisation Elective Course")){
                coursecategory = "SEC";
            }

            courseyear = previousController.getCourseYearSetter().toUpperCase();
            coursesem = previousController.getCourseSemSetter().toUpperCase();    
            muetband = previousController.getMuetBandSetter().toUpperCase();
            nationality = previousController.getNationalitySetter().toUpperCase();

            if(previousController.getProgrammeSetter().equals("Software Engineer")){
                programme = "SE";
            }else if(previousController.getProgrammeSetter().equals("Data Science")){
                programme = "DS";
            }else if(previousController.getProgrammeSetter().equals("Artificial Intelligence")){
                programme = "AI";
            }else if(previousController.getProgrammeSetter().equals("Computer System and Networking")){
                programme = "CSN";
            }else if(previousController.getProgrammeSetter().equals("Information System")){
                programme = "IS";
            }else if(previousController.getProgrammeSetter().equals("Multimedia")){
                programme = "MM";
            }else{
                programme = "ALL";
            }
        } catch (IOException ex) {
            Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    //Add new course
    public void confirmAddCourse(ActionEvent event){
        try {
            //add new course
            if(selectedNode == -1){
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
                System.out.println(statement);
                statement.executeUpdate();

                for (int j = 0; j < occ.size(); j++) {
                    String actOcc = occ.get(j);
                    System.out.println(actOcc);

                    String lectID = courseID + "_L" + actOcc;
                    String tutoID = courseID + "_T" + actOcc;
                    String labID = courseID + "_A" + actOcc;

                    if(!lectday.get(j).isEmpty()){
                        PreparedStatement lectstatement = connectDB.prepareStatement("INSERT INTO lecture VALUES (?,?,?,?,?,?)");
                        lectstatement.setString(1,lectID);
                        lectstatement.setString(2,misc.formatFullDay(lectday.get(j)));
                        lectstatement.setString(3,misc.formatFullTime(lectstart.get(j)));
                        lectstatement.setString(4,misc.formatFullTime(lectend.get(j)));
                        lectstatement.setString(5,coursename.toUpperCase() + " LECTURE");
                        lectstatement.setString(6,lectlocation.get(j).toUpperCase());
                        System.out.println(lectstatement);
                        lectstatement.executeUpdate();
                    }

                    if(!tutoday.get(j).isEmpty()){
                        PreparedStatement tutostatement = connectDB.prepareStatement("INSERT INTO tutorial VALUES (?,?,?,?,?,?)");
                        tutostatement.setString(1,tutoID);
                        tutostatement.setString(2,misc.formatFullDay(tutoday.get(j)));
                        tutostatement.setString(3,misc.formatFullTime(tutostart.get(j)));
                        tutostatement.setString(4,misc.formatFullTime(tutoend.get(j)));
                        tutostatement.setString(5,coursename.toUpperCase() + " TUTORIAL");
                        tutostatement.setString(6,tutolocation.get(j).toUpperCase());
                        System.out.println(tutostatement);
                        tutostatement.executeUpdate();
                    }

                    if(!labday.get(j).isEmpty()){
                        PreparedStatement labstatement = connectDB.prepareStatement("INSERT INTO lab VALUES (?,?,?,?,?,?)");
                        labstatement.setString(1,labID);
                        labstatement.setString(2,misc.formatFullDay(labday.get(j)));
                        labstatement.setString(3,misc.formatFullTime(labstart.get(j)));
                        labstatement.setString(4,misc.formatFullTime(labend.get(j)));
                        labstatement.setString(5,coursename.toUpperCase() + " LAB");
                        labstatement.setString(6,lablocation.get(j).toUpperCase());
                        System.out.println(labstatement);
                        labstatement.executeUpdate();
                    }


                    PreparedStatement occStatement = connectDB.prepareStatement("INSERT INTO occ VALUES (?,?,?,?,?,?)");
                    occStatement.setString(1,courseID + "_OCC" + actOcc);
                    occStatement.setString(2,"OCC" + actOcc);
                    if(lectday.get(j) != null){
                        occStatement.setString(3,lectID);
                    }else{
                        occStatement.setString(3,"NONE");
                    }
                    if(tutoday.get(j) != null){
                        occStatement.setString(4,tutoID);
                    }else{
                        occStatement.setString(4,"NONE");
                    }
                    if(labday.get(j) != null){
                        occStatement.setString(5,labID);
                    }else{
                        occStatement.setString(5,"NONE");
                    }
                    occStatement.setString(6,occCapacity.get(j));
                    System.out.println(occStatement);
                    occStatement.executeUpdate();

                    if(!lectstaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachLectureStatement = connectDB.prepareStatement("INSERT INTO staff_teach_lecture VALUES (?,?)");
                        staffTeachLectureStatement.setString(1, lectstaffid.get(j).toUpperCase());
                        staffTeachLectureStatement.setString(2, lectID);
                        staffTeachLectureStatement.executeUpdate();
                        System.out.println(staffTeachLectureStatement);
                        
                        PreparedStatement staffTeachCourseLectStatement = connectDB.prepareStatement("INSERT INTO staff_teach_course VALUES (?,?)");
                        staffTeachCourseLectStatement.setString(1, lectstaffid.get(j).toUpperCase());
                        staffTeachCourseLectStatement.setString(2, courseID);
                        staffTeachCourseLectStatement.executeUpdate();
                    }

                    if(!tutostaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachTutorialStatement = connectDB.prepareStatement("INSERT INTO staff_teach_tutorial VALUES (?,?)");
                        staffTeachTutorialStatement.setString(1, tutostaffid.get(j).toUpperCase());
                        staffTeachTutorialStatement.setString(2, tutoID);
                        staffTeachTutorialStatement.executeUpdate();
                        System.out.println(staffTeachTutorialStatement);
                        
                        PreparedStatement staffTeachCourseTutoStatement = connectDB.prepareStatement("INSERT INTO staff_teach_course VALUES (?,?)");
                        staffTeachCourseTutoStatement.setString(1, tutostaffid.get(j).toUpperCase());
                        staffTeachCourseTutoStatement.setString(2, courseID);
                        staffTeachCourseTutoStatement.executeUpdate();
                    }

                    if(!labstaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachLabStatement = connectDB.prepareStatement("INSERT INTO staff_teach_lab VALUES (?,?)");
                        staffTeachLabStatement.setString(1, labstaffid.get(j).toUpperCase());
                        staffTeachLabStatement.setString(2, labID);
                        staffTeachLabStatement.executeUpdate();
                        System.out.println(staffTeachLabStatement);
                        
                        PreparedStatement staffTeachCourseLabStatement = connectDB.prepareStatement("INSERT INTO staff_teach_course VALUES (?,?)");
                        staffTeachCourseLabStatement.setString(1, labstaffid.get(j).toUpperCase());
                        staffTeachCourseLabStatement.setString(2, courseID);
                        staffTeachCourseLabStatement.executeUpdate();
                    }

                    PreparedStatement courseOccStatement = connectDB.prepareStatement("INSERT INTO course_occ VALUES (?,?)");
                    courseOccStatement.setString(1, courseID);
                    courseOccStatement.setString(2, courseID + "_OCC" + actOcc);
                    courseOccStatement.executeUpdate();
                    System.out.println(courseOccStatement);

                }

                System.out.println("Successfully added!");
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            
            //edit course so need to update
            }else if(selectedNode > -1){
                System.out.println(selectedNode);
                PreparedStatement statement = connectDB.prepareStatement("UPDATE course SET course_name=?, credit_hour=?, course_category=?, course_year=?, course_sem=?, muet_band=?, nationality=?, programme=? WHERE course_id=?");
                statement.setString(1,coursename);
                statement.setInt(2,credithour);
                statement.setString(3,coursecategory);
                statement.setString(4,courseyear);
                statement.setString(5,coursesem);
                statement.setString(6,muetband);
                statement.setString(7,nationality);
                statement.setString(8,programme);
                statement.setString(9,courseID);
                System.out.println(statement);
                statement.executeUpdate();
                
                for (int j = 0; j < occ.size(); j++) {
                    String actOcc = occ.get(j);
                    System.out.println(actOcc);

                    String lectID = courseID + "_L" + actOcc;
                    String tutoID = courseID + "_T" + actOcc;
                    String labID = courseID + "_A" + actOcc;

                    if(!lectday.get(j).isEmpty()){
                        PreparedStatement lectstatement = connectDB.prepareStatement("UPDATE lecture SET lecture_day=?, lecture_start_time=?, lecture_end_time=?, lecture_name=?, lecture_location=? WHERE lecture_id=?");
                        lectstatement.setString(1,misc.formatFullDay(lectday.get(j)));
                        lectstatement.setString(2,misc.formatFullTime(lectstart.get(j)));
                        lectstatement.setString(3,misc.formatFullTime(lectend.get(j)));
                        lectstatement.setString(4,coursename.toUpperCase() + " LECTURE");
                        lectstatement.setString(5,lectlocation.get(j).toUpperCase());
                        lectstatement.setString(6,lectID);
                        System.out.println(lectstatement);
                        lectstatement.executeUpdate();
                    }

                    if(!tutoday.get(j).isEmpty()){
                        PreparedStatement tutostatement = connectDB.prepareStatement("UPDATE tutorial SET tutorial_day=?, tutorial_start_time=?, tutorial_end_time=?, tutorial_name=?, tutorial_location=? WHERE tutorial_id=?");
                        tutostatement.setString(1,misc.formatFullDay(tutoday.get(j)));
                        tutostatement.setString(2,misc.formatFullTime(tutostart.get(j)));
                        tutostatement.setString(3,misc.formatFullTime(tutoend.get(j)));
                        tutostatement.setString(4,coursename.toUpperCase() + " TUTORIAL");
                        tutostatement.setString(5,tutolocation.get(j).toUpperCase());
                        tutostatement.setString(6,tutoID);
                        System.out.println(tutostatement);
                        tutostatement.executeUpdate();
                    }

                    if(!labday.get(j).isEmpty()){
                        PreparedStatement labstatement = connectDB.prepareStatement("UPDATE lab SET lab_day=?, lab_start_time=?, lab_end_time=?, lab_name=?, lab_location=? WHERE lab_id=?");
                        labstatement.setString(1,labID);
                        labstatement.setString(2,misc.formatFullDay(labday.get(j)));
                        labstatement.setString(3,misc.formatFullTime(labstart.get(j)));
                        labstatement.setString(4,misc.formatFullTime(labend.get(j)));
                        labstatement.setString(5,coursename.toUpperCase() + " LAB");
                        labstatement.setString(6,lablocation.get(j).toUpperCase());
                        System.out.println(labstatement);
                        labstatement.executeUpdate();
                    }


                    PreparedStatement occStatement = connectDB.prepareStatement("UPDATE occ SET occ_name=?, lecture_id=?, tutorial_id=?, lab_id=?, occ_capacity=? WHERE occ_id=?");
                    occStatement.setString(1,"OCC" + actOcc);
                    if(lectday.get(j) != null){
                        occStatement.setString(2,lectID);
                    }else{
                        occStatement.setString(2,"NONE");
                    }
                    if(tutoday.get(j) != null){
                        occStatement.setString(3,tutoID);
                    }else{
                        occStatement.setString(3,"NONE");
                    }
                    if(labday.get(j) != null){
                        occStatement.setString(4,labID);
                    }else{
                        occStatement.setString(4,"NONE");
                    }
                    occStatement.setString(5,occCapacity.get(j));
                    occStatement.setString(6,courseID + "_OCC" + actOcc);
                    System.out.println(occStatement);
                    occStatement.executeUpdate();

                    if(!lectstaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachLectureStatement = connectDB.prepareStatement("UPDATE staff_teach_lecture SET staff_id=? WHERE lecture_id=?");
                        staffTeachLectureStatement.setString(1, lectstaffid.get(j).toUpperCase());
                        staffTeachLectureStatement.setString(2, lectID);
                        System.out.println(staffTeachLectureStatement);
                        staffTeachLectureStatement.executeUpdate();
                        
                        PreparedStatement staffTeachCourseLectStatement = connectDB.prepareStatement("UPDATE staff_teach_course SET staff_id=? WHERE course_id=?");
                        staffTeachCourseLectStatement.setString(1, lectstaffid.get(j).toUpperCase());
                        staffTeachCourseLectStatement.setString(2, courseID);
                        staffTeachCourseLectStatement.executeUpdate();
                    }

                    if(!tutostaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachTutorialStatement = connectDB.prepareStatement("UPDATE staff_teach_tutorial SET staff_id=? WHERE tutorial_id=?");
                        staffTeachTutorialStatement.setString(1, tutostaffid.get(j).toUpperCase());
                        staffTeachTutorialStatement.setString(2, tutoID);
                        System.out.println(staffTeachTutorialStatement);
                        staffTeachTutorialStatement.executeUpdate();
                        
                        PreparedStatement staffTeachCourseTutoStatement = connectDB.prepareStatement("UPDATE staff_teach_course SET staff_id=? WHERE course_id=?");
                        staffTeachCourseTutoStatement.setString(1, tutostaffid.get(j).toUpperCase());
                        staffTeachCourseTutoStatement.setString(2, courseID);
                        staffTeachCourseTutoStatement.executeUpdate();
                    }

                    if(!labstaffid.get(j).isEmpty()){
                        PreparedStatement staffTeachLabStatement = connectDB.prepareStatement("UPDATE staff_teach_lab SET staff_id=? WHERE lab_id=?");
                        staffTeachLabStatement.setString(1, labstaffid.get(j).toUpperCase());
                        staffTeachLabStatement.setString(2, labID);
                        System.out.println(staffTeachLabStatement);
                        staffTeachLabStatement.executeUpdate();
                        
                        PreparedStatement staffTeachCourseLabStatement = connectDB.prepareStatement("UPDATE staff_teach_course SET staff_id=? WHERE course_id=?");
                        staffTeachCourseLabStatement.setString(1, labstaffid.get(j).toUpperCase());
                        staffTeachCourseLabStatement.setString(2, courseID);
                        staffTeachCourseLabStatement.executeUpdate();
                    }

                    PreparedStatement courseOccStatement = connectDB.prepareStatement("UPDATE course_occ SET occ_id=? WHERE course_id=?");
                    courseOccStatement.setString(1, courseID + "_OCC" + actOcc);
                    courseOccStatement.setString(2, courseID);
                    courseOccStatement.executeUpdate();
                    System.out.println(courseOccStatement);

                }

                System.out.println("Successfully edited!");
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();               
                
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    
    
    public ArrayList<String> getOcc() {
        return occ;
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


    
    
    
    

    
    
}
