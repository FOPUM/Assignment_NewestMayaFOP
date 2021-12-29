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
    private String lectureDay;
    private String lectureStartTime;
    private String lectureEndTime;
    private String lectStaff;

    public modelCourse(String courseID, String courseName, String creditHour, String occID, String occName, String tutoDay, String tutoStartTime, String tutoEndTime, String tutoStaff, String lectureDay, String lectureStartTime, String lectureEndTime, String lectStaff) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.occID = occID;
        this.occName = occName;
        this.tutoDay = tutoDay;
        this.tutoStartTime = tutoStartTime;
        this.tutoEndTime = tutoEndTime;
        this.tutoStaff = tutoStaff;
        this.lectureDay = lectureDay;
        this.lectureStartTime = lectureStartTime;
        this.lectureEndTime = lectureEndTime;
        this.lectStaff = lectStaff;
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

    public String getLectureDay() {
        return lectureDay;
    }

    public void setLectureDay(String lectureDay) {
        this.lectureDay = lectureDay;
    }

    public String getLectureStartTime() {
        return lectureStartTime;
    }

    public void setLectureStartTime(String lectureStartTime) {
        this.lectureStartTime = lectureStartTime;
    }

    public String getLectureEndTime() {
        return lectureEndTime;
    }

    public void setLectureEndTime(String lectureEndTime) {
        this.lectureEndTime = lectureEndTime;
    }

    public String getLectStaff() {
        return lectStaff;
    }

    public void setLectStaff(String lectStaff) {
        this.lectStaff = lectStaff;
    }
//    private String course_id;
//    private String course_name;
//    private String occ_name;
//    private String credit_hour;
//
//    public modelCourse(String course_id, String course_name, String occ_name, String credit_hour) {
//        this.course_id = course_id;
//        this.course_name = course_name;        
//        this.occ_name = occ_name;
//        this.credit_hour = credit_hour;
//    }
//
//    public String getCourse_id() {
//        return course_id;
//    }
//
//    public void setCourse_id(String course_id) {
//        this.course_id = course_id;
//    }
//
//    public String getCourse_name() {
//        return course_name;
//    }
//
//    public void setCourse_name(String course_name) {
//        this.course_name = course_name;
//    }
//
//    public String getOcc_name() {
//        return occ_name;
//    }
//
//    public void setOcc_name(String occ_name) {
//        this.occ_name = occ_name;
//    }
//
//    public String getCredit_hour() {
//        return credit_hour;
//    }
//
//    public void setCredit_hour(String credit_hour) {
//        this.credit_hour = credit_hour;
//    }
    
    
}
