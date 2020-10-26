package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends MongoRepository<Warehouse, Long> {

}
