/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author bhagy
 */
public class StudentCourseAttendance {
    
    private int courseId;
    
    private String courseName;
    
    private int studentId;
    
    private String firstName;
    
    private String lastName;
    
    private LocalTime courseStartTime;
    
    private String day;
    
    private LocalTime attendTime;
    
    private LocalDate attendDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
    
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalTime getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(LocalTime courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public LocalTime getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(LocalTime attendTime) {
        this.attendTime = attendTime;
    }

    public LocalDate getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(LocalDate attendDate) {
        this.attendDate = attendDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
