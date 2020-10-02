package com.synclab.ecommerce.service.furnisher;

import java.util.List;

import com.synclab.ecommerce.model.Furnisher;

public interface FurnisherService {

	//insert
	Furnisher insert(Furnisher entity);
	//retrieve
	Furnisher findById(Long id);
	List<Furnisher>findAll();
	//update
	Furnisher update(Furnisher entity);
	//delete
	void deleteById(Long id);

}
