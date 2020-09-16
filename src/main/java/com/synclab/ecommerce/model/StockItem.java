package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "stock_item")
public class StockItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "stock_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockItemId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "product_id")
    private Long productId;

    @Column (name = "quantity")
    private Integer quantity;

    // endregion

    // region getter and setters

    public Long getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(Long stockItemId) {
        this.stockItemId = stockItemId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    // endregion

}

