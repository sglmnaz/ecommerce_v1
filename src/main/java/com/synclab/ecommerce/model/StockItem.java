package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "stockitems")
public class StockItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

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


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    // endregion

}

