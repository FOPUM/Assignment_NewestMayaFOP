/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import static assignment_mayafop.searchModule.coursesModel;
import static assignment_mayafop.searchModule.creditHour;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class registeredModuleController implements Initializable, ControlledScreen{
    ScreenController myController = new ScreenController();
    animation Animation;
    login_controller loginControl = new login_controller();
    MiscFunc misc = new MiscFunc();
    
    @FXML
    private Button exit_button;
    @FXML
    private VBox vContainersPopUpRegisteredModule;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    private static ArrayList<String> academicYear = new ArrayList<String>();
    private static ArrayList<String> periodSlot = new ArrayList<String>();
    private static ArrayList<String> courseCode = new ArrayList<String>();
    private static ArrayList<String> courseName = new ArrayList<String>();
    private static ArrayList<String> creditHour = new ArrayList<String>();
    private static ArrayList<String> occurence = new ArrayList<String>();
    private static ArrayList<String> enrollmentStatus = new ArrayList<String>();
    List<registeredModuleDetailsPopupModel> moduleDetails = new ArrayList<>();
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();

    @FXML
    private ScrollPane registeredModuleScreen;
    
    boolean upScreenStatus = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(registeredModuleScreen);
        }
        getCourseDetails();
        insertModuleDetails();
        
        
        
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
    public void getCourseDetails(){
        try {
            academicYear.clear();
            periodSlot.clear();
            courseCode.clear();
            courseName.clear();
            creditHour.clear();
            occurence.clear();
            enrollmentStatus.clear();
            String courseDetailss="SELECT student.student_studyyear, student.student_studysem, course.course_id, course.course_name, \n" +
                                "course.credit_hour, occ.occ_name, student_take_course.course_status FROM occ\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course.course_id=course_occ.course_id\n" +
                                "INNER JOIN student_take_course ON course_occ.occ_id=student_take_course.occ_id\n" +
                                "INNER JOIN student ON student_take_course.matric_num=student.matric_num\n" +
                                "WHERE student.matric_num='"+matric_num+"'";
            ResultSet queryCourseDetail = connectDB.createStatement().executeQuery(courseDetailss);
            while(queryCourseDetail.next()) {
                String acayear = queryCourseDetail.getString("student_studyyear");
                if(acayear.equals("1")){
                    academicYear.add("2021");
                }
                
                String period = queryCourseDetail.getString("student_studysem");
                if(period.equals("1")){
                    periodSlot.add("S1");
                }
                
                courseCode.add(queryCourseDetail.getString("course_id"));
                
                courseName.add(misc.upperLetter(queryCourseDetail.getString("course_name")));
                creditHour.add(queryCourseDetail.getString("credit_hour"));
                
                String occ = queryCourseDetail.getString("occ_name");
                occ = occ.substring(3);
                occurence.add(occ);
                
                if(queryCourseDetail.getString("course_status").equals("Y")){
                    enrollmentStatus.add("Enrolled");
                }else{
                    enrollmentStatus.add("Not enrolled");
                }
            }
            
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    static boolean confirmedDrop = false;
    boolean showing;
    public void insertModuleDetails(){
        try {
            moduleDetails.clear();
            for (int j = 0; j < courseCode.size(); j++) {
                moduleDetails.add(new registeredModuleDetailsPopupModel(academicYear.get(j),periodSlot.get(j), courseCode.get(j), courseName.get(j), creditHour.get(j), occurence.get(j), enrollmentStatus.get(j)));
            }
            
            Node[] nodes = new Node[moduleDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredModuleDetailsPopup.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                registeredModuleDetailsPopupController detailsController = loader.getController();
                //customise content
                detailsController.setContentInfo(moduleDetails.get(j).getAcademicYearLabel(),
                        moduleDetails.get(j).getPeriodSlotLabel(),
                        moduleDetails.get(j).getCourseCodeLabel(),
                        moduleDetails.get(j).getCourseNameLabel(),
                        moduleDetails.get(j).getCreditHourLabel(),
                        moduleDetails.get(j).getOccLabel(),
                        moduleDetails.get(j).getStatusLabel());               
                    
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
                    if (!showing) {
                        myController.showPopupStage(registeredModuleScreen, "/assignment_MayaFOP/dropModule.fxml");
                        showing = myController.getShowing();   
                    }
                    System.out.println(confirmedDrop);
                    if(confirmedDrop){
                        vContainersPopUpRegisteredModule.getChildren().remove(nodes[h]);
                        dropModule(h);
                        
                    }

                });
                vContainersPopUpRegisteredModule.getChildren().add(nodes[j]);
            }
          


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void dropModule(int i){
        
        try {
            PreparedStatement statement = connectDB.prepareStatement("DELETE FROM student_take_course WHERE course_id=? AND matric_num=?");
            statement.setString(1,courseCode.get(i));
            statement.setString(2,matric_num);
            statement.executeUpdate();
            
            //Get the current credit hour of student
            int creditHour = 0;
            ResultSet creditHourGetter = connectDB.createStatement().executeQuery("SELECT credit_hour FROM student WHERE matric_num='"+matric_num+"'");
            while (creditHourGetter.next()) {
                creditHour = creditHourGetter.getInt("credit_hour");
            }
            
            //Get the credit hour of course
            ResultSet creditHourSubtractGetter = connectDB.createStatement().executeQuery("SELECT credit_hour FROM course WHERE course_id='"+courseCode.get(i)+"'");
            while (creditHourSubtractGetter.next()) {
                creditHour -= creditHourSubtractGetter.getInt("credit_hour");
            }
            
            //Minus the credit hour
            PreparedStatement creditstatement = connectDB.prepareStatement("UPDATE student SET credit_hour=? WHERE matric_num=?");
            creditstatement.setInt(1,creditHour);
            creditstatement.setString(2,matric_num);
            creditstatement.executeUpdate();
            System.out.println("Successfully drop " + courseCode.get(i));
            
        } catch (SQLException ex) {
            Logger.getLogger(registeredModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public void setConfirmedDrop(boolean confirmedDrop) {
        this.confirmedDrop = confirmedDrop;
    }
    
    
    
    
}
