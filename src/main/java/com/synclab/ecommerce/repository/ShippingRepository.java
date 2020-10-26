package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Shipping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends MongoRepository<Shipping, String> {

}
