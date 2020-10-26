package com.synclab.ecommerce.service.category;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements categoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public void insert(Category category) {
        repository.save(category);
    }

    @Override
    public Category findById(String id) {
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
        repository.save(category);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
