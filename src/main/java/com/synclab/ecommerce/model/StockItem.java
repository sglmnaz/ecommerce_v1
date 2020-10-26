package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "stockItem")
public class StockItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String stockId;
    private Integer quantity;
    private Stock stock;
    private Product product;
    
    //methods

    public String getStockItemId() {
        return id;
    }

    public void setStockItemId(String stockItemId) {
        this.id = stockItemId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
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
}

