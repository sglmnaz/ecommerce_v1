package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategories(Category category);

    List<Product> findByName(String name);


}
