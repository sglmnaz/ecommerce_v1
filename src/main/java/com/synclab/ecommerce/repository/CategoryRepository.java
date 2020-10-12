package com.synclab.ecommerce.repository;


import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    Category findBySubcategories(Subcategory subcategory);
}
