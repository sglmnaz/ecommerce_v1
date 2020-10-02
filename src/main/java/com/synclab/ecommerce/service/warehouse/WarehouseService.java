package com.synclab.ecommerce.service.warehouse;

import java.util.List;

import com.synclab.ecommerce.model.Warehouse;

public interface WarehouseService {

	// insert
	Warehouse insert(Warehouse entity);

	// retrieve
	Warehouse findById(Long id);

	List<Warehouse> findAll();

	// update
	Warehouse update(Warehouse entity);

	// delete
	void deleteById(Long id);

}
