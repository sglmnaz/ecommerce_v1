package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.security.PublicKey;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cartsitems")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne()
    @JoinColumn(name = "cart", referencedColumnName = "cart_id")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "order_id")
    private Order order;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCartID() { //changed to avoid loop (can be canged with jsnignoreproperty?)
        return cart.getCartId();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public Long getOrder() { //changed to avoid loop (can be canged with jsnignoreproperty?)
  		return order.getOrderId();
  	}

  	public void setOrder(Order order) {
  		this.order = order;
  	}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	@Override
	public String toString() {
		return "CartItem [ID=" + cartItemId + ", cartID=" + getCartID() + ", product=" + product + ", quantity=" + quantity + "]";
	}
    
    


}
