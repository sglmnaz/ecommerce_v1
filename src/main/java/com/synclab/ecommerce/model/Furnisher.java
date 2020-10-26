package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "furnisher")
public class Furnisher implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    
    //methods

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFurnisherId() {
        return id;
    }

    public void setFurnisherId(String furnisherId) {
        this.id = furnisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
