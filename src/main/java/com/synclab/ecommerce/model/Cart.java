package com.synclab.ecommerce.model;

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
    private String userId;
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

    public Cart(String userId, BigDecimal totalPrice, Integer totalItems) {
        super();
        this.userId = userId;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }
    
    public Cart(String userId) {
        super();
        this.userId = userId;
        this.totalItems = 0;
        this.totalPrice = BigDecimal.ZERO;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
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
