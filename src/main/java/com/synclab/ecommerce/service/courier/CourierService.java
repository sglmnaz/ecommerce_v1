package com.synclab.ecommerce.service.courier;

import java.util.List;

import com.synclab.ecommerce.model.Courier;

public interface CourierService {
	
	//insert
	Courier insert(Courier entity);
	//retrieve
	Courier findById(Long id);
	List<Courier>findAll();
	//update
	Courier update(Courier entity);
	//delete
	void deleteById(Long id);

}
