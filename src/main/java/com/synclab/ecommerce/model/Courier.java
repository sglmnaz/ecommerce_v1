package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "couriers")
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "courier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courierId;

    @Column(name = "name")
    private String name;

    // endregion

    // region getter and setters

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // endregion

}
