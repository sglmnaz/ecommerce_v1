package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    private long userId;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "total_items")
    private long totalItems;

    // endregion

    // region getter and setters

    // endregion

}
