package com.synclab.ecommerce.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.CartRepository;

@Service
public class CartServiceImplementation implements CartService {

	@Autowired
	CartRepository repository;
	
	@Override
	public void insert(Cart record) {
		repository.save(record);
	}

	@Override
	public Cart findById(Long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public List<Cart> findAll() {
		return repository.findAll();
	}

	@Override
	public void update(Cart record) {
		repository.save(record);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Cart findByUser(User user) {
		return repository.findByUser(user);
	}

}
