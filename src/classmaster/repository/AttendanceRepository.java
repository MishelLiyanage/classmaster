/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Attendance;
import classmaster.models.DailyCourseAttendanceDto;
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

        Object[] params = {year, month, courseId, studentId};
        ResultSet rs = dBConnection.execute("with CourseDates as ("
                + " select distinct a.attend_date as scheduled_date"
                + " from Attendance a"
                + " where year(a.attend_date) = ? and month(a.attend_date) = ? and a.course_id = ?"
                + " )"
                + " select cd.*, a.student_id , a.course_id , c.name, c.fromTime, a.attend_date , a.attend_time"
                + " from CourseDates cd left join Attendance a on cd.scheduled_date = a.attend_date and a.student_id = ?"
                + "	left join Course c on a.course_id = c.id ", params);

        while (rs.next()) {
            StudentCourseAttendance attendance = new StudentCourseAttendance();
            attendance.setCourseId(rs.getInt("course_id"));
            attendance.setStudentId(rs.getInt("student_id"));
            if(rs.getString("attend_date") != null){
                attendance.setAttendDate(LocalDate.parse(rs.getString("attend_date")));
            }
            
            if(rs.getString("attend_time") != null){
                attendance.setAttendTime(LocalTime.parse(rs.getString("attend_time")));
            }
            
            if(rs.getString("name") != null){
                attendance.setCourseName(rs.getString("name"));
            }
            
            if(rs.getString("fromTime") != null){
                attendance.setCourseStartTime(LocalTime.parse(rs.getString("fromTime")));
            }
            
//            attendance.setDay(rs.getString("day"));
            
            if(rs.getString("scheduled_date") != null){
                attendance.setScheduledDate(LocalDate.parse(rs.getString("scheduled_date")));
            }
            

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

    public List<StudentCourseAttendance> getAttendanceForCourse(int courseId, LocalDate scheduledDate) throws SQLException {

        String query = "with CourseDates as ("
                + " select ? as attend_date"
                + " ),"
                + " StudentAllAttendance as ("
                + " select ca.courseId, ca.studentId, cd.attend_date as scheduled_date"
                + " from CourseAssignment ca cross join CourseDates cd"
                + " where ca.courseId = ? )"
                + " select ssa.courseId as course_id, ssa.studentId as student_id, acc.first_name, acc.last_name ,ssa.scheduled_date, a.attend_date, a.attend_time"
                + " from StudentAllAttendance ssa"
                + "	left join Attendance a on ssa.studentId = a.student_id and ssa.courseId = a.course_id"
                + "		and ssa.scheduled_date = a.attend_date"
                + " left join Account acc on ssa.studentId = acc.id"
                + " order by ssa.studentId, ssa.scheduled_date";

        Object[] params = {scheduledDate, courseId};

        List<StudentCourseAttendance> result = new ArrayList();

        ResultSet rs = dBConnection.execute(query, params);

        while (rs.next()) {
            StudentCourseAttendance attendance = new StudentCourseAttendance();
            attendance.setCourseId(rs.getInt("course_id"));
            attendance.setStudentId(rs.getInt("student_id"));
            attendance.setFirstName(rs.getString("first_name"));
            attendance.setLastName(rs.getString("last_name"));
            if (rs.getString("attend_date") != null) {
                attendance.setAttendDate(LocalDate.parse(rs.getString("attend_date")));
            }
            if (rs.getString("attend_time") != null) {
                attendance.setAttendTime(LocalTime.parse(rs.getString("attend_time")));
            }

            result.add(attendance);
        }

        return result;
    }

    public List<DailyCourseAttendanceDto> getDailyAttendanceSummary(int teacherId, int year, int month) throws SQLException {

        String query = "select c.id as course_id, c.name as course_name, a.attend_date, count(a.attend_date) as count"
                + " from Attendance a inner join Course c on a.course_id = c.id and c.teacherId = ?"
                + " where year(a.attend_date) = ? and month(a.attend_date) = ?"
                + " group by c.id, c.name, a.attend_date order by  a.attend_date";

        Object[] params = {teacherId, year, month};
        ResultSet rs = dBConnection.execute(query, params);

        List<DailyCourseAttendanceDto> result = new ArrayList<>();

        while (rs.next()) {
            DailyCourseAttendanceDto dto = new DailyCourseAttendanceDto();
            dto.setCourseId(rs.getInt("course_id"));
            dto.setCourseName(rs.getString("course_name"));
            dto.setAttendDate(LocalDate.parse(rs.getString("attend_date")));
            dto.setTotalAttendandts(rs.getInt("count"));
            result.add(dto);
        }
        return result;

    }

}
