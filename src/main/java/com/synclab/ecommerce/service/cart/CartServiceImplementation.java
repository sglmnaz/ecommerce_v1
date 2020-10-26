package com.synclab.ecommerce.service.cart;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository repository;

    @Override
    public Cart insert(Cart record) {
        return repository.save(record);
    }

    @Override
    public Cart findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Cart update(Cart record) {
        return repository.save(record);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }


    @Override
    public Cart findByUserId(String id) {
        return repository.findByUserId(id);
    }

	@Override
	public Void deleteByUserId(String id) {
		repository.deleteByUserId(id);
		return null;
	}

}
