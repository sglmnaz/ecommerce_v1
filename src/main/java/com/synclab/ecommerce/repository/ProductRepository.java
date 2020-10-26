package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategories(Category category);

    List<Product> findByName(String name);


}
