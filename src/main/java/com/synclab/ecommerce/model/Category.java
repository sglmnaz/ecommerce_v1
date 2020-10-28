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
    
    public Category() {
		super();
	}
    
    public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, Image image, List<Subcategory> subcategories) {
		super();
		this.name = name;
		this.image = image;
		this.subcategories = subcategories;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
