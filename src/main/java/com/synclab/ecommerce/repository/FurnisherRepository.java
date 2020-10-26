package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Furnisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnisherRepository extends MongoRepository<Furnisher, String> {

}
