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
        getPreviousPageValues();
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
            
            int actOcc = selectedNode + 1;
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
    
    public void getValues(){
        
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
            int actOcc = i + 1;
            
            moduleController.occLabel.setText(occ.get(i));
            moduleController.capacityLabel.setText(occCapacity.get(i));
            
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
            moduleController.tutoStaffIDLabel.setText(lectstaffid.get(i));
            
            moduleController.labIDLabel.setText(courseID + "_A" + actOcc);
            moduleController.labDayLabel.setText(labday.get(i));
            moduleController.labStartTimeLabel.setText(labstart.get(i));
            moduleController.labEndTimeLabel.setText(labend.get(i));
            moduleController.labLocationLabel.setText(lablocation.get(i));
            moduleController.labStaffIDLabel.setText(lectstaffid.get(i));
            
            
            
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

            courseyear = previousController.getCourseYearSetter();
            coursesem = previousController.getCourseSemSetter();    
            muetband = previousController.getMuetBandSetter();
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
            }else if(previousController.getProgrammeSetter().equals("ALL")){
                programme = "ALL";
            }
        } catch (IOException ex) {
            Logger.getLogger(ModuleNextController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    //Add new course
    public void confirmAddCourse(ActionEvent event){
        
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
            
            for (int j = 0; j < occ.size(); j++) {
                int actOcc = Integer.parseInt(occ.get(j)) + 1;
                String lectID = courseID + "_L" + actOcc;
                String tutoID = courseID + "_T" + actOcc;
                String labID = courseID + "_A" + actOcc;
                   
                if(lectday.size()>0){
                    PreparedStatement lectstatement = connectDB.prepareStatement("INSERT INTO lecture VALUES (?,?,?,?,?,?)");
                    lectstatement.setString(1,lectID);
                    lectstatement.setString(2,lectday.get(j).toUpperCase());
                    lectstatement.setString(3,lectstart.get(j));
                    lectstatement.setString(4,lectend.get(j));
                    lectstatement.setString(5,coursename.toUpperCase() + " LECTURE");
                    lectstatement.setString(6,lectlocation.get(j));
                    lectstatement.executeUpdate();
                }
                
                if(tutoday.size() > 0){
                    PreparedStatement tutostatement = connectDB.prepareStatement("INSERT INTO tutorial VALUES (?,?,?,?,?,?)");
                    tutostatement.setString(1,tutoID);
                    tutostatement.setString(2,tutoday.get(j).toUpperCase());
                    tutostatement.setString(3,tutostart.get(j));
                    tutostatement.setString(4,tutoend.get(j));
                    tutostatement.setString(5,coursename.toUpperCase() + " TUTORIAL");
                    tutostatement.setString(6,tutolocation.get(j));
                    tutostatement.executeUpdate();
                }
                
                if(labday.size() > 0){
                    PreparedStatement labstatement = connectDB.prepareStatement("INSERT INTO lab VALUES (?,?,?,?,?,?)");
                    labstatement.setString(1,labID);
                    labstatement.setString(2,labday.get(j).toUpperCase());
                    labstatement.setString(3,labstart.get(j));
                    labstatement.setString(4,labend.get(j));
                    labstatement.setString(5,coursename.toUpperCase() + " LAB");
                    labstatement.setString(6,lablocation.get(j));
                    labstatement.executeUpdate();
                }
                
                
                PreparedStatement occStatement = connectDB.prepareStatement("INSERT INTO occ VALUES (?,?,?,?,?,?)");
                occStatement.setString(1,courseID + "_OCC" + actOcc);
                occStatement.setString(2,"OCC" + actOcc);
                if(lectID != null){
                    occStatement.setString(3,lectID);
                }else{
                    occStatement.setString(3,"NONE");
                }
                if(tutoID != null){
                    occStatement.setString(4,tutoID);
                }else{
                    occStatement.setString(4,"NONE");
                }
                if(labID != null){
                    occStatement.setString(5,labID);
                }else{
                    occStatement.setString(5,"NONE");
                }
                occStatement.setString(6,occCapacity.get(j));
                occStatement.executeUpdate();
                
                if(lectID != null){
                    PreparedStatement staffTeachLectureStatement = connectDB.prepareStatement("INSERT INTO staff_teach_lecture VALUES (?,?)");
                    staffTeachLectureStatement.setString(1, lectstaffid.get(j));
                    staffTeachLectureStatement.setString(2, lectID);
                    staffTeachLectureStatement.executeUpdate();
                }
                
                if(tutoID != null){
                    PreparedStatement staffTeachTutorialStatement = connectDB.prepareStatement("INSERT INTO staff_teach_tutorial VALUES (?,?)");
                    staffTeachTutorialStatement.setString(1, tutostaffid.get(j));
                    staffTeachTutorialStatement.setString(2, tutoID);
                    staffTeachTutorialStatement.executeUpdate();
                }
                
                if(labID != null){
                    PreparedStatement staffTeachLabStatement = connectDB.prepareStatement("INSERT INTO staff_teach_lab VALUES (?,?)");
                    staffTeachLabStatement.setString(1, labstaffid.get(j));
                    staffTeachLabStatement.setString(2, labID);
                    staffTeachLabStatement.executeUpdate();
                }
                
                PreparedStatement courseOccStatement = connectDB.prepareStatement("INSERT INTO course_occ VALUES (?,?)");
                courseOccStatement.setString(1, courseID);
                courseOccStatement.setString(2, courseID + "_OCC" + actOcc);
                courseOccStatement.executeUpdate();

            }
            
            System.out.println("Successfully added!");
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
            
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
