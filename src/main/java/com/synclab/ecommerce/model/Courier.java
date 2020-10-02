package com.synclab.ecommerce.model;

import java.io.Serializable;


import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "couriers")
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "courier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courierId;

    @Column(name = "name")
    private String name;

}
