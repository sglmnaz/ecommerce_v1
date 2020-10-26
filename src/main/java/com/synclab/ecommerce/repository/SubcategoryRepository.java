package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Subcategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends MongoRepository<Subcategory, String> {

}
