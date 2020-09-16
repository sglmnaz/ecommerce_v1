package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "wharehouses")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "warehouse")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(name = "name")
    private String name;

    @Column(name = "address_id")
    private Long addressId;

    // endregion

    // region getter and setters

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    
    // endregion

}

