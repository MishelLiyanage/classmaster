/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Course;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.CourseStudentPayment;
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

    public CourseStudentPaymentRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public int save(CourseStudentPayment courseStudentPayment) throws SQLException {

        String query = "INSERT INTO CourseAssignmentPayment\n"
                + "(studentId, courseId, amount, payingYear, payingMonth, paidDate)\n"
                + "VALUES(?, ?, ?, ?, ?, ?);";

        Object[] params = {
            courseStudentPayment.getStudentId(),
            courseStudentPayment.getCourseId(),
            courseStudentPayment.getAmount(),
            courseStudentPayment.getPayingYear(),
            courseStudentPayment.getMonth(),
            courseStudentPayment.getPaidDate()
        };

        return dBConnection.executeUpdate(query, params);

    }

    public List<CourseStudentPayment> getStudentPayments(int studentId) throws SQLException {

        List<CourseStudentPayment> payments = new ArrayList<>();

        Object[] params = {studentId};

        String getPaymentQuery = "SELECT studentId, courseId, amount, payingYear, payingMonth, paidDate\n"
                + "FROM CourseAssignmentPayment\n"
                + "WHERE studentId =?";
        ResultSet rs = dBConnection.execute(getPaymentQuery, params);

        while (rs.next()) {
            CourseStudentPayment ca = new CourseStudentPayment();
            ca.setCourseId(rs.getInt("courseId"));
            ca.setStudentId(rs.getInt("studentId"));
            ca.setAmount(rs.getDouble("amount"));
            ca.setPayingYear(rs.getInt("payingYear"));
            ca.setMonth(rs.getInt("payingMonth"));
            ca.setPaidDate(LocalDate.parse(rs.getString("paidDate")));

            payments.add(ca);
        }

        return payments;
    }

    @Override
    public String getName() {
        return "CourseStudentPaymentRepository";
    }

}
