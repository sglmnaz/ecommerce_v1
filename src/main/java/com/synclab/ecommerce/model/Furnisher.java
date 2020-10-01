package com.synclab.ecommerce.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
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

}
