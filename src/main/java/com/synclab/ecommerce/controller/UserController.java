package com.synclab.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.JobOriginatingUserName;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.UserRepository;
import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.address.AddressServiceImplementation;
import com.synclab.ecommerce.service.role.RoleService;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImplementation userServiceImplementation;
	
	@Autowired
	private RoleServiceImplementation roleServiceImplementation;
	
	@Autowired
	private AccountServiceImplementation accountServiceImplementation;
	
	@Autowired
	private AddressServiceImplementation addressServiceImplementation;

	// region postMapping

	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> insert(@RequestBody User user) {
		
		if (user != null)
		{
			User newUser = user;
			List<Address> addresses = newUser.getAddresses();
			Account account = newUser.getAccount();
			Role role = roleServiceImplementation.findByName("standard");
			
			//initialize fields with default values
			newUser.setAddresses(addresses);
			newUser.setAccount(account);
			account.getRole().add(role);
			newUser.setSignupDate(new Date());
			
			//add to database
			accountServiceImplementation.insert(account);
			for (Address address : addresses) {
				addressServiceImplementation.insert(address);
			}
			userServiceImplementation.insert(newUser);
			
			return ResponseEntity.ok(newUser);
		}
		else 
		{
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

	@GetMapping(value = "/get/all", produces = "application/json")
	public ResponseEntity<List<User>> findAll() {

		List<User> users = userServiceImplementation.findAll();

		if (!users.isEmpty()) {
			return ResponseEntity.ok(users);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// endregion
	
	// region update
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> update(@RequestBody User user) throws RecordNotFoundException {
		
		if (user != null)
		{
			
			List<Address> addresses = user.getAddresses();
			Account account = user.getAccount();
			String email = account.getEmail();
			Account oldAccount = accountServiceImplementation.findByEmail(email);
			Long id = userServiceImplementation.findByAccount(oldAccount).getUserId();
			List<Role> roles = oldAccount.getRole();
			
			User newUser = user;
			
			//initialize fields with default values
			newUser.setUserId(id);
			newUser.setAddresses(addresses);
			newUser.setAccount(account);
			account.setRole(roles);
			
			//update to database
			try 
			{
				accountServiceImplementation.UpdateById(oldAccount.getAccountId(), account);
				
				for (Address address : addresses) 
				{
					addressServiceImplementation.insert(address);
				}
				
				userServiceImplementation.UpdateById(id, newUser);
				
				return ResponseEntity.ok(newUser);
			}
			catch (Exception e) 
			{
				throw new RecordNotFoundException();
			}
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	// endregion
	
	// region deleteMapping
	
	@DeleteMapping(value = "/delete/all" , produces = "application/json")
	public ResponseEntity deleteAll() {
		
		accountServiceImplementation.deleteAll();
		addressServiceImplementation.deleteAll();
		userServiceImplementation.deleteAll();
		
		List<User> users = userServiceImplementation.findAll();

		if (users.isEmpty()) {
			return ResponseEntity.ok(users);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// endregion

}
