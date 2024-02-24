/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Student;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author bhagy
 */
public class StudentRepository implements Component {

    private DBConnection dBConnection;

    public StudentRepository(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public int save(Student student) throws SQLException {

        String studentInsertQuery = "INSERT INTO student (id, guardian_name, guardian_no, dob, city) VALUES (?,?,?,?,?);";

        Object[] studentParams = {
            student.getId(),
            student.getGuardianName(),
            student.getGuardianNum(),
            student.getDob(),
            student.getCity()
        };

        return dBConnection.executeUpdate(studentInsertQuery, studentParams);

    }

    public Student getStudentById(int id) throws SQLException {

        Student st = null;

        Object[] params = {id};
        ResultSet rs = dBConnection.execute("select a.*, s.dob, s.city, s.guardian_name, s.guardian_no from Student s inner join Account a on s.id = a.id and s.id = ?", params);

        while (rs.next()) {
            st = new Student();
            st.setId(rs.getInt("id"));
            st.setEmail(rs.getString("email"));
            st.setFirstName(rs.getString("first_name"));
            st.setLastName(rs.getString("last_name"));
            st.setDisplayName(rs.getString("display_name"));
            st.setDob(LocalDate.parse(rs.getString("dob")));
            st.setCity(rs.getString("city"));
            st.setGuardianName(rs.getString("guardian_name"));
            st.setGuardianNum(rs.getString("guardian_no"));
        }
        return st;
    }

    @Override
    public String getName() {
        return "StudentRepository";
    }

}
