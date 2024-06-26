/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.CourseNoOfStudentsDto;
import classmaster.models.Teacher;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhagy
 */
public class TeacherRepository implements Component {

    private DBConnection dBConnection;

    public TeacherRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public List<Teacher> getAllTeachers() throws SQLException {

        List<Teacher> teachers = new ArrayList();

        Object[] params = {};
        ResultSet rs = dBConnection.execute("select a.*, t.degree, t.description, t.contact_no, t.nic from Teacher t inner join Account a on t.id = a.id", params);

        while (rs.next()) {
            Teacher tch = new Teacher();
            tch.setId(rs.getInt("id"));
            tch.setEmail(rs.getString("email"));
            tch.setFirstName(rs.getString("first_name"));
            tch.setLastName(rs.getString("last_name"));
            tch.setDisplayName(rs.getString("display_name"));
            tch.setDegree(rs.getString("degree"));
            tch.setDescription(rs.getString("description"));
            tch.setContactNo(rs.getString("contact_no"));
            tch.setNicNo(rs.getString("nic"));

            teachers.add(tch);

        }

        return teachers;
    }

    public int save(Teacher teacher) throws SQLException {

        String teacherInsertQuery = "INSERT INTO teacher (id, degree, description, contact_no, nic) VALUES (?,?,?,?,?);";

        Object[] teacherParams = {
            teacher.getId(),
            teacher.getDegree(),
            teacher.getDescription(),
            teacher.getContactNo(),
            teacher.getNicNo()
        };

        return dBConnection.executeUpdate(teacherInsertQuery, teacherParams);

    }
    
    public List<CourseNoOfStudentsDto> getAllCourseNoOfStudents(int teacherID) throws SQLException {
        List<CourseNoOfStudentsDto> courseNoOfStudents = new ArrayList<>();
        
        Object[] params = {teacherID};
        ResultSet rs = dBConnection.execute("SELECT c.id, c.name AS courseName, COUNT(ca.studentID) AS studentCount"
                + " FROM course c"
                + " JOIN courseAssignment ca ON c.id = ca.courseID"
                + " WHERE c.teacherID = ?"
                + " GROUP BY c.id, c.name", params);
        
        while (rs.next()) {
            CourseNoOfStudentsDto cs = new CourseNoOfStudentsDto();
            cs.setCourseID(rs.getInt("id"));
            cs.setCourseName(rs.getString("courseName"));
            cs.setNoOfStudents(rs.getInt("studentCount"));
        
            courseNoOfStudents.add(cs);
        }
        
        return courseNoOfStudents;
    }
    
    @Override
    public String getName() {
        return "TeacherRepository";
    }

}
