package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "subcategory")
public class Subcategory implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    private Image image;
    
    //methods

    public String getSubcategoryId() {
        return id;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.id = subcategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
