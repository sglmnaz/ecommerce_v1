package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "shippings")
public class Shipping implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "shipping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @OneToOne()
    @JoinColumn(name = "order_id" , referencedColumnName = "order_id")
    private Order order;

    @Column(name = "recipient")
    private String recipient;

    @ManyToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    @Column(name = "status")
    private String status;

    @Column(name = "shipping_date")
    private Date shippingDate;

    @Column(name = "deliver_date")
    private Date deliverDate;

    @Column(name = "estimated_deliver_date")
    private Date estimatedDeliverDate;

    // getter and setters

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


}
