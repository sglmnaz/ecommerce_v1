package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
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
	@JoinColumn(name = "image_id" , referencedColumnName = "image_id")
    private Image image;

 
}
