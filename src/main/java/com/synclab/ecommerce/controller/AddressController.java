package com.synclab.ecommerce.controller;

import java.util.Date;

import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController()
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    // insert - takes a JSON body <- stores the object in the db -> outputs Response
    // entity
    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> insert(@RequestBody final Address requestBody) {

        if (requestBody == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        final Address address = addressRepository.save(requestBody);

        return ResponseEntity.ok(address);
    }

    // get - takes a path variable <- find the object in the db -> outputs Response
    // entity
    @GetMapping(value = "/findById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> findById(@PathVariable(value = "id") Long id) {

        if (id == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        final Address address = addressRepository.findById(id).get();
        return ResponseEntity.ok(address);
    }

    // update - takes a path variable and a request body <- find the object in the
    // db and updates -> outputs Response entity
    @PutMapping(value = "/updateById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> updateById(@PathVariable(value = "id") Long id,
            @RequestBody final Address requestBody) {

        if (id == null || requestBody == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        if (!addressRepository.findById(id).isPresent()) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Address address = requestBody;
        address.setAddressId(id);
        addressRepository.save(address);

        return ResponseEntity.ok(address);
    }

    // patch - takes a path variable and a request body <- find the object in the
    // db and patches -> outputs Response entity
    @PatchMapping(value = "/patchById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> patchById(@PathVariable(value = "id") Long id,
            @RequestBody final Address requestBody) {

        if (id == null || requestBody == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Address oldAddress = addressRepository.findById(id).get();

        if (oldAddress == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Address newAddress = oldAddress;

        // patching
        if (requestBody.getCountry() != null)
            newAddress.setCountry(requestBody.getCountry());
        if (requestBody.getCity() != null)
            newAddress.setCity(requestBody.getCity());
        if (requestBody.getProvince() != null)
            newAddress.setProvince(requestBody.getProvince());
        if (requestBody.getZipcode() != null)
            newAddress.setZipcode(requestBody.getZipcode());
        if (requestBody.getStreet() != null)
            newAddress.setStreet(requestBody.getStreet());
        if (requestBody.getHouseNumber() != null)
            newAddress.setHouseNumber(requestBody.getHouseNumber());

        newAddress.setAddressId(id);
        newAddress = addressRepository.save(newAddress);

        return ResponseEntity.ok(newAddress);
    }

    // delete - takes a path variable <- delete the object in the db -> outputs
    // Response entity
    @DeleteMapping(value = "/findById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {

        if (id == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        addressRepository.deleteById(id);
        return ResponseEntity.ok(new Date() + " - Entity deleted succesfully.");
    }

}
