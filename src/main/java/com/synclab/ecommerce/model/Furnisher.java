package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "furnishers")
public class Furnisher implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "furnisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long furnisherId;

    @Column(name = "name")
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getFurnisherId() {
        return furnisherId;
    }

    public void setFurnisherId(Long furnisherId) {
        this.furnisherId = furnisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
