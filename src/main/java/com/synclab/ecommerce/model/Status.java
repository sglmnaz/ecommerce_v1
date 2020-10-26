package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "status")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    private String description;

    // methods

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
}
