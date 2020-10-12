package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // constructor

    public Role() {
    }

    public Role(String name, String desc) {
        super();
        this.name = name;
        this.description = desc;
    }


    // getter and setters

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


    // endregion

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", name=" + name + ", description=" + description + "]";
    }

}
