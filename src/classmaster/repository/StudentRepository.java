/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Account;
import classmaster.models.Student;
import classmaster.shared.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author bhagy
 */
public class StudentRepository implements Component{
    
    private DBConnection dBConnection;
    
    public StudentRepository(DBConnection dBConnection){
        this.dBConnection = dBConnection;
    }
    
    
     public int save(Student student) throws SQLException{
       
        String studentInsertQuery = "INSERT INTO student (id, guardian_name, guardian_no, dob, city) VALUES (?,?,?,?,?);";
        
        Object[] studentParams = {
            student.getId(),
            student.getGuardianName(),
            student.getGuardianNum(),
            student.getDob(),
            student.getCity()
        };
       
        int state = dBConnection.executeUpdate(studentInsertQuery, studentParams);
       
        return state;
        
        
    }

    @Override
    public String getName() {
        return "StudentRepository";
    }
    
}
