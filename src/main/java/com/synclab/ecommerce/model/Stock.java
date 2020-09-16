package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    // endregion

    // region getter and setters

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    // endregion

}

