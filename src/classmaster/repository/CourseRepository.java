/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Course;
import classmaster.models.CourseAssignment;
import classmaster.models.CourseAssignmentDto;
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
public class CourseRepository implements Component {

    private DBConnection dBConnection;

    public CourseRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public int save(Course course) throws SQLException {

        String query = "INSERT INTO Course (name, amount, teacherId, day, fromTime, toTime) VALUES (?,?,?,?,?,?);";

        Object[] params = {
            course.getName(),
            course.getAmount(),
            course.getTeacherId(),
            course.getDay(),
            course.getFrom(),
            course.getTo()
        };

        return dBConnection.executeUpdate(query, params);

    }

    public List<Course> getAllCourse() throws SQLException {
        Object[] params = {};
        return getAllCourse("select * from Course", params);
    }

    public List<Course> getAllCourseNotIn(List<Integer> courseIds) throws SQLException {

        Integer[] courseIdArray = new Integer[courseIds.size()];
        String query = "select * from Course where id not in (";

        for (int i = 0; i < courseIds.size(); i++) {
            courseIdArray[i] = courseIds.get(i);
            query += "?";
            if (i < courseIds.size() - 1) {
                query += ",";
            }
        }
        query += ")";
        System.out.println("query --> " + query);
        return getAllCourse(query, courseIdArray);
    }

    public List<Course> getAllCourse(String query, Object[] params) throws SQLException {
        List<Course> courseList = new ArrayList();
        ResultSet rs = dBConnection.execute(query, params);

        while (rs.next()) {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setAmount(rs.getDouble("amount"));
            course.setName(rs.getString("name"));
            course.setDay(rs.getString("day"));
            course.setFrom(LocalTime.parse(rs.getString("fromTime")));
            course.setTo(LocalTime.parse(rs.getString("toTime")));

            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public String getName() {
        return "CourseRepository";
    }

    public int assignCourse(CourseAssignment assignment) throws SQLException {
        String query = "INSERT INTO CourseAssignment (studentId, courseId, joinedDate, complete) VALUES (?,?,?,?);";

        Object[] params = {
            assignment.getSudentId(),
            assignment.getClassId(),
            assignment.getJoinedDate(),
            assignment.isComplete()
        };

        return dBConnection.executeUpdate(query, params);
    }

    public List<CourseAssignmentDto> getAllStudentCourses(int studentId) throws SQLException {

        List<CourseAssignmentDto> courseAssignments = new ArrayList<>();

        Object[] params = {studentId};
        ResultSet rs = dBConnection.execute("select c.*, ca.joinedDate, ca.studentId from CourseAssignment ca inner join Course c"
                + " on c.id = ca.courseId and ca.studentId = ? order by ca.joinedDate desc", params);

        while (rs.next()) {
            CourseAssignmentDto ca = new CourseAssignmentDto();
            ca.setCourseId(rs.getInt("id"));
            ca.setAmount(rs.getDouble("amount"));
            ca.setCourseName(rs.getString("name"));
            ca.setDay(rs.getString("day"));
            ca.setFromTime(LocalTime.parse(rs.getString("fromTime")));
            ca.setToTime(LocalTime.parse(rs.getString("toTime")));
            ca.setJoinedDate(LocalDate.parse(rs.getString("joinedDate")));

            courseAssignments.add(ca);
        }

        return courseAssignments;
    }

    public int removeStudentCourse(int studentId, int deletedCourse) throws SQLException {
        String query = "delete from CourseAssignment ca where ca.studentId = ? and ca.courseId = ?";

        Object[] params = {
            studentId,
            deletedCourse
        };

        return dBConnection.executeUpdate(query, params);
    }

    public List<Course> getTeacherCourses(int teacherId) throws SQLException {

        List<Course> courses = new ArrayList<>();

        Object[] params = {teacherId};
        ResultSet rs = dBConnection.execute("select * from Course where teacherId = ?", params);

        while (rs.next()) {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setAmount(rs.getDouble("amount"));
            course.setDay(rs.getString("day"));
            course.setFrom(LocalTime.parse(rs.getString("fromTime")));
            course.setTo(LocalTime.parse(rs.getString("toTime")));
            courses.add(course);
        }

        return courses;

    }

    public boolean hasStudentPaidForCourse(int studentId, int courseId) throws SQLException {
        String query = "select count(cap.amount) as total_payment"
                + " from CourseAssignmentPayment cap"
                + " where cap.studentId = ? and cap.courseId = ?";
        Object[] params = {studentId, courseId};

        ResultSet rs = dBConnection.execute(query, params);

        int count = 0;
        while (rs.next()) {
            count = rs.getInt("total_payment");
        }

        return count > 0;

    }

    public List<LocalDate> getCompletedCourseDates(int courseId, int year, int month) throws SQLException {

        String query = "select distinct a.attend_date "
                + " from Attendance a "
                + " where year(a.attend_date) = ? and month(a.attend_date) = ? and a.course_id = ?"
                + " order by a.attend_date";
        Object[] params = {year, month, courseId};

        ResultSet rs = dBConnection.execute(query, params);
        List<LocalDate> classDates = new ArrayList<>();
        while (rs.next()) {
            classDates.add(LocalDate.parse(rs.getString("attend_date")));
        }
        return classDates;
    }
   
}
