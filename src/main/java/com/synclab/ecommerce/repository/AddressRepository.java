package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}