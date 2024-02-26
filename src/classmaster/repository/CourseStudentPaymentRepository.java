/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Course;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.CourseStudentPayment;
import classmaster.models.CourseStudentPaymentDto;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siri
 */
public class CourseStudentPaymentRepository implements Component {

    private DBConnection dBConnection;

    private final String getPaymentQuery = "SELECT cap.studentId, cap.courseId, cap.amount, cap.payingYear, cap.payingMonth, cap.paidDate, c.name as courseName\n"
            + "FROM CourseAssignmentPayment cap join Course c on cap.courseId =c.id\n"
            + "WHERE studentId =?";

    private final String insertPaymentQuery = "INSERT INTO CourseAssignmentPayment\n"
            + "(studentId, courseId, amount, payingYear, payingMonth, paidDate)\n"
            + "VALUES(?, ?, ?, ?, ?, ?)\n"
            + "ON DUPLICATE KEY UPDATE courseId=?,payingYear=?, payingMonth=?;";

    private final String deletePaymentQuery = "DELETE FROM CourseAssignmentPayment\n"
            + "WHERE studentId=? AND courseId=? AND payingYear=? AND payingMonth=?;";

    public CourseStudentPaymentRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public int save(CourseStudentPayment courseStudentPayment) throws SQLException {

        Object[] params = {
            courseStudentPayment.getStudentId(),
            courseStudentPayment.getCourseId(),
            courseStudentPayment.getAmount(),
            courseStudentPayment.getPayingYear(),
            courseStudentPayment.getMonth(),
            courseStudentPayment.getPaidDate(),
            courseStudentPayment.getCourseId(),
            courseStudentPayment.getPayingYear(),
            courseStudentPayment.getMonth()
        };

        return dBConnection.executeUpdate(insertPaymentQuery, params);

    }

    public List<CourseStudentPaymentDto> getStudentPayments(int studentId) throws SQLException {

        List<CourseStudentPaymentDto> payments = new ArrayList<>();

        Object[] params = {studentId};

        ResultSet rs = dBConnection.execute(getPaymentQuery, params);

        while (rs.next()) {
            CourseStudentPaymentDto ca = new CourseStudentPaymentDto();
            ca.setCourseId(rs.getInt("courseId"));
            ca.setStudentId(rs.getInt("studentId"));
            ca.setAmount(rs.getDouble("amount"));
            ca.setPayingYear(rs.getInt("payingYear"));
            ca.setMonth(rs.getInt("payingMonth"));
            ca.setPaidDate(LocalDate.parse(rs.getString("paidDate")));
            ca.setCourseName(rs.getString("courseName"));

            payments.add(ca);
        }

        return payments;
    }

    public int delete(int studentId, int courseId, int payingYear, int payingMonth) throws SQLException {

        Object[] deleteParams = {studentId, courseId, payingYear, payingMonth};
        return this.dBConnection.executeUpdate(deletePaymentQuery, deleteParams);
    }

    @Override
    public String getName() {
        return "CourseStudentPaymentRepository";
    }

}
