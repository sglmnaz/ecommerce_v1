package com.synclab.ecommerce.service.shipping;

import java.util.List;

import com.synclab.ecommerce.model.Shipping;

public interface ShippingService {

	// insert
	Shipping insert(Shipping record);

	// retrieve
	Shipping findById(Long id);

	List<Shipping> findAll();

	// update
	Shipping update(Shipping record);

	// delete
	void deleteById(Long id);

	void deleteAll();
	
}
