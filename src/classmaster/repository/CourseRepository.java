/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Course;
import classmaster.models.Student;
import classmaster.shared.DBConnection;
import java.sql.SQLException;

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

    @Override
    public String getName() {
        return "CourseRepository";
    }

}
