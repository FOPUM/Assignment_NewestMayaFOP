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
    private TextField emailPassword_Textfield;
    
    @FXML
    private TextField confirmEmailPassword_TextField;

    @FXML
    private TableColumn<chatStudentModel, String> idColumn;

    @FXML
    private Label messageLabel;
    
    @FXML
    private Label sendEmailPageErrorLabel;

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
    login_controller loginControl = new login_controller();
    
    private String receiver_email;
    private String receiver_name;
    String user_matric_num = loginControl.getUsername();
    String user_email;
    String user_name;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendButton.setDisable(true);
        try{
            String query_StaffAndStudent = "SELECT matric_num, student_name, siswamail FROM student UNION ALL SELECT staff_id, staff_name, staff_email FROM staff";
            ResultSet courseQueryOutput = connectDB.createStatement().executeQuery(query_StaffAndStudent);
            while (courseQueryOutput.next()) {
                if (courseQueryOutput.getString("matric_num").equals(user_matric_num)) {
                    user_name = courseQueryOutput.getString("student_name");
                    user_email = courseQueryOutput.getString("siswamail");
                }
                if (!courseQueryOutput.getString("matric_num").equals("NONE") && !courseQueryOutput.getString("matric_num").equals(user_matric_num)) {
                    String id = courseQueryOutput.getString("matric_num");
                    String name = courseQueryOutput.getString("student_name");
                    String email = courseQueryOutput.getString("siswamail");
                    studentStaffObservableList.add(new chatStudentModel(id, name, email));
                }
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
                        receiver_email = "" + studentTableView.getSelectionModel().getSelectedItem().getEmail();
                        emailTargetLabel.setText(receiver_email);
                        receiver_name = "" + studentTableView.getSelectionModel().getSelectedItem().getName();
                        sendButton.setDisable(false);
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
        //send email
        if (!emailPassword_Textfield.getText().equals(confirmEmailPassword_TextField.getText())) {
            sendEmailPageErrorLabel.setText("Password does not match.");
        }else if(emailTargetLabel.getText().isEmpty()){
            sendEmailPageErrorLabel.setText("Please select a receiver.");
        }
        else if(emailPassword_Textfield.getText().isEmpty()){
            sendEmailPageErrorLabel.setText("Please enter password.");
        }
        else{
            SendMail mailSender = new SendMail();
            String receiver = receiver_email;
            String content = contentTextField.getText();
            String subject = subjectTextField.getText();
            String mailMessage = content;
            String sender = user_email;
            final String pass = emailPassword_Textfield.getText();
            mailSender.sendMail(receiver, subject, mailMessage, sender, pass);
            sendEmailPageErrorLabel.setText("");
            messageLabel.setText("Email has been sent successfully");
            sendButton.setDisable(true);
        }
         
         
    }
    
 

}
