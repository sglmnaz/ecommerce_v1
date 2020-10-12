package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Subcategories")
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "subcategory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subcategoryId;

    @Column(name = "name")
    private String name;

    @OneToOne()
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
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
