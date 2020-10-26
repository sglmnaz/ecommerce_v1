package com.synclab.ecommerce.service.cartItem;

import com.synclab.ecommerce.model.CartItem;

import java.util.List;

public interface CartItemService {

    // insert
    CartItem insert(CartItem record);

    // retrieve
    CartItem findById(String id);

    List<CartItem> findByCart_CartId(String cartId);

    List<CartItem> findAll();

    // update
    CartItem update(CartItem record);

    // delete
    void deleteById(String id);

    void deleteAll();

}
