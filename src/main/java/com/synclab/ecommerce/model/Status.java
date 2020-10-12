package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statuses")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // methods


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
