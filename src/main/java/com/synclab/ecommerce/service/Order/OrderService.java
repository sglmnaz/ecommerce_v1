package com.synclab.ecommerce.service.Order;

import com.synclab.ecommerce.model.Order;

public interface OrderService {
	
	//insert
	Order insert(Order entity);
	//retrieve
	Order findById(Long id);
	//update
	Order update(Order entity);
	//delete
	void deleteById(Long id);
	
}
