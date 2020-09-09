package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "carts_items")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "quantity")
    private int quantity;

    // endregion

    // region getter and setters

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // endregion

}
