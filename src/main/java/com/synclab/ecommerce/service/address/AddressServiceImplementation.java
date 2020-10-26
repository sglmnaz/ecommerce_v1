package com.synclab.ecommerce.service.address;

import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.repository.AddressRepository;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address insert(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(String id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public Address UpdateById(String id, Address address) throws Exception {

        if (findById(id) == null)
            throw new RecordNotFoundException();

        Address newAddress = address;
        newAddress.setAddressId(id);
        return addressRepository.save(newAddress);
    }

    @Override
    public Address PatchById(String id, Address address) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void DeleteById(String id) {
        addressRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        addressRepository.deleteAll();
    }

}
