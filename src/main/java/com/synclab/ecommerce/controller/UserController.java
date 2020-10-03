package com.synclab.ecommerce.controller;

import java.util.Date;
import java.util.List;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.address.AddressServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;
import com.synclab.ecommerce.utility.pages.PageUtils;
import com.synclab.ecommerce.utility.response.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	// post

	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> insert(@RequestBody User user) {

		if (user == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		User entity = user;
		Account account = entity.getAccount();
		List<Address> addressList = entity.getAddress();

		// initialize fields with default value and add tthem to db
		account.getRole().add(roleServiceImplementation.findByName("ROLE_CLIENT"));
		account = accountServiceImplementation.insert(account);
		if (addressList != null) {
			for (Address address : addressList) {
				addressServiceImplementation.insert(address);
			}
		}

		// assign fields to entity
		entity.setAccount(account);
		entity.setSignupDate(new Date());

		// add entity to db
		entity = userServiceImplementation.insert(entity);

		return ResponseEntity.ok(entity);

	}

	// get

	@GetMapping(value = "/findByFirstName/{name}", produces = "application/json")
	public ResponseEntity<User> findByFitstName(@PathVariable(value = "name") String name) {

		User newUser = userServiceImplementation.findByFirstName(name).get();

		return CustomResponse.getFindResponse(newUser, "user not found",
				"user with name: " + name + " could not be found.");
	}

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {

		User newUser = userServiceImplementation.findById(id);

		return CustomResponse.getFindResponse(newUser, "user not found",
				"user with id: " + id + " could not be found.");

	}

	@GetMapping(value = "/get/all", produces = "application/json")
	public ResponseEntity<List<User>> findAll() {

		List<User> users = userServiceImplementation.findAll();

		return !users.isEmpty() ? ResponseEntity.ok(users)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/getFromQuery", produces = "application/json")
	public ResponseEntity<Page<User>> findById(@RequestParam String query, @RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size) {

		List<User> list = userServiceImplementation.rsqlQuery(query);
		return PageUtils.listToPageResponseEntity(list, page, size);

	}

	// update

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> update(@RequestBody User user) throws RecordNotFoundException {

		if (user != null) {

			List<Address> addresses = user.getAddress();
			Account account = user.getAccount();
			Account oldAccount = accountServiceImplementation.findById(account.getAccountId()).get();
			Long id = userServiceImplementation.findByAccount(oldAccount).getUserId();
			List<Role> roles = oldAccount.getRole();

			User newUser = user;

			// initialize fields with default values
			newUser.setUserId(id);
			newUser.setAddress(addresses);
			newUser.setAccount(account);
			account.setRole(roles);

			// update to database
			try {
				accountServiceImplementation.UpdateById(oldAccount.getAccountId(), account);

				for (Address address : addresses) {
					addressServiceImplementation.insert(address);
				}

				userServiceImplementation.UpdateById(id, newUser);

				return ResponseEntity.ok(newUser);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// patch

	@PatchMapping(value = "/patch/{id}", consumes = "applicationj/json", produces = "application/json")
	public ResponseEntity<User> patch(@PathVariable(name = "id") Long id, @RequestBody User user) {

		if (user == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		User oldUser = userServiceImplementation.findById(id);

		if (oldUser == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		if (user.getFirstName() != null)
			oldUser.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			oldUser.setLastName(user.getLastName());
		if (user.getSignupDate() != null)
			oldUser.setSignupDate(user.getSignupDate());
		if (user.getAccount() != null)
			oldUser.setAccount(user.getAccount());
		if (user.getAddress() != null)
			oldUser.setAddress(user.getAddress());
		if (user.getLastLoginDate() != null)
			oldUser.setLastLoginDate(user.getLastLoginDate());

		oldUser = userServiceImplementation.insert(oldUser);
		return ResponseEntity.ok(oldUser);

	}

	// delete

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable(name = "id") Long id) {
		userServiceImplementation.DeleteById(id);
		User user = userServiceImplementation.findById(id);
		return CustomResponse.getDeleteResponse(user, "deletion failed", "could not delete entity with id: " + id);
	}

}
