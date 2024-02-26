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
public class Attendance {
    
    private int studentId;
    
    private int classId;
    
    private LocalDate attendDate;
    
    private LocalTime attendTime;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public LocalDate getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(LocalDate attend_date) {
        this.attendDate = attend_date;
    }

    public LocalTime getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(LocalTime attend_time) {
        this.attendTime = attend_time;
    }
    
    
    
    
}
