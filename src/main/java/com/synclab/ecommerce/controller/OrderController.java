package com.synclab.ecommerce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Image;
import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.service.Order.OrderServiceImplementation;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderServiceImplementation orderServiceImplementation;
	
	@Autowired
	CartServiceImplementation cartServiceImplementation;

	// post

	//transorms a cart in order
	@PostMapping(value = "/insert/{cartId}", produces = "application/json") 
	public ResponseEntity<Order> insert(@PathVariable(value = "cartId") Long cartId) throws Exception {

		Cart cart = cartServiceImplementation.findById(cartId);
		
		if (cart == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		Order order = new Order();
		order.setCreationDate(new Date());
		order.setTotalItems(cart.getTotalItems());
		order.setTotalPrice(cart.getTotalPrice());
		
		order = orderServiceImplementation.insert(order);
		
		cart.setTotalItems(0);
		cart.setTotalPrice(BigDecimal.ZERO);
		
		cart = cartServiceImplementation.insert(cart);

		return ResponseEntity.ok(order);

	}

	// delete

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Order> deleteById(@PathVariable(value = "id") Long id) {

		orderServiceImplementation.deleteById(id);

		Order entity = orderServiceImplementation.findById(id);

		return entity == null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

}
