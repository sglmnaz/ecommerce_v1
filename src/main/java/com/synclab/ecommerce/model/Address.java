package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private Integer zipcode;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private Integer houseNumber;


    // methods


    public Address(String country, String province, String city, Integer zipcode, String street, Integer houseNumber) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

}
