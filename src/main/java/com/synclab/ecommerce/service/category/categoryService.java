package com.synclab.ecommerce.service.category;

import com.synclab.ecommerce.model.Category;

import java.util.List;

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
