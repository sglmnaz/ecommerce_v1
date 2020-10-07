package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// fields

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
	
	//constructor 
	
	public User() {
		this.signupDate = new Date();
	}
	
	public User(Account account,String firstname, String lastname, List<Address> addresses) {
		super();
		this.account = account;
		this.firstName = firstname;
		this.lastName = lastname;
		this.address = addresses;
	}

	
}
