package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "user")
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private Account account;
    private List<Address> address = new ArrayList<Address>();
    private String firstName;
    private String lastName;
    private Date signupDate;
    private Date lastLoginDate;

    //methods

    public User() {
        this.signupDate = new Date();
    }

    public User(Account account, String firstname, String lastname, List<Address> addresses) {
        super();
        this.account = account;
        this.firstName = firstname;
        this.lastName = lastname;
        this.address = addresses;
    }

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
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

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

	@Override
	public String toString() {
		return "User [userId=" + id + ", account=" + account + ", address=" + address + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", signupDate=" + signupDate + ", lastLoginDate=" + lastLoginDate + "]";
	}
    
    
}
