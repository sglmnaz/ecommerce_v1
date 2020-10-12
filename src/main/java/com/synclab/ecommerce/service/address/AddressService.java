package com.synclab.ecommerce.service.address;

import com.synclab.ecommerce.model.Address;

import java.util.List;


public interface AddressService {

    //C
    Address insert(Address address);

    //R
    List<Address> findAll();

    Address findById(Long id);

    //U
    Address UpdateById(Long id, Address address) throws Exception;

    Address PatchById(Long id, Address address) throws Exception;

    //D
    void DeleteById(Long id);

    void deleteAll();

}
