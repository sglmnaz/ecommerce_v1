package com.synclab.ecommerce.service.cartItem;

import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImplementation implements CartItemService {

    @Autowired
    private CartItemRepository repository;

    @Override
    public CartItem insert(CartItem record) {
        return repository.save(record);
    }

    @Override
    public CartItem findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<CartItem> findAll() {
        return repository.findAll();
    }

    @Override
    public CartItem update(CartItem record) {
        return repository.save(record);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<CartItem> findByCart_CartId(String cartId) {
        return repository.findByCart_Id(cartId);
    }

}
