package com.synclab.ecommerce.service.orderItem;

import com.synclab.ecommerce.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    // insert
    OrderItem insert(OrderItem record);

    // retrieve
    OrderItem findById(String id);

    List<OrderItem> findByOrder_OrderId(String orderId);

    List<OrderItem> findAll();

    // update
    OrderItem update(OrderItem record);

    // delete
    void deleteById(String id);

    void deleteAll();

}
