package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.security.PublicKey;

import javax.persistence.*;

@Entity
@Table(name = "cartsitems")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    private Cart cart;

    @OneToOne()
    @JoinColumn(name = "product_id" , referencedColumnName = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
    
    // Initializer
    
    public CartItem() {}
    
    public CartItem(Cart cart, Product product, Integer quantity) {
    	super();
    	this.cart=cart;
    	this.product = product;
    	this.quantity = quantity;
    }

    // getter and setters

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
