package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {
    Status findByName(String name);

}
