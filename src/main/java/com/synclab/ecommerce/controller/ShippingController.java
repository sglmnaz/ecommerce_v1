package com.synclab.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.model.Shipping;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.Order.OrderServiceImplementation;
import com.synclab.ecommerce.service.address.AddressServiceImplementation;
import com.synclab.ecommerce.service.shipping.ShippingServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

	@Autowired
	private ShippingServiceImplementation shippingServiceImplementation;

	@Autowired
	private AddressServiceImplementation addressServiceImplementation;
	
	@Autowired
	private OrderServiceImplementation orderServiceImplementation;
	
	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@PostMapping(value = "/insert")
	ResponseEntity<Shipping> insert(@RequestParam(name = "orderId") Long orderId,
			@RequestParam(name = "userId") Long userId,
			@RequestParam(name = "addressId") Long addressId,
			@RequestParam(name = "courierId") Long courierId){
		
		Order order = orderServiceImplementation.findById(orderId);
		Address address = addressServiceImplementation.findById(addressId);
		User user = userServiceImplementation.findById(userId);
		
		if (order == null || address == null || user == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		Shipping shipping = new Shipping();
		shipping.setAddress(address);
		shipping.setOrder(order);
		shipping.setRecipient(user.getFirstName() + " " + user.getLastName());
		shipping.setStatus("STATUS_CREATED");
		
		shipping = shippingServiceImplementation.insert(shipping);
		return ResponseEntity.ok(shipping);
		
	}

}
