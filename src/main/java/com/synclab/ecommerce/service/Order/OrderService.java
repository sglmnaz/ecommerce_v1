package com.synclab.ecommerce.service.Order;

import com.synclab.ecommerce.model.Order;

import java.util.List;

public interface OrderService {

    //insert
    Order insert(Order entity);

    //retrieve
    Order findById(Long id);

    List<Order> findByUser_UserId(Long id);

    List<Order> findAll();

    //update
    Order update(Order entity);

    //delete
    void deleteById(Long id);

}
