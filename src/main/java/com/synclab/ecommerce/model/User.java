package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// region fields

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@OneToOne() 
	@JoinColumn(name = "account_id" , referencedColumnName = "account_id")
	private Account account;

	@ManyToMany()
    @JoinTable(name = "users_addresses",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "address_id", referencedColumnName = "address_id",
                            nullable = false, updatable = false)})
	private List<Address> address = new ArrayList<Address>();

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "signup_date")
	private Date signupDate;

	@Column(name = "last_login_date")
	private Date lastLoginDate;


	// endregion

	// region getter and setters

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Address> getAddresses() {
		return address;
	}

	public void setAddresses(List<Address> address) {
		this.address = address;
	}

	
	// endregion

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", address=" + address + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", signupDate=" + signupDate + ", lastLoginDate=" + lastLoginDate
				 + "]";
	}

	
}
