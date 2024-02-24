/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Course;
import classmaster.models.CourseAssignment;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.Student;
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
        
        for(int i = 0; i< courseIds.size(); i++){
            courseIdArray[i] = courseIds.get(i);
            query += "?";
            if(i < courseIds.size() - 1){
                query += ",";
            }
        }
        query += ")";
        System.out.println("query --> " + query);
        return getAllCourse(query, courseIdArray);
    }
        
     public List<Course> getAllCourse(String query, Object[] params ) throws SQLException {
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
                + " on c.id = ca.courseId and ca.studentId = ?", params);

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

}
