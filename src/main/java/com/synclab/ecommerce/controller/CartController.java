package com.synclab.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	// fields

	@Autowired
	private CartServiceImplementation cartServiceImplementation;

	@Autowired
	private UserServiceImplementation userServiceImplementation;
	
	// post
	
	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cart> insert(@RequestBody Cart request) {

		if (request == null) //return error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		// declarations
		Cart entity = request;
		List<CartItem> items = entity.getCartItem();
		Integer totalItems = 0;
		BigDecimal totalPrice = new BigDecimal("0");
		
		// operations
		if (items != null)
		for (CartItem item : items) {
			totalItems += item.getQuantity();
			BigDecimal price = item.getProduct().getPrice();
			totalPrice = BigDecimal.valueOf(item.getQuantity()).multiply(price);                         
		}
		
		entity.setTotalItems(totalItems);
		entity.setTotalPrice(totalPrice);
		
		// add to database
		cartServiceImplementation.insert(entity);

		return ResponseEntity.ok(entity);

	}

	// get
	
	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Cart> findById(@PathVariable(value = "id") Long id) {
		
		Cart entity = cartServiceImplementation.findById(id);

		return entity != null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	
	@GetMapping(value = "/getByUser/{id}", produces = "application/json")
	public ResponseEntity<Cart> findByUser(@PathVariable(value = "id") Long id) {
		
		User user = userServiceImplementation.findById(id).get();
		Cart entity = cartServiceImplementation.findByUser(user);

		return entity != null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// update
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cart> update(@RequestBody Cart request) throws Exception {

		if (request == null) //return error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		if (cartServiceImplementation.findById(request.getCartId()) == null) //no such object is present in the repository
			throw new RecordNotFoundException();
		
		// declarations
		Cart entity = request;
		
		// operations

		// add to database
		cartServiceImplementation.insert(entity);

		return ResponseEntity.ok(entity);

	}
		
	// delete
	
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Cart> deleteById(@PathVariable(value = "id") Long id) {

		cartServiceImplementation.deleteById(id);;
		
		Cart entity = cartServiceImplementation.findById(id);

		return entity == null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	
	@DeleteMapping(value = "/delete/all", produces = "application/json")
	public ResponseEntity<List<Cart>> deleteAll() {

		cartServiceImplementation.deleteAll();
		
		List<Cart> entities = cartServiceImplementation.findAll();

		return entities.isEmpty() ? ResponseEntity.ok(entities)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	
}
