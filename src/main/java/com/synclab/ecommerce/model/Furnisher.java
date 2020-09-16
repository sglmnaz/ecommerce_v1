package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "furnishers")
public class Furnisher implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "furnisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long furnisherId;

    @Column(name = "name")
    private String name;

    // endregion

    // region getter and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFurnisherId() {
        return furnisherId;
    }

    public void setFurnisherId(Long furnisherId) {
        this.furnisherId = furnisherId;
    }

    // endregion

}
