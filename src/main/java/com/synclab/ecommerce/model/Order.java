package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

	@Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "creation_date")
    private Date creationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<CartItem> orderItems = new ArrayList<CartItem>();
    
    // endregion

    // region getter and setters

    public Long getOrderId() {
 		return orderId;
 	}

 	public void setOrderId(Long orderId) {
 		this.orderId = orderId;
 	}

 	public List<CartItem> getOrderItems() {
 		return orderItems;
 	}

 	public void setOrderItems(List<CartItem> orderItems) {
 		this.orderItems = orderItems;
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

    // endregion

}
