/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Ming
 */
public class courseRow extends Node{
    

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public courseRow(String coursename) {
        this.coursename = coursename;
    }
    
    String coursename;
    HBox hbox= new HBox();
    Button button = new Button(coursename+"_button");
    Label label = new Label(coursename+"_label");
    
    public void createHbox(){
        hbox.getChildren().add(label);
//        hbox.getChildren().add(button);
//        Image img = new Image("/Assignment_MayaFOP/Images/white exit.png");
//        ImageView view = new ImageView(img);
//        view.setFitHeight(8);
//        view.setPreserveRatio(true);
//        button.setGraphic(view);
    }
}
