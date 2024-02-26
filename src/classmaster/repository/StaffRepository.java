/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Staff;
import classmaster.shared.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author Mishel Fernando
 */
public class StaffRepository implements Component{
    private final DBConnection dbCOnnection;

    public StaffRepository(DBConnection dbCOnnection) {
        this.dbCOnnection = dbCOnnection;
    }
    
    public int save(Staff staff) throws SQLException {

        String staffInsertQuery = "INSERT INTO staff (id, nic, contact_no) VALUES (?,?,?);";

        Object[] staffParams = {
            staff.getId(),
            staff.getNic(),
            staff.getContact_no()
        };

        return dbCOnnection.executeUpdate(staffInsertQuery, staffParams);

    }
    
    
    @Override
    public String getName() {
        return "StaffRepository";
    }
}
