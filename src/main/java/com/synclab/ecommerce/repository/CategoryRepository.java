package com.synclab.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Subcategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findByName(String name);
	Category findBySubcategories(Subcategory subcategory);
}
