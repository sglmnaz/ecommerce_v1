package com.synclab.ecommerce.service.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Override
	public Order insert(Order entity) {
		return repository.save(entity);
	}

	@Override
	public Order findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Order update(Order entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
