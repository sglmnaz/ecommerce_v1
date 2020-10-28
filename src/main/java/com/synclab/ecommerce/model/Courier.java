package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "courier")
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    
    //methods
    
    

    public Courier(String name) {
		super();
		this.name = name;
	}
    

    
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

	public String getId() {
        return id;
    }

    public void setId(String courierId) {
        this.id = courierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
