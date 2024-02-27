/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.ClassPaidStudentsDto;
import classmaster.models.Course;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.CourseStudentPayment;
import classmaster.models.CourseStudentPaymentDto;
import classmaster.models.TeacherClassPaymentSummaryDto;
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

    private final String getClassPaidStudentQuery = "select ca.studentId as student_id, a.first_name, a.last_name,"
            + " IF(cap.amount is not null, \"YES\", \"NO\") as paid,"
            + " cap.paidDate as paid_date, cap.amount as paid_amount"
            + " from CourseAssignment ca "
            + "	left join CourseAssignmentPayment cap on ca.courseId = cap.courseId and ca.studentId = cap.studentId"
            + " and cap.payingYear = ? and payingMonth = ? "
            + " left join Account a on ca.studentId = a.id"
            + " where ca.courseId = ? ";

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

    public List<ClassPaidStudentsDto> getClassStudentPayment(int classId, int year, int month) throws SQLException {

        List<ClassPaidStudentsDto> payments = new ArrayList<>();

        Object[] params = {year, month, classId};

        ResultSet rs = dBConnection.execute(getClassPaidStudentQuery, params);

        while (rs.next()) {
            ClassPaidStudentsDto ca = new ClassPaidStudentsDto();
            ca.setStudentId(rs.getInt("student_id"));
            ca.setFirstName(rs.getString("first_name"));
            ca.setLastName(rs.getString("last_name"));
            ca.setPaid(rs.getString("paid"));
            ca.setPaidAmount(rs.getDouble("paid_amount"));

            if (rs.getString("paid_date") != null && !rs.getString("paid_date").isBlank()) {
                ca.setPaidDate(LocalDate.parse(rs.getString("paid_date")));
            }

            payments.add(ca);
        }

        return payments;

    }

    public TeacherClassPaymentSummaryDto getTeacherClassPayment(int classId, int year, int month) throws SQLException {

        String query = "select ca.courseId, c.name as course_name, c.amount as course_fee, sum(cap.amount) as total_income, count(*) as total_students, count(cap.amount) as paid_students"
                + " FROM CourseAssignment ca"
                + " LEFT JOIN CourseAssignmentPayment cap ON ca.studentId = cap.studentId AND ca.courseId = cap.courseId"
                + " and cap.payingMonth = ? and cap.payingYear = ? LEFT JOIN Course c on ca.courseId = c.id"
                + " where ca.courseId = ?";
        Object[] params = {month, year, classId};
        ResultSet rs = dBConnection.execute(query, params);

        TeacherClassPaymentSummaryDto teacherClassPaymentSummaryDto = null;
        while (rs.next()) {
            teacherClassPaymentSummaryDto = new TeacherClassPaymentSummaryDto();
            teacherClassPaymentSummaryDto.setCourseId(rs.getInt("courseId"));
            teacherClassPaymentSummaryDto.setCourseName(rs.getString("course_name"));
            teacherClassPaymentSummaryDto.setCourseFee(rs.getDouble("course_fee"));
            teacherClassPaymentSummaryDto.setTotalIncome(rs.getDouble("total_income"));
            teacherClassPaymentSummaryDto.setTotalStudents(rs.getInt("total_students"));
            teacherClassPaymentSummaryDto.setTotalPaidStudents(rs.getInt("paid_students"));
        }
        return teacherClassPaymentSummaryDto;
    }

    public List<TeacherClassPaymentSummaryDto> getTeacherMonthlyPaymentSummary(int teacherId, int year, int month) throws SQLException {

        String query = "select ca.courseId, c.name as course_name, c.amount as course_fee, sum(cap.amount) as total_income, count(*) as total_students, count(cap.amount) as paid_students"
                + " FROM CourseAssignment ca"
                + " LEFT JOIN CourseAssignmentPayment cap ON ca.studentId = cap.studentId AND ca.courseId = cap.courseId"
                + " and cap.payingMonth = ? and cap.payingYear = ? LEFT JOIN Course c on ca.courseId = c.id"
                + " where c.teacherId = ?"
                + " group by ca.courseId, c.name, c.amount;";
        Object[] params = {month, year, teacherId};
        ResultSet rs = dBConnection.execute(query, params);

        List<TeacherClassPaymentSummaryDto> result = new ArrayList<>();

        while (rs.next()) {
            TeacherClassPaymentSummaryDto teacherClassPaymentSummaryDto = new TeacherClassPaymentSummaryDto();
            teacherClassPaymentSummaryDto.setCourseId(rs.getInt("courseId"));
            teacherClassPaymentSummaryDto.setCourseName(rs.getString("course_name"));
            teacherClassPaymentSummaryDto.setCourseFee(rs.getDouble("course_fee"));
            teacherClassPaymentSummaryDto.setTotalIncome(rs.getDouble("total_income"));
            teacherClassPaymentSummaryDto.setTotalStudents(rs.getInt("total_students"));
            teacherClassPaymentSummaryDto.setTotalPaidStudents(rs.getInt("paid_students"));
            result.add(teacherClassPaymentSummaryDto);
        }
        return result;

    }

    public List<TeacherClassPaymentSummaryDto> getTeacherAnnualPaymentSummary(int teacherId, int year) throws SQLException {

        String query = "select ca.courseId, c.name as course_name, c.amount as course_fee, cap.payingMonth as paying_month,"
                + " sum(cap.amount) as total_income, count(*) as total_students, count(cap.amount) as paid_students"
                + " FROM CourseAssignment ca"
                + " INNER JOIN CourseAssignmentPayment cap ON ca.studentId = cap.studentId AND ca.courseId = cap.courseId"
                + " and cap.payingYear = ? INNER JOIN Course c on ca.courseId = c.id"
                + " where c.teacherId = ?"
                + " group by ca.courseId, c.name, c.amount, cap.payingMonth"
                + " order by cap.payingMonth";
        Object[] params = { year, teacherId};
        ResultSet rs = dBConnection.execute(query, params);

        List<TeacherClassPaymentSummaryDto> result = new ArrayList<>();

        while (rs.next()) {
            TeacherClassPaymentSummaryDto teacherClassPaymentSummaryDto = new TeacherClassPaymentSummaryDto();
            teacherClassPaymentSummaryDto.setCourseId(rs.getInt("courseId"));
            teacherClassPaymentSummaryDto.setCourseName(rs.getString("course_name"));
            teacherClassPaymentSummaryDto.setCourseFee(rs.getDouble("course_fee"));
            teacherClassPaymentSummaryDto.setMonth(rs.getInt("paying_month"));
            teacherClassPaymentSummaryDto.setTotalIncome(rs.getDouble("total_income"));
            teacherClassPaymentSummaryDto.setTotalStudents(rs.getInt("total_students"));
            teacherClassPaymentSummaryDto.setTotalPaidStudents(rs.getInt("paid_students"));
            result.add(teacherClassPaymentSummaryDto);
        }
        return result;

    }

}
