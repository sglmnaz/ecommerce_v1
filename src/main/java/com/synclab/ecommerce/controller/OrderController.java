package com.synclab.ecommerce.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.model.OrderItem;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.service.Order.OrderServiceImplementation;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.cartItem.CartItemServiceImplementation;
import com.synclab.ecommerce.service.orderItem.OrderItemServiceImplementation;
import com.synclab.ecommerce.service.status.StatusServiceImplementation;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImplementation orderServiceImplementation;

	@Autowired
	private CartServiceImplementation cartServiceImplementation;

	@Autowired
	private CartItemServiceImplementation cartItemServiceImplementation;

	@Autowired
	private OrderItemServiceImplementation orderItemServiceImplementation;
	
	@Autowired
	private StatusServiceImplementation statusServiceImplementation;

	// post

	// transorms a cart in order
	@PostMapping(value = "/insert/{cartId}", produces = "application/json")
	public ResponseEntity<Order> insert(@PathVariable(value = "cartId") Long cartId) throws Exception {

		Cart cart = cartServiceImplementation.findById(cartId);

		if (cart == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		Order order = new Order();
		order.setCreationDate(new Date());
		order.setTotalItems(cart.getTotalItems());
		order.setTotalPrice(cart.getTotalPrice());
		order.setStatus(statusServiceImplementation.findByName("STATUS_CREATED")); //TODO: questo in tabella e spostarlo in order

		order = orderServiceImplementation.insert(order);

		List<CartItem> cartItems = cartItemServiceImplementation.findByCart_CartId(cartId);
		// List<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (CartItem item : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem = orderItemServiceImplementation.insert(orderItem);
			cartItemServiceImplementation.deleteById(item.getCartItemId());
			// orderItems.add(orderItem);
		}
		
		cart.setTotalItems(0);
		cart.setTotalPrice(BigDecimal.ZERO);

		cart = cartServiceImplementation.update(cart);

		return ResponseEntity.ok(order);

	}
	
	// get
	
	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Order> findById (@PathVariable(value = "id") Long id){
		
		Order entity = orderServiceImplementation.findById(id);
		
		return entity != null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = "/getFromQuery", produces = "application/json")
	public ResponseEntity<Page<Order>> findById(@RequestParam String query,
			@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {

		List<Order> list = orderServiceImplementation.rsqlQuery(query);
		Pageable pageable = PageRequest.of(page, size);

		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());

		if (list.size() == 0 || start >= end)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(new PageImpl<Order>(list.subList(start, end), pageable, list.size()));

	}

	// delete

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Order> deleteById(@PathVariable(value = "id") Long id) {

		Order entity = orderServiceImplementation.findById(id);

		if (entity != null) // delete all order items
		{
			List<OrderItem> items = orderItemServiceImplementation.findByOrder_OrderId(id);
			for (OrderItem item : items) {
				orderItemServiceImplementation.deleteById(item.getOrderItemId());
			}
		}

		orderServiceImplementation.deleteById(id); // delete the order

		entity = orderServiceImplementation.findById(id);

		return entity == null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

}
