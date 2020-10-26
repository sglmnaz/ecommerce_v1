package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "orderItem")
public class OrderItem implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private Order order;
    private Product product;
    private Integer quantity;
    
    //methods

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrderItemId() {
        return id;
    }

    public void setOrderItemId(String orderItemId) {
        this.id = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
