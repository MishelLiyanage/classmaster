/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

import java.time.LocalDate;

/**
 *
 * @author bhagy
 */
public class DailyCourseAttendanceDto {

    private int courseId;
    
    private String courseName;
    
    private LocalDate attendDate;
    
    private int totalAttendandts;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(LocalDate attendDate) {
        this.attendDate = attendDate;
    }

    public int getTotalAttendandts() {
        return totalAttendandts;
    }

    public void setTotalAttendandts(int totalAttendandts) {
        this.totalAttendandts = totalAttendandts;
    }
    
    
    
}
