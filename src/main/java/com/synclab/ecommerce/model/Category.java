package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    private Image image;
    private List<Subcategory> subcategories = new ArrayList<Subcategory>();

    //methods
    
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCategoryId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        this.id = categoryId;
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

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
