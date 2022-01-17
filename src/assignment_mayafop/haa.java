/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import static assignment_mayafop.searchModule.isOverlapping;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ming
 */
public class haa {
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    Random r = new Random();
    
    private static ArrayList<String> tutorial = new ArrayList<String>();
    private static ArrayList<String> lecture = new ArrayList<String>();
    private static ArrayList<String> labarray = new ArrayList<String>();
    
    
    //occ
//    public void query(){
//       
//        try {
//            ResultSet tut = connectDB.createStatement().executeQuery("SELECT * FROM tutorial");
//            while(tut.next()) {
//                tutorial.add(tut.getString("tutorial_id"));
//            }
////            ResultSet lec = connectDB.createStatement().executeQuery("SELECT * FROM lecture");
////            while(lec.next()) {
////                lecture.add(lec.getString("lecture_id"));
////            }
//            ResultSet lab = connectDB.createStatement().executeQuery("SELECT * FROM lab");
//            while(lab.next()) {
//                labarray.add(lab.getString("lab_id"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        for (int i = 0; i < tutorial.size(); i++) {
//            if(tutorial.get(i).equals("NONE")){
//                continue;
//            }
//            String tutonew = tutorial.get(i);
//            String[] tutonewsplitted = tutonew.split("_");
//            
////            String lectToinsert = "";
////            if(lecture.contains(tutonewsplitted[0] + "_T" + tutonewsplitted[1].substring(1))){
////                lectToinsert = tutonewsplitted[0] + "_T" + tutonewsplitted[1].substring(1);
////            }else{
////                lectToinsert = "NONE";
////            }
//
//            String labToinsert = "";
//            if(labarray.contains(tutonewsplitted[0] + "_A" + tutonewsplitted[1].substring(1))){
//                labToinsert = tutonewsplitted[0] + "_A" + tutonewsplitted[1].substring(1);
//            }else{
//                labToinsert = "NONE";
//            }
//  
//            int occcapacity = (r.nextInt(6)+2) * 10 ;
//            try {
//                PreparedStatement statement = connectDB.prepareStatement("INSERT INTO occ VALUES (?,?,?,?,?,?)");
//                statement.setString(1,tutonewsplitted[0] + "_OCC"+tutonewsplitted[1].substring(1));
//                statement.setString(2,"OCC"+tutonewsplitted[1].substring(1));
//                int haha = r.nextInt(2) - 1;
//                statement.setString(3,tutonewsplitted[0] + "_L1");
//                statement.setString(4,tutonew);
//                statement.setString(5,labToinsert);
//                statement.setInt(6,occcapacity);
//                statement.executeUpdate();
//            } catch(Exception e) {
//                e.printStackTrace();
//                e.getCause();
//            }
//        }
//    }
    
    
//    course_occ
    
//    private static ArrayList<String> occ = new ArrayList<String>();
//    private static ArrayList<String> course = new ArrayList<String>();
//    public void query(){
//       
//        try {
//            ResultSet tut = connectDB.createStatement().executeQuery("SELECT * FROM tutorial");
//            while(tut.next()) {
//                tutorial.add(tut.getString("tutorial_id"));
//            }
//            ResultSet lec = connectDB.createStatement().executeQuery("SELECT * FROM lecture");
//            while(lec.next()) {
//                lecture.add(lec.getString("lecture_id"));
//            }
//            ResultSet lab = connectDB.createStatement().executeQuery("SELECT * FROM lab");
//            while(lab.next()) {
//                labarray.add(lab.getString("lab_id"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        for (int i = 0; i < lecture.size(); i++) {
//            if(lecture.get(i).equals("NONE")){
//                continue;
//            }
//            String lect = lecture.get(i);
//            String[] lectsplitted = lect.split("_");
//            
//            String tutoToinsert = "";
//            String labToinsert = "";
//            if(tutorial.contains(lectsplitted[0] + "_T" + lectsplitted[1].substring(1))){
//                tutoToinsert = lectsplitted[0] + "_T" + lectsplitted[1].substring(1);
//            }else{
//                tutoToinsert = "NONE";
//            }
//
//            if(labarray.contains(lectsplitted[0] + "_A" + lectsplitted[1].substring(1))){
//                labToinsert = lectsplitted[0] + "_A" + lectsplitted[1].substring(1);
//            }else{
//                labToinsert = "NONE";
//            }
//  
//            int occcapacity = (r.nextInt(6)+2) * 10 ;
//            try {
//                PreparedStatement statement = connectDB.prepareStatement("INSERT INTO occ VALUES (?,?,?,?,?,?)");
//                statement.setString(1,lectsplitted[0] + "_OCC"+lectsplitted[1].substring(1));
//                statement.setString(2,"OCC"+lectsplitted[1].substring(1));
//                statement.setString(3,lect);
//                statement.setString(4,tutoToinsert);
//                statement.setString(5,labToinsert);
//                statement.setInt(6,occcapacity);
//                statement.executeUpdate();
//            } catch(Exception e) {
//                e.printStackTrace();
//                e.getCause();
//            }
//        }
//    }
        
//    private static ArrayList<String> occ = new ArrayList<String>();
//    private static ArrayList<String> course = new ArrayList<String>();
//    public void query(){
//       
//        try {
//            ResultSet tut = connectDB.createStatement().executeQuery("SELECT * FROM course");
//            while(tut.next()) {
//                course.add(tut.getString("course_id"));
//            }
//            ResultSet lec = connectDB.createStatement().executeQuery("SELECT * FROM occ");
//            while(lec.next()) {
//                occ.add(lec.getString("occ_id"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        for (int i = 0; i < occ.size(); i++) {
//            String occh = occ.get(i);
//            String[] occsplitted = occh.split("_");
//            
//            String courseToinsert = "";
//            if(course.contains(occsplitted[0])){
//                courseToinsert = occsplitted[0];
//            }else{
//                courseToinsert = "NONE";
//            }
//
//            try {
//                PreparedStatement statement = connectDB.prepareStatement("INSERT INTO course_occ VALUES (?,?)");
//                statement.setString(1,courseToinsert);
//                statement.setString(2,occh);
//                statement.executeUpdate();
//            } catch(Exception e) {
//                e.printStackTrace();
//                e.getCause();
//            }
//        }
//    }
        
       
    private static ArrayList<String> lectid = new ArrayList<String>();
    private static ArrayList<String> lectday = new ArrayList<String>();
    private static ArrayList<String> lectstart = new ArrayList<String>();
    private static ArrayList<String> lectend = new ArrayList<String>();
    
    private static ArrayList<String> checkday = new ArrayList<String>();
    private static ArrayList<String> checkstart = new ArrayList<String>();
    private static ArrayList<String> checkend = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public void query(){
       
        try {
            ResultSet lec = connectDB.createStatement().executeQuery("SELECT * FROM lecture");
            while(lec.next()) {
                lectid.add(lec.getString("lecture_id"));
                lectday.add(lec.getString("lecture_day"));
                lectstart.add(lec.getString("lecture_start_time"));
                lectend.add(lec.getString("lecture_end_time"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < lectid.size(); i++) {
            String lectidnow = lectid.get(i);
            String finalStaff = "";
            do{
                System.out.println("");
                String rr = String.valueOf(r.nextInt(67-1)+1);
                if(rr.length() == 1){
                    rr = "0" + rr;
                }
                String stafftoinsert = "A10" + rr;
                System.out.println("Current staff is " + stafftoinsert);
                System.out.println("Current lecture is " + lectidnow);
                try {
                    checkday.clear();
                    checkstart.clear();
                    checkend.clear();
                    String query = "SELECT * from staff_teach_lab \n" +
                                    "INNER JOIN lab ON staff_teach_lab.lab_id=lab.lab_id\n" +
                                    "WHERE staff_id='"+stafftoinsert+"'\n" +
                                    "\n" +
                                    "UNION ALL \n" +
                                    "\n" +
                                    "SELECT * FROM staff_teach_tutorial\n" +
                                    "INNER JOIN tutorial ON staff_teach_tutorial.tutorial_id=tutorial.tutorial_id\n" +
                                    "WHERE staff_id='"+stafftoinsert+"'\n" +
                                    "\n" +
                                    "UNION ALL\n" +
                                    "\n" +
                                    "SELECT * FROM staff_teach_lecture \n" +
                                    "INNER JOIN lecture ON staff_teach_lecture.lecture_id=lecture.lecture_id\n" +
                                    "WHERE staff_id='"+stafftoinsert+"'";
                    ResultSet labbb = connectDB.createStatement().executeQuery(query);
                    while(labbb.next()) {
                        System.out.println("Lab added");
                        checkday.add(labbb.getString("lab_day"));
                        checkstart.add(labbb.getString("lab_start_time"));
                        checkend.add(labbb.getString("lab_end_time"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("current lectday is " + lectday.get(i));
                if(checkday.size() > 0){ // if he got teaches any courses
                    System.out.println("checkday is not 0");
                    if(checkday.contains(lectday.get(i))){ //if that day he got teaches any courses
                        System.out.println(lectday.get(i) + " is equal to checkday" );
                        try {
                            System.out.println("Reached here");
                            for (int j = 0; j < checkstart.size(); j++) {
                                if(isOverlapping(sdf.parse(lectstart.get(i)), sdf.parse(lectend.get(i)), sdf.parse(checkstart.get(j)), sdf.parse(checkend.get(j)))){
                                    System.out.println("They overlapped");
                                }else{ 
                                    finalStaff = stafftoinsert;     
                                } 
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(haa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{ // if that day he is free
                         finalStaff = stafftoinsert;
                         break;

                    }
                }else{
                    finalStaff = stafftoinsert;
                    System.out.println("Final staff is " + finalStaff);
                    break;
                }
                if(!finalStaff.isEmpty()){
                    System.out.println("Final staff is " + finalStaff);
                    break;
                }
                
            }while(true);
            
            

            try {
                PreparedStatement statement = connectDB.prepareStatement("INSERT INTO staff_teach_lecture VALUES (?,?)");
                statement.setString(1,finalStaff);
                statement.setString(2,lectidnow);
                statement.executeUpdate();
            } catch(Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    
    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }

}
