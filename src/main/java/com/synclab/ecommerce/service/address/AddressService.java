package com.synclab.ecommerce.service.address;

import java.util.List;
import java.util.Optional;

import com.synclab.ecommerce.model.Address;


public interface AddressService {
	
    //C
    Address insert(Address address);
    //R
	List<Address> findAll();
	Optional<Address> findById(Long id);
    //U
	Address UpdateById(Long id, Address address) throws Exception;
	Address PatchById(Long id, Address address) throws Exception;
    //D
    void DeleteById(Long id);
    void deleteAll();
	
}
