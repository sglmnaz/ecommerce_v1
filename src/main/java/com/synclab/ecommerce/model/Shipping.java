package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "shippings")
public class Shipping implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "shipping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @OneToOne()
    @JoinColumn(name = "order_id" , referencedColumnName = "order_id")
    private Order order;

    @Column(name = "recipient")
    private String recipient;

    @ManyToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    @Column(name = "shipping_date")
    private Date shippingDate;

    @Column(name = "deliver_date")
    private Date deliverDate;

    @Column(name = "estimated_deliver_date")
    private Date estimatedDeliverDate;

    
}
