/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
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
    
    private String occ;
    private String staffID;
    private String lectday;
    private String lectstart;
    private String lectend;
    private String tutoday;
    private String tutostart;
    private String tutoend;
    
    
    ScreenController myController = new ScreenController();
    animation Animation;
    
    addOccController occController = new addOccController();
    
    
    Node[] nodes = new Node[20];
    private int i =0;
    ArrayList<confirmPickedModuleModel> confirmCourses = new ArrayList<>();
    
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
    
    
    public void getValues(){
        
            occ = occController.getOcc();
            staffID = occController.getStaffID();
            lectday = occController.getLectday();
            lectstart = occController.getLectstart();
            lectend = occController.getLectend();
            tutoday = occController.getTutoday();
            tutostart = occController.getTutostart();
            tutoend = occController.getTutoend(); 

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
                nodes[h].setStyle("-fx-background-color: #000000");
                
            });


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
