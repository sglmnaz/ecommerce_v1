package com.synclab.ecommerce.service.cart;

import java.util.List;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;

public interface CartService {

	//insert
	void insert(Cart record);
	//retrieve
	Cart findById(Long id);
	Cart findByUser(User user);
	List<Cart> findAll();
	//update
	void update(Cart record);
	//delete
	void deleteById(Long id);
	void deleteAll();
	
}
