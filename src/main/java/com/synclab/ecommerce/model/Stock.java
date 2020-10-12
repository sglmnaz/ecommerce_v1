package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @OneToOne(mappedBy = "stock")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "stock")
    private List<StockItem> stockItems = new ArrayList<StockItem>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }
}

