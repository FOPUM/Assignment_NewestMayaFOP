/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class userAccount implements Initializable, ControlledScreen{
    
    ScreenController myController;
    login_controller loginControl = new login_controller();
    
    @FXML
    private Button exit_button;
    @FXML
    private BorderPane userScreen;
    @FXML
    private VBox vContainerRegisteredModule;
    @FXML
    private Label academicYearLabel;
    @FXML
    private Label enrollmentStatusLabel;
    @FXML
    private Label matricIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label programmeLabel;
    @FXML
    private Label specialisationLabel;
    
    boolean showing;
    
    
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    

    private static ArrayList<String> courseID = new ArrayList<String>();
    private static ArrayList<String> courseName = new ArrayList<String>();
    List<registeredModuleDetailsTextModel> moduleDetails = new ArrayList<>();
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        getUserDetails();
        getCourseDetails();
        insertModuleDetails();
        
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
//    public void goToRegisteredModule(ActionEvent event)throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("registeredModule.fxml"));
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
    
    public void goToRegisteredModule(ActionEvent event){
        myController = new ScreenController();
        myController.showPopupStage(userScreen, "/assignment_MayaFOP/registeredModule.fxml");
    } 

    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
    
    public void insertModuleDetails(){
        try {
            
            for (int j = 0; j < courseID.size(); j++) {
                moduleDetails.add(new registeredModuleDetailsTextModel(courseID.get(j),upperLetter(courseName.get(j))));
            }
//            moduleDetails.add(new registeredModuleDetailsTextModel("WIX1002","MAth"));
//            moduleDetails.add(new registeredModuleDetailsTextModel("WIX1003","Mothhh"));
            
            Node[] nodes = new Node[moduleDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredModuleDetailsText.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                registeredModuleDetailsTextController detailsController = loader.getController();
                //customise content
                detailsController.setContentInfo(moduleDetails.get(j).getCourseCodeDetailsLabel(),moduleDetails.get(j).getCourseNameDetailsLabel());               
                
//                nodes[h].setOnMouseEntered(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #b4baca");
//                });
//                nodes[h].setOnMouseExited(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: transparent");
//                });
//                nodes[h].setOnMousePressed(evt -> {
//                    //add effect
//                });

                vContainerRegisteredModule.getChildren().add(nodes[j]);
            }
          


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void getUserDetails(){
        try {
            String UserDetails="SELECT student_name, matric_num, student_programme, student_specialisation, student_studyyear, enrolled_status\n" +
                            "FROM student\n" +
                            "WHERE matric_num='"+matric_num+"'";
            ResultSet queryResultForCheck = connectDB.createStatement().executeQuery(UserDetails);
            while(queryResultForCheck.next()) {

                nameLabel.setText(upperLetter(queryResultForCheck.getString("student_name")));
                
                matricIDLabel.setText(queryResultForCheck.getString("matric_num").toUpperCase());              
                
                String studentProgrammeTemp = queryResultForCheck.getString("student_programme");
                System.out.println(studentProgrammeTemp);
                if (studentProgrammeTemp.equals("SE")) {
                    programmeLabel.setText("Software Engineering");
                } else if (studentProgrammeTemp.equals("AI")) {
                    programmeLabel.setText("Artificial Intelligence");
                }else if (studentProgrammeTemp.equals("DS")) {
                    programmeLabel.setText("Data Science");
                }else if (studentProgrammeTemp.equals("CSN")) {
                    programmeLabel.setText("Computer System and Networking");
                }else if (studentProgrammeTemp.equals("IS")) {
                    programmeLabel.setText("Information System");
                }else if (studentProgrammeTemp.equals("MM")) {
                    programmeLabel.setText("Multimedia");
                }
                
                if(queryResultForCheck.getString("student_specialisation") == null){
                    specialisationLabel.setText("-");
                }
  
                academicYearLabel.setText(queryResultForCheck.getString("student_studyyear"));

                if (queryResultForCheck.getString("enrolled_status").equals("Y")) {
                    enrollmentStatusLabel.setText("Enrolled");
                } else if (queryResultForCheck.getString("enrolled_status").equals("N")) {
                    enrollmentStatusLabel.setText("Not enrolled");
                }
                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    public void getCourseDetails(){
        try {
            String registeredModuleCourseIDQuery="SELECT course_id FROM\n" +
                                "student_take_course WHERE matric_num='"+matric_num+"' AND course_status='Y'";
            
            ResultSet courseIDQuery = connectDB.createStatement().executeQuery(registeredModuleCourseIDQuery);
            while(courseIDQuery.next()) {
                String courseid = courseIDQuery.getString("course_id");
                courseID.add(courseid);
            }
            
            
            for (int j = 0; j < courseID.size(); j++) {
                String courseIDFromArray = courseID.get(j);
                String registeredModuleCourseNameQuery="SELECT course_name FROM course WHERE course_id='"+courseIDFromArray+"'";
                
                ResultSet courseNameQuery = connectDB.createStatement().executeQuery(registeredModuleCourseNameQuery);
                while(courseNameQuery.next()) {
                    String coursename = courseNameQuery.getString("course_name");
                    courseName.add(coursename);
                }
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    public String upperLetter(String name){
        String[] stringTemp = name.split(" ");
        String modifiedString=" ";
        for (int i = 0; i < stringTemp.length; i++) {
            
            String firstLetStr = stringTemp[i].substring(0, 1);
            String remLetStr = stringTemp[i].substring(1);
            if(!stringTemp[i].equals("and")){
                firstLetStr = firstLetStr.toUpperCase();
            }
            remLetStr = remLetStr.toLowerCase();
            if(modifiedString.equals(" ")){
                modifiedString = firstLetStr + remLetStr + " ";
            }else{
                modifiedString += firstLetStr + remLetStr + " ";
            }
        }

        return modifiedString;
    }
    
}
