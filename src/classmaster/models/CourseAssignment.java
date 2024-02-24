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
public class CourseAssignment {
    
    private int sudentId;
    
    private int classId;
    
    private LocalDate joinedDate;
    
    private boolean complete;

    public int getSudentId() {
        return sudentId;
    }

    public void setSudentId(int sudentId) {
        this.sudentId = sudentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
    
}
