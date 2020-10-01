package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

	@Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "creation_date")
    private Date creationDate;
    
    @ManyToOne()
    @JoinColumn(name = "status_id",referencedColumnName = "status_id")
    private Status status;
    
    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    @JsonIgnore
    private User user;


}
