/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

/**
 *
 * @author Siri
 */
public class CourseStudentPaymentDto extends CourseStudentPayment{
    private String courseName;
    private int paidYear;
    private int paidMonth;
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPaidYear() {
        return paidYear;
    }

    public void setPaidYear(int paidYear) {
        this.paidYear = paidYear;
    }

    public int getPaidMonth() {
        return paidMonth;
    }

    public void setPaidMonth(int paidMonth) {
        this.paidMonth = paidMonth;
    }
}
