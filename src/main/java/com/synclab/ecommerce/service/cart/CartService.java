package com.synclab.ecommerce.service.cart;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;

import java.util.List;

public interface CartService {

    //insert
    Cart insert(Cart record);

    //retrieve
    Cart findById(Long id);

    Cart findByUser(User user);

    List<Cart> findAll();

    //update
    Cart update(Cart record);

    //delete
    void deleteById(Long id);

    void deleteAll();

}
