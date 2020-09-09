package com.synclab.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.UserServiceImplementation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	// region postMapping

	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> insert(@RequestBody User user) {
		User newUser = userServiceImplementation.insert(user);

		if (newUser != null) {
			return ResponseEntity.ok(newUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// endregion

	// region getMapping

	@GetMapping(value = "/findByFirstName/{name}", produces = "application/json")
	public ResponseEntity<User> findByFitstName(@PathVariable(value = "name") String name) {
		User newUser = userServiceImplementation.findByFirstName(name).get();

		if (newUser != null) {
			return ResponseEntity.ok(newUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
		User newUser = userServiceImplementation.findById(id).get();

		if (newUser != null) {
			return ResponseEntity.ok(newUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value = "/get/", produces = "application/json")
	public ResponseEntity<List<User>> findAll() {

		List<User> users = userServiceImplementation.findAll();

		if (!users.isEmpty()) {
			return ResponseEntity.ok(users);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// endregion

}