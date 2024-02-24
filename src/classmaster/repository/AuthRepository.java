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
import models.Teacher;

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
        } else if (account.getRole().equalsIgnoreCase("TEACHER")) {
            Object[] params = {account.getId()};
            ResultSet rs = dbCOnnection.execute("select * from teacher where id = ?", params);
            Teacher th = new Teacher(account);
            while (rs.next()) {
                th.setDegree(rs.getString("degree"));
                th.setDescription(rs.getString("description"));
                th.setContactNo(rs.getString("contact_no"));
                th.setNicNo(rs.getString("nic"));
            }
            return th;
        }

        return account;

    }

    public int signup(Account account) throws SQLException {

        String accountInsertQuery = "INSERT INTO account (email, password, first_name, last_name, display_name, role) VALUES (?,?,?,?,?,?);";

        Object[] signupParams = {
            account.getEmail(),
            account.getPassword(),
            account.getFirstName(),
            account.getLastName(),
            account.getDisplayName(),
            account.getRole()
        };

        int state = dbCOnnection.executeUpdate(accountInsertQuery, signupParams);

        Account acc = null;

        if (state != 1) {
            throw new RuntimeException("Failed to add account");
        }
        acc = getAccount(account.getEmail());

        return acc.getId();

    }

    private Account getAccount(String email) throws SQLException {
        String getAccIdQuery = "SELECT * FROM ACCOUNT WHERE email = ?";

        ResultSet rs = dbCOnnection.execute(getAccIdQuery, new Object[]{email});

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

        return account;

    }

    @Override
    public String getName() {
        return "AuthRepository";
    }

}
