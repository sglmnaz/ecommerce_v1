package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "statuses")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    // methods
    
}
