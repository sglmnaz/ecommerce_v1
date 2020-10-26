package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "courier")
public class Courier implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    
    //methods

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCourierId() {
        return id;
    }

    public void setCourierId(String courierId) {
        this.id = courierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
