package com.synclab.ecommerce.repository;


import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Subcategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Category findByName(String name);

    Category findBySubcategories(Subcategory subcategory);
}
