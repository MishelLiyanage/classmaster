/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

import java.util.Date;

/**
 *
 * @author bhagy
 */
public class Student extends Account{
    
    private String guardianName;
    
    private String guardianNum;
    
    private Date dob;
    
    private String city;
    
    public Student(){
        
    }
      
    public Student(Account account){
        this.setId(account.getId());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setDisplayName(account.getDisplayName());
        this.setEmail(account.getEmail());
        this.setPassword(account.getPassword());
        this.setRole(account.getRole());
    }
    
    public Student(Account account, String guardianName, String guardianNum, Date dob, String city){
        Student s = new Student(account);
        s.setCity(city);
        s.setDob(dob);
        s.setGuardianName(guardianName);
        s.setGuardianNum(guardianNum);       
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianNum() {
        return guardianNum;
    }

    public void setGuardianNum(String guardianNum) {
        this.guardianNum = guardianNum;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
    
}
