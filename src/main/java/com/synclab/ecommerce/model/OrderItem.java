package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    private Product product;
    private Integer quantity;
    
    //methods
    
    
    public OrderItem(Product product, Integer quantity) {
  		super();
  		this.product = product;
  		this.quantity = quantity;
  	}
    

    public Product getProduct() {
        return product;
    }

	public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
