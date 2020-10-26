package com.synclab.ecommerce.model;


import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private BigDecimal totalPrice;
    private int totalItems;
    private Date creationDate;
    private Status status;
    private String userId;

    //methods
    
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrderId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.id = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }
}
