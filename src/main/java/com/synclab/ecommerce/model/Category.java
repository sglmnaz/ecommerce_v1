package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @OneToOne() 
	@JoinColumn(name = "image_id" , referencedColumnName = "image_id")
    private Image image;

    // endregion

    // region getter and setters

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


    // endregion

}
