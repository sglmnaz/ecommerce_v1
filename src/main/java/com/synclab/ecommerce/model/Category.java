package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @OneToOne()
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;

    @ManyToMany()
    @JoinTable(name = "categories_subcategories",
            joinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "category_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "subcategory_id", referencedColumnName = "subcategory_id",
                            nullable = false, updatable = false)})
    private List<Subcategory> subcategories = new ArrayList<Subcategory>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
