package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "wharehouses")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    @JoinTable(name = "warehouses_furnishers",
            joinColumns = {
                    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "furnisher_id", referencedColumnName = "furnisher_id",
                            nullable = false, updatable = false)})
	private List<Furnisher> furnishers;

    @OneToOne()
    @JoinColumn(name = "address_id" ,referencedColumnName = "address_id")
    private Address address;

    @OneToOne()
    @JoinColumn(name = "stock_id" ,referencedColumnName = "stock_id")
    private Stock stock;

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

    // endregion

}

