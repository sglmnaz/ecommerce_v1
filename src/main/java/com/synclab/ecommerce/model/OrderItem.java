package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;



@Entity
@Table(name = "orderitems")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

	@OneToOne()
    @JoinColumn(name = "product_id" , referencedColumnName = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
    
    // Initializer
    


    // getter and setters

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
   

}
