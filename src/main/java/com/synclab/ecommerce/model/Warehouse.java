package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "warehouse")
public class Warehouse implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String name;
    private Address address;
    private Stock stock;
    private List<Furnisher> furnishers;
    
    //methods

    public String getWarehouseId() {
        return id;
    }

    public void setWarehouseId(String warehouseId) {
        this.id = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Furnisher> getFurnishers() {
        return furnishers;
    }

    public void setFurnishers(List<Furnisher> furnishers) {
        this.furnishers = furnishers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}

