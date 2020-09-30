package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping , Long> {

}
