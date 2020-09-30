package com.synclab.ecommerce.service.orderItem;

import java.util.List;

import com.synclab.ecommerce.model.OrderItem;

public interface OrderItemService {

	// insert
	OrderItem insert(OrderItem record);

	// retrieve
	OrderItem findById(Long id);

	List<OrderItem> findByOrder_OrderId(Long orderId);

	List<OrderItem> findAll();

	// update
	OrderItem update(OrderItem record);

	// delete
	void deleteById(Long id);

	void deleteAll();

}
