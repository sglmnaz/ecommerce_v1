package com.synclab.ecommerce.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    @JoinTable(name = "products_categories",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "category_id",
                            nullable = false, updatable = false)})
	private List<Category> categories;
    
    @ManyToMany()
    @JoinTable(name = "products_images",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id", referencedColumnName = "image_id",
                            nullable = false, updatable = false)})
    private List<Image> image;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "is_available")
    private Boolean isAvailable;

    
}
