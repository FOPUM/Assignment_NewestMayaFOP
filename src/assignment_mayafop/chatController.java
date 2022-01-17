/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import com.sendemail.SendMail;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ming
 */
public class chatController implements Initializable, ControlledScreen {

    ScreenController myController = new ScreenController();
    
    @FXML
    private TextField contentTextField;

    @FXML
    private TableColumn<chatStudentModel, String> idColumn;

    @FXML
    private Label messageLabel;

    @FXML
    private TableColumn<chatStudentModel, String> nameColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button sendButton;

    @FXML
    private TableView<chatStudentModel> studentTableView;

    @FXML
    private TextField subjectTextField;
    
    @FXML
    private Label emailTargetLabel;

    ObservableList<chatStudentModel> studentStaffObservableList = FXCollections.observableArrayList();
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    private String email;
    private String name;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            String course = "SELECT matric_num, student_name, siswamail FROM student UNION ALL SELECT staff_id, staff_name, staff_email FROM staff";
            ResultSet courseQueryOutput = connectDB.createStatement().executeQuery(course);
            while (courseQueryOutput.next()) {
                String id = courseQueryOutput.getString("matric_num");
                String name = courseQueryOutput.getString("student_name");
                String email = courseQueryOutput.getString("siswamail");
                studentStaffObservableList.add(new chatStudentModel(id, name, email));   
            }

            //PropertyValueFactory corresponds to the new ProductSearchModel fields
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            idColumn.setResizable(false);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setResizable(false);
            studentTableView.setItems(studentStaffObservableList);
            search();

            studentTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    if (!studentTableView.getSelectionModel().equals(null)) {
                        email = "" + studentTableView.getSelectionModel().getSelectedItem().getEmail();
                        emailTargetLabel.setText("To: " + email);
                        name = "" + studentTableView.getSelectionModel().getSelectedItem().getName();
                        
                    }
                    
                }
            });
            } catch (SQLException e) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void search() {
        // Initialise filtered list
        FilteredList<chatStudentModel> filteredData = new FilteredList<>(studentStaffObservableList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(chatStudentModel -> {

                // If no values input, no change to the list
                if (newValue.isEmpty() || newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (chatStudentModel.getId().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Found a match in course name
                } else if (chatStudentModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Found a match in course name
                } else {
                    return false;
                }
            });
        });

        SortedList<chatStudentModel> sortedData = new SortedList<>(filteredData);

        //Bind sorted result with Table View
        sortedData.comparatorProperty().bind(studentTableView.comparatorProperty());
        studentTableView.setItems(sortedData);
    }
    
    SendMail mailSender = new SendMail();
    @FXML
    void sendEmail(ActionEvent event) {
        String content = contentTextField.getText();
        //send email
        sendOtpToForgotter(email,name, content);
        messageLabel.setText("Send successfully");
    }
    
    public void sendOtpToForgotter(String receiver, String name, String content ){
         SendMail mailSender = new SendMail();
         String subject = subjectTextField.getText();
         String mailMessage = content;
         String sender = "mayaFOPUM@gmail.com";
         final String pass = "U2102857@";
         
         mailSender.sendMail(receiver, subject, mailMessage, sender, pass);
         
     }

}
