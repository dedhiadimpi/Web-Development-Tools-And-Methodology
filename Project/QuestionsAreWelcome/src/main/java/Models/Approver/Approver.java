/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Approver;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dedhi
 */
@Entity
@Table(name = "tbl_approver_details")
public class Approver{
    @Id
    @Column(name = "approver_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long approver_id;
    @Column(name = "firstname", length = 50)
    private String firstname;
    @Column(name = "lastname", length = 50)
    private String lastname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private Date date_of_birth;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "email", length = 30)
    private String email;
    @Column(name = "phone_number", length = 20)
    private String phone_number;
    @Column(name = "username", length = 10)
    private String username;
    @Column(name = "password", length = 50)
    private String password;

    public long getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(long approver_id) {
        this.approver_id = approver_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
}
