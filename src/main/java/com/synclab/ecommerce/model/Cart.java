package com.synclab.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    // Fields

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_items")
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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
