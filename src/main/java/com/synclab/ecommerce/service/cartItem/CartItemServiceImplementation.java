package com.synclab.ecommerce.service.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.repository.CartItemRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {
	
	@Autowired
	CartItemRepository repository;

	@Override
	public CartItem insert(CartItem record) {
		return repository.save(record);
	}

	@Override
	public CartItem findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItem update(CartItem record) {
		return repository.save(record);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CartItem> findByCart_CartId(Long cartId) {
		return repository.findByCart_CartId(cartId);
	}

}
