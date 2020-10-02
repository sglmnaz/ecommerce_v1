package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id" )
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

}
