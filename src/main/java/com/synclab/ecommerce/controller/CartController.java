package com.synclab.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.cartItem.CartItemServiceImplementation;
import com.synclab.ecommerce.service.product.ProductServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

	// fields

	@Autowired
	private CartServiceImplementation cartServiceImplementation;

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@Autowired
	private ProductServiceImplementation productServiceImplementation;

	@Autowired
	private CartItemServiceImplementation cartItemServiceImplementation;

	// post

	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cart> insert(@RequestBody Cart request) {

		if (request == null) // return error message
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

	@GetMapping(value = "/insertProduct", produces = "application/json")
	public ResponseEntity<String> addProduct(@RequestParam(name = "cartId") Long cartId,
			@RequestParam(name = "productId") Long productId,
			@RequestParam(name = "productQuantity", defaultValue = "1") Integer productQuantity) {

		Cart cart = cartServiceImplementation.findById(cartId);
		Product product = productServiceImplementation.findById(productId);
		CartItem cartItem = new CartItem(cart, product, productQuantity);

		cartItem = cartItemServiceImplementation.insert(cartItem);

		return cartItem != null
				? ResponseEntity.ok("product: " + product.getName() + " x" + productQuantity + " was added to cart")
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// update

	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cart> update(@RequestBody Cart request) throws Exception {

		if (request == null) // return error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		if (cartServiceImplementation.findById(request.getCartId()) == null) // no such object is present in the
																				// repository
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

		cartServiceImplementation.deleteById(id);
		;

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
