package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// region fields

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger userId;

	@Column(name = "security_id")
	private BigInteger securityId;

	@Column(name = "role_id")
	private BigInteger roleId;

	@Column(name = "address_id")
	private BigInteger addressId;

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

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getSecurityId() {
		return securityId;
	}

	public void setSecurityId(BigInteger securityId) {
		this.securityId = securityId;
	}

	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public BigInteger getAddressId() {
		return addressId;
	}

	public void setAddressId(BigInteger addressId) {
		this.addressId = addressId;
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

	// endregion

}
