/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package classmaster.repository;

import classmaster.models.Account;
import classmaster.models.Staff;
import classmaster.models.Student;
import classmaster.shared.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import classmaster.models.Teacher;
import java.time.LocalDate;

/**
 *
 * @author bhagy
 */
public class AuthRepository implements Component {

    private final DBConnection dbCOnnection;

    private Account currentLoggedAccount;

    public AuthRepository(DBConnection dbCOnnection) {
        this.dbCOnnection = dbCOnnection;
    }
    
    public boolean isEmailAlreadyExists(String email) throws SQLException{
        Object[] params = {email};
        ResultSet rs = dbCOnnection.execute("select * from Account a where a.email = ?", params);
        
        boolean isExist = false;
        while (rs.next()) {
            if(rs.getString("email")!=null){
                isExist = true;
            }
           
        }
        return isExist;
    }

    public Account signin(String email, String password) throws SQLException {
        Object[] params = {email, password};
        ResultSet rs = dbCOnnection.execute("select * from Account a where a.email = ? and a.password = ?", params);

        currentLoggedAccount = null;
        while (rs.next()) {
            currentLoggedAccount = new Account();
            currentLoggedAccount.setId(rs.getInt("id"));
            currentLoggedAccount.setEmail(rs.getString("email"));
            currentLoggedAccount.setPassword(rs.getString("password"));
            currentLoggedAccount.setFirstName(rs.getString("first_name"));
            currentLoggedAccount.setLastName(rs.getString("last_name"));
            currentLoggedAccount.setDisplayName(rs.getString("display_name"));
            currentLoggedAccount.setRole(rs.getString("role"));
        }

        if (currentLoggedAccount != null) {
            currentLoggedAccount = setProfileInfo(currentLoggedAccount);
        }

        return currentLoggedAccount;
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
        }else if (account.getRole().equalsIgnoreCase("STUDENT")) {
            Object[] params = {account.getId()};
            ResultSet rs = dbCOnnection.execute("select * from student where id = ?", params);
            Student st = new Student(account);
            while (rs.next()) {
                st.setGuardianName(rs.getString("guardian_name"));
                st.setGuardianNum(rs.getString("guardian_no"));
                st.setDob(LocalDate.parse(rs.getString("dob")));
                st.setCity(rs.getString("city"));
            }
            return st;
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

        Account acc = null;

        while (rs.next()) {
            acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setEmail(rs.getString("email"));
            acc.setPassword(rs.getString("password"));
            acc.setFirstName(rs.getString("first_name"));
            acc.setLastName(rs.getString("last_name"));
            acc.setDisplayName(rs.getString("display_name"));
            acc.setRole(rs.getString("role"));
        }

        return acc;

    }

    public Account getCurrentAccount() {
        return currentLoggedAccount;
    }
    
    public void changePassword(String newPassword, String email) throws SQLException{
        Object[] params = {newPassword, email};
        dbCOnnection.executeUpdate("UPDATE account "
                + "SET password = ?"
                + "WHERE email = ?", params);
    }

    @Override
    public String getName() {
        return "AuthRepository";
    }

}
