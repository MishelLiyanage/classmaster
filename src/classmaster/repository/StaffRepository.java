/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Account;
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
    
    
    
    @Override
    public String getName() {
        return "AuthRepository";
    }
}
