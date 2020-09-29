package com.synclab.ecommerce.service.cartItem;

import java.util.List;

import com.synclab.ecommerce.model.CartItem;


public interface CartItemService {
	
	//insert
		CartItem insert(CartItem record);
		//retrieve
		CartItem findById(Long id);
		List<CartItem> findByCart_CartId(Long cartId);
		List<CartItem> findAll();
		//update
		CartItem update(CartItem record);
		//delete
		void deleteById(Long id);
		void deleteAll();

}
