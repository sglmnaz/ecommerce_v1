package com.synclab.ecommerce.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    private String country;
    private String province;
    private String city;
    private Integer zipcode;
    private String street;
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

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAddressId() {
        return id;
    }

    public void setAddressId(String addressId) {
        this.id = addressId;
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

	@Override
	public String toString() {
		return "Address [country=" + country + ", province=" + province + ", city=" + city + ", zipcode=" + zipcode
				+ ", street=" + street + ", houseNumber=" + houseNumber + "]";
	}
    
    
}
