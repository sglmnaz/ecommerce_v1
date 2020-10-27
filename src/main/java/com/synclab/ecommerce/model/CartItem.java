package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private Product product;
    private Integer quantity;

    // Initializer

    public CartItem() {
    }

    public CartItem( Product product, Integer quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
    }

    // getter and setters

    public String getCartItemId() {
        return id;
    }

    public void setCartItemId(String cartItemId) {
        this.id = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
