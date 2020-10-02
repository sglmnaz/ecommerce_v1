package com.synclab.ecommerce.service.subcategory;

import java.util.List;

import com.synclab.ecommerce.model.Subcategory;

public interface SubcategoryService {

	// insert
	Subcategory insert(Subcategory entity);

	// retrieve
	Subcategory findById(Long id);

	List<Subcategory> findAll();

	// update
	Subcategory update(Subcategory entity);

	// delete
	void deleteById(Long id);

}
