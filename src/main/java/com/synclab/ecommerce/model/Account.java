package com.synclab.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_suspended")
    private Boolean isSuspended = false;

    @Column(name = "is_banned")
    private Boolean isBanned = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accounts_roles",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "account_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id",
                            nullable = false, updatable = false)})

    private List<Role> role = new ArrayList<>();

    // constructors

    public Account() {
    }

    public Account(String username, String email, String password, String phone, Date birtDate, List<Role> role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthDate = birtDate;
        this.role = role;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
}
