/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

import java.time.LocalDate;

public class AttendanceSummary {

    private LocalDate scheduledDate;
    
    private int totalStudent;
    
    private int totalAttendants;

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getTotalAttendants() {
        return totalAttendants;
    }

    public void setTotalAttendants(int totalAttendants) {
        this.totalAttendants = totalAttendants;
    }
    
    
}