package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

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
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_items")
    private Integer totalItems;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem;

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

    // getter and setters

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public BigDecimal evaluateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItem) {
            total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

}
