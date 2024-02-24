/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

/**
 *
 * @author bhagy
 */
public class Account {
    
    private int id;
    
    private String email;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String displayName;
    
    private String role;

    public Account(){}
    
    public Account(String email, String password, String firstName, String lastName, String displayName, String role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.role = role;
    }

    public Account(int id, String email, String password, String firstName, String lastName, String displayName, String role) {
        Account a = new Account(email, password, firstName, lastName, displayName, role);
        a.setId(id);
    }
    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", displayName=" + displayName + ", role=" + role + '}';
    }
    
    
}
