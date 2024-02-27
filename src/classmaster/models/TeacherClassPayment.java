/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

/**
 *
 * @author bhagy
 */
public class TeacherClassPayment {
    
    private int teacherId;
    
    private int classId;
    
    private int year;
    
    private int month;
    
    private double totalSalary;
    
    private double totalIncome;
    
    private double totalStudents;
    
    private double totalPaidStudent;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(double totalStudents) {
        this.totalStudents = totalStudents;
    }

    public double getTotalPaidStudent() {
        return totalPaidStudent;
    }

    public void setTotalPaidStudent(double totalPaidStudent) {
        this.totalPaidStudent = totalPaidStudent;
    }
    
    
}
