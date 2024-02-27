/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.models;

/**
 *
 * @author bhagy
 */
public class Staff extends Account{
    
    private String nic;
    
    private String contact_no;

    public Staff(Account account) {
        this.setId(account.getId());
        this.setEmail(account.getEmail());
        this.setPassword(account.getPassword());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setDisplayName(account.getDisplayName());
        this.setRole(account.getRole());
    }

    public Staff() {
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
    
    
    
    
    
}
