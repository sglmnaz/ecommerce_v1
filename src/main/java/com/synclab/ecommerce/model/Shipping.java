package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "shipping")
public class Shipping implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private Order order;
    private String recipient;
    private Address address;
    private Courier courier;
    private Date shippingDate;
    private Date deliverDate;
    private Date estimatedDeliverDate;
    
    //methods

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShippingId() {
        return id;
    }

    public void setShippingId(String shippingId) {
        this.id = shippingId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Date getEstimatedDeliverDate() {
        return estimatedDeliverDate;
    }

    public void setEstimatedDeliverDate(Date estimatedDeliverDate) {
        this.estimatedDeliverDate = estimatedDeliverDate;
    }
}
