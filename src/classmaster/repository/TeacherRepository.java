/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

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

    @Override
    public String getName() {
        return "TeacherRepository";
    }

}
