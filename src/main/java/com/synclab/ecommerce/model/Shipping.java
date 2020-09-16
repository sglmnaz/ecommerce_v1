package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "shippings")
public class Shipping implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "shipping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "shipping_address_id")
    private Long shippingAddressId;

    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "status")
    private String status;

    @Column(name = "shipping_date")
    private Date shippingDate;

    @Column(name = "deliver_date")
    private Date deliverDate;

    @Column(name = "estimated_deliver_date")
    private Date estimatedDeliverDate;

    // endregion

    // region getter and setters

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
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


    // endregion

}
