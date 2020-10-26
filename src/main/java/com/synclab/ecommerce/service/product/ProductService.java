package com.synclab.ecommerce.service.product;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    //insert
    void insert(Product product);

    //retrieve
    Product findById(String id);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByCategory(Category category);

    List<Product> findBySubcategory(Subcategory subcategory);

    List<Product> findByName(String name);

    List<Product> findByPrice(BigDecimal min, BigDecimal max);

    List<Product> findByRating(Integer min, Integer max);

    //update
    void update(Product product);

    //delete
    void deleteById(String id);



}
