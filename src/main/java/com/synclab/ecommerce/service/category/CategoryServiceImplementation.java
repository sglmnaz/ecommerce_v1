package com.synclab.ecommerce.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImplementation implements categoryService {
	
	@Autowired 
	private CategoryRepository repository;

	@Override
	public void insert(Category category) {
		repository.save(category);
	}

	@Override
	public Category findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Category findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
