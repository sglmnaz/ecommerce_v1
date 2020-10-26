package com.synclab.ecommerce.service.subcategory;

import com.synclab.ecommerce.model.Subcategory;

import java.util.List;

public interface SubcategoryService {

    // insert
    Subcategory insert(Subcategory entity);

    // retrieve
    Subcategory findById(String id);

    List<Subcategory> findAll();

    // update
    Subcategory update(Subcategory entity);

    // delete
    void deleteById(String id);

}
