package com.synclab.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(collection = "cart")
public class Cart implements Serializable {

    private static final Long serialVersionUID = 1L;

    // Fields

    @Id
    private String id;
    @JsonIgnore
    private User user;
    private BigDecimal totalPrice;
    private Integer totalItems;

    // Initializer

    public Cart() {
    }

    public Cart(BigDecimal totalPrice, Integer totalItems) {
        super();
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    public Cart(User user, BigDecimal totalPrice, Integer totalItems) {
        super();
        this.user = user;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCartId() {
        return id;
    }

    public void setCartId(String cartId) {
        this.id = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
