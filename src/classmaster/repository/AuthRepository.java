/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Account;
import classmaster.models.Staff;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bhagy
 */
public class AuthRepository implements Component {

    private final DBConnection dbCOnnection;

    public AuthRepository(DBConnection dbCOnnection) {
        this.dbCOnnection = dbCOnnection;
    }

    public Account signin(String email, String password) throws SQLException {
        Object[] params = {email, password};
        ResultSet rs = dbCOnnection.execute("select * from Account a where a.email = ? and a.password = ?", params);

        Account account = null;
        while (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setFirstName(rs.getString("first_name"));
            account.setLastName(rs.getString("last_name"));
            account.setDisplayName(rs.getString("display_name"));
            account.setRole(rs.getString("role"));
        }

        if (account != null) {
            account = setProfileInfo(account);
                  
        }

        return account;
    }

    private Account setProfileInfo(Account account) throws SQLException {

        if (account.getRole().equalsIgnoreCase("STAFF")) {
            Object[] params = {account.getId()};
            ResultSet rs = dbCOnnection.execute("select * from staff where id = ?", params);
            Staff st = new Staff(account);
            while (rs.next()) {
                st.setContact_no(rs.getString("contact_no"));
                st.setNic(rs.getString("nic"));
            }
            return st;
        }

        return account;

    }

    @Override
    public String getName() {
        return "AuthRepository";
    }

}
