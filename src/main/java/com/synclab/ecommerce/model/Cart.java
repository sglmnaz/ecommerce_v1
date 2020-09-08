package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger cartId;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "total_price")
    private BigInteger totalPrice;

    @Column(name = "total_items")
    private BigInteger totalItems;

    // endregion

    // region getter and setters

    // endregion

}
