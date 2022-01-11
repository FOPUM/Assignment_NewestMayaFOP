/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class staffListPopUpController implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    login_controller loginControl = new login_controller();
    
    @FXML
    private Button exit_button;
    @FXML
    private VBox vContainerStaff;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    @FXML
    private ScrollPane staffPane;
    
    boolean upScreenStatus = false;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(staffPane);
        }
        getStaffs();
        insertStaffs();
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
    
    private static ArrayList<String> id = new ArrayList<String>();
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> mail = new ArrayList<String>();
    private static ArrayList<String> cid = new ArrayList<String>();
    private static ArrayList<String> cname = new ArrayList<String>();
    private static ArrayList<String> admin = new ArrayList<String>();
    List<staffListPopUpTextModel> fullStaffDetails = new ArrayList<>();
    
    public void getStaffs(){
        id.clear();
        name.clear();
        mail.clear();
        cid.clear();
        cname.clear();
        admin.clear();

        String staffQueryText="SELECT staff.staff_id,staff.staff_email,staff.staff_name,GROUP_CONCAT(staff_teach_course.course_id) AS course_id,GROUP_CONCAT(course.course_name) AS course_name,admin.admin_id FROM staff\n" +
                                "LEFT JOIN staff_teach_course ON staff.staff_id=staff_teach_course.staff_id\n" +
                                "LEFT JOIN admin ON admin.staff_id=staff.staff_id\n" +
                                "LEFT JOIN course ON course.course_id=staff_teach_course.course_id\n" +
                                "WHERE staff.staff_id!='NONE'\n" +
                                "GROUP BY staff.staff_id";
        try {
            ResultSet staffQuery = connectDB.createStatement().executeQuery(staffQueryText);
            while(staffQuery.next()) {
                id.add(staffQuery.getString("staff_id"));
                name.add(staffQuery.getString("staff_name"));
                mail.add(staffQuery.getString("staff_email"));
                cid.add(staffQuery.getString("course_id"));
                cname.add(staffQuery.getString("course_name"));
                admin.add(staffQuery.getString("admin_id"));
            }
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    
    public void insertStaffs(){
        try {
            fullStaffDetails.clear();
            for (int j = 0; j < id.size(); j++) {
                fullStaffDetails.add(new staffListPopUpTextModel(id.get(j),name.get(j), mail.get(j), cid.get(j),cname.get(j), admin.get(j)));
            }
            Node[] nodes = new Node[fullStaffDetails.size()];

            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/staffListPopUpText.fxml"));
                nodes[j] = loader.load();
                
                staffListPopUpTextController staff = loader.getController();
                //customise content
                staff.setContentInfo( fullStaffDetails.get(j).getStaffIdLabel(), fullStaffDetails.get(j).getStaffNameLabel(), fullStaffDetails.get(j).getUmMailLabel(),
                                        fullStaffDetails.get(j).getCourseIdLabel(), fullStaffDetails.get(j).getCourseNameIdLabel(), fullStaffDetails.get(j).getAdminLabel());               
                
                vContainerStaff.getChildren().add(nodes[j]);
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
