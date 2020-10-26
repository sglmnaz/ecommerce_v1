package com.synclab.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String phone;
    private Boolean isSuspended = false;
    private Boolean isBanned = false;
    private List<Role> role = new ArrayList<>();

    // constructors

    public Account() {
    }

    public Account( String email, String username, String password, String phone, Date birtDate, List<Role> role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthDate = birtDate;
        this.role = role;
    }

   
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean banned) {
        isBanned = banned;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
    
    public void addRole(Role role) {
        this.role.add(role);
    }

	@Override
	public String toString() {
		return "Account [ username=" + username + ", email=" + email + ", password="
				+ password + ", birthDate=" + birthDate + ", phone=" + phone + ", isSuspended=" + isSuspended
				+ ", isBanned=" + isBanned + ", role=" + role + "]";
	}
    
    
}
