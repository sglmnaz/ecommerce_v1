package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "creation_date")
    private Date creationDate;

    // endregion

    // region getter and setters

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
