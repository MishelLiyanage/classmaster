/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Attendance;
import classmaster.models.StudentCourseAttendance;
import classmaster.models.Teacher;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhagy
 */
public class AttendanceRepository implements Component {

    private DBConnection dBConnection;

    public AttendanceRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public int markAttendance(Attendance attendance) throws SQLException {

        String query = "INSERT INTO Attendance (student_id, course_id, attend_date, attend_time)"
                + " VALUES (?, ?, ?, ?)"
                + " ON DUPLICATE KEY UPDATE attend_time = ?";

        Object[] params = {
            attendance.getStudentId(),
            attendance.getClassId(),
            attendance.getAttendDate(),
            attendance.getAttendTime(),
            attendance.getAttendTime()
        };

        return dBConnection.executeUpdate(query, params);

    }

    @Override
    public String getName() {
        return "AttendanceRepository";
    }

    public List<StudentCourseAttendance> findStudentCourseAttendance(int studentId, int courseId) throws SQLException {
        List<StudentCourseAttendance> result = new ArrayList();

        Object[] params = {studentId, courseId};
        ResultSet rs = dBConnection.execute("select a.student_id, a.course_id, a.attend_date, a.attend_time, c.name, c.fromTime, c.day"
                + " from Attendance a inner join Course c on a.course_id = c.id and a.student_id = ? and c.id = ?"
                + " order by a.attend_date desc, a.attend_time desc", params);

        while (rs.next()) {
            StudentCourseAttendance attendance = new StudentCourseAttendance();
            attendance.setCourseId(rs.getInt("course_id"));
            attendance.setStudentId(rs.getInt("student_id"));
            attendance.setAttendDate(LocalDate.parse(rs.getString("attend_date")));
            attendance.setAttendTime(LocalTime.parse(rs.getString("attend_time")));
            attendance.setCourseName(rs.getString("name"));
            attendance.setCourseStartTime(LocalTime.parse(rs.getString("fromTime")));
            attendance.setDay(rs.getString("day"));

            result.add(attendance);

        }

        return result;
    }
    
    public List<StudentCourseAttendance> findAllStudentCourseAttendance(int studentId, int courseId, int year, int month) throws SQLException {
        List<StudentCourseAttendance> result = new ArrayList();

        Object[] params = {studentId, courseId, year, month};
        ResultSet rs = dBConnection.execute("select a.student_id, a.course_id, a.attend_date, a.attend_time, c.name, c.fromTime, c.day"
                + " from Attendance a inner join Course c on a.course_id = c.id and a.student_id = ? and c.id = ?"
                + " where year(a.attend_date) = ? and month(a.attend_date) = ?"
                + " order by a.attend_date desc, a.attend_time desc", params);

        while (rs.next()) {
            StudentCourseAttendance attendance = new StudentCourseAttendance();
            attendance.setCourseId(rs.getInt("course_id"));
            attendance.setStudentId(rs.getInt("student_id"));
            attendance.setAttendDate(LocalDate.parse(rs.getString("attend_date")));
            attendance.setAttendTime(LocalTime.parse(rs.getString("attend_time")));
            attendance.setCourseName(rs.getString("name"));
            attendance.setCourseStartTime(LocalTime.parse(rs.getString("fromTime")));
            attendance.setDay(rs.getString("day"));

            result.add(attendance);

        }

        return result;
    }

    public int deleteAttendace(int studentId, int courseId, LocalDate date) throws SQLException {

        String query = "DELETE FROM Attendance a WHERE a.student_id = ? and a.course_id = ? and a.attend_date = ?";

        Object[] params = {
            studentId,
            courseId,
            date
        };

        return dBConnection.executeUpdate(query, params);

    }

}
