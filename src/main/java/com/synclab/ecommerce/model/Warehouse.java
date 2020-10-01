package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "wharehouses")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

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

}

