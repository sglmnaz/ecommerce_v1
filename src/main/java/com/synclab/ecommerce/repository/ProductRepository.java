package com.synclab.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.Subcategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategories(Category category);
	List<Product> findBySubcategory(Subcategory subcategory);
	List<Product> findByName(String name);
	//List<Product> findByPrice(Float min,Float max);
	//List<Product> findByRating(Integer min, Integer max);

}
