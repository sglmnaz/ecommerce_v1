package com.synclab.ecommerce.service.category;

import java.util.List;

import com.synclab.ecommerce.model.Category;

public interface categoryService {
	
		//insert
		void insert(Category category);
		//retrieve
		Category findById(Long id);
		List<Category> findAll();
		Category findByName(String name);
		//update
		void update(Category category);
		//delete
		void deleteById(Long id);
		void deleteAll();
}
