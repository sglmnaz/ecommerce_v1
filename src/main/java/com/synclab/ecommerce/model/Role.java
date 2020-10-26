package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    private String description;

    // methods

    public Role() {
    }

    public Role(String name, String desc) {
        super();
        this.name = name;
        this.description = desc;
    }

    public String getRoleId() {
        return id;
    }

    public void setRoleId(String roleId) {
        this.id = roleId;
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
        return "Role [roleId=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
