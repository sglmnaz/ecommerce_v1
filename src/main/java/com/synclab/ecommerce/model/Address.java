package com.synclab.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;


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

    public Address() {

    }

    public Address(String country, String province, String city, Integer zipcode, String street, Integer houseNumber) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
