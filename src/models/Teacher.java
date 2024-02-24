package models;

import classmaster.models.Account;

public class Teacher extends Account{
    private String degree;
    private String description;
    private String nicNo;
    private String contactNo;

    public Teacher() {
    }

    public Teacher(Account account){
        this.setId(account.getId());
        this.setEmail(account.getEmail());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setRole(account.getRole());
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    
    
}
