/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

/**
 *
 * @author bhagy
 */
public class TeacherClassPaymentSummaryDto {
    
    private int courseId;
    
    private String courseName;
    
    private double courseFee;
    
    private double totalIncome;
    
    private int totalStudents;
    
    private int totalPaidStudents;

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

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalPaidStudents() {
        return totalPaidStudents;
    }

    public void setTotalPaidStudents(int totalPaidStudents) {
        this.totalPaidStudents = totalPaidStudents;
    }
    
    
        
    
}
