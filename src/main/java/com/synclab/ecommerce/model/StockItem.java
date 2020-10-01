package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "stockitems")
public class StockItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "stock_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockItemId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column (name = "quantity")
    private Integer quantity;

    @ManyToOne()
    private Stock stock;

    @OneToOne()
    @JoinColumn(name = "product_id" ,referencedColumnName = "product_id")
    private Product product;

}

