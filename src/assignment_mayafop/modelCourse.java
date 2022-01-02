/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

/**
 *
 * @author Ming
 */
public class modelCourse {
    private String courseID;
    private String courseName;
    private String creditHour;
    private String occID;
    private String occName;
    private String tutoDay;
    private String tutoStartTime;
    private String tutoEndTime;
    private String tutoStaff;
    private String tutoLocation;
    private String lectDay;
    private String lectStartTime;
    private String lectEndTime;
    private String lectStaff;
    private String lectLocation;
    private String labDay;
    private String labStartTime;
    private String labEndTime;
    private String labStaff;
    private String labLocation;

    public modelCourse(String courseID, String courseName, String creditHour, String occID, String occName, String tutoDay, String tutoStartTime, String tutoEndTime, String tutoStaff, String tutoLocation, String lectDay, String lectStartTime, String lectEndTime, String lectStaff, String lectLocation, String labDay, String labStartTime, String labEndTime, String labStaff, String labLocation) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.occID = occID;
        this.occName = occName;
        this.tutoDay = tutoDay;
        this.tutoStartTime = tutoStartTime;
        this.tutoEndTime = tutoEndTime;
        this.tutoStaff = tutoStaff;
        this.tutoLocation = tutoLocation;
        this.lectDay = lectDay;
        this.lectStartTime = lectStartTime;
        this.lectEndTime = lectEndTime;
        this.lectStaff = lectStaff;
        this.lectLocation = lectLocation;
        this.labDay = labDay;
        this.labStartTime = labStartTime;
        this.labEndTime = labEndTime;
        this.labStaff = labStaff;
        this.labLocation = labLocation;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(String creditHour) {
        this.creditHour = creditHour;
    }

    public String getOccID() {
        return occID;
    }

    public void setOccID(String occID) {
        this.occID = occID;
    }

    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public String getTutoDay() {
        return tutoDay;
    }

    public void setTutoDay(String tutoDay) {
        this.tutoDay = tutoDay;
    }

    public String getTutoStartTime() {
        return tutoStartTime;
    }

    public void setTutoStartTime(String tutoStartTime) {
        this.tutoStartTime = tutoStartTime;
    }

    public String getTutoEndTime() {
        return tutoEndTime;
    }

    public void setTutoEndTime(String tutoEndTime) {
        this.tutoEndTime = tutoEndTime;
    }

    public String getTutoStaff() {
        return tutoStaff;
    }

    public void setTutoStaff(String tutoStaff) {
        this.tutoStaff = tutoStaff;
    }

    public String getLectDay() {
        return lectDay;
    }

    public void setLectDay(String lectDay) {
        this.lectDay = lectDay;
    }

    public String getLectStartTime() {
        return lectStartTime;
    }

    public void setLectStartTime(String lectStartTime) {
        this.lectStartTime = lectStartTime;
    }

    public String getLectEndTime() {
        return lectEndTime;
    }

    public void setLectEndTime(String lectEndTime) {
        this.lectEndTime = lectEndTime;
    }

    public String getLectStaff() {
        return lectStaff;
    }

    public void setLectStaff(String lectStaff) {
        this.lectStaff = lectStaff;
    }    

    public String getTutoLocation() {
        return tutoLocation;
    }

    public void setTutoLocation(String tutoLocation) {
        this.tutoLocation = tutoLocation;
    }

    public String getLectLocation() {
        return lectLocation;
    }

    public void setLectLocation(String lectLocation) {
        this.lectLocation = lectLocation;
    }

    public String getLabDay() {
        return labDay;
    }

    public void setLabDay(String labDay) {
        this.labDay = labDay;
    }

    public String getLabStartTime() {
        return labStartTime;
    }

    public void setLabStartTime(String labStartTime) {
        this.labStartTime = labStartTime;
    }

    public String getLabEndTime() {
        return labEndTime;
    }

    public void setLabEndTime(String labEndTime) {
        this.labEndTime = labEndTime;
    }

    public String getLabStaff() {
        return labStaff;
    }

    public void setLabStaff(String labStaff) {
        this.labStaff = labStaff;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }
    
    
    
}
