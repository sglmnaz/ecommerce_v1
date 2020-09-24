package com.synclab.ecommerce.service.product;

import java.math.BigDecimal;
import java.util.List;


import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.Subcategory;

public interface ProductService {

	//insert
	void insert(Product product);
	//retrieve
	Product findById(Long id);
	List<Product> findAll();
	List<Product> findByCategory(Category category);
	List<Product> findBySubcategory(Subcategory subcategory);
	List<Product> findByName(String name);
	List<Product> findByPrice(BigDecimal min,BigDecimal max);
	List<Product> findByRating(Integer min, Integer max);
	//update
	void update(Product product);
	//delete
	void deleteById(Long id);
	void deleteAll();
	
	
}
