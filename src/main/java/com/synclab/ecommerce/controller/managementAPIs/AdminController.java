package com.synclab.ecommerce.controller.managementAPIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RoleServiceImplementation rsi;
	
	@Autowired
	private UserServiceImplementation usi;


	@PostMapping("/role/insert")
	public ResponseEntity<String> insertRole(@RequestBody Role role) {
		Role _role = rsi.insert(role);
		return ResponseEntity.ok(_role.getName() + " was added");
	}
	
	@PatchMapping("/role/set/{id}")
	public ResponseEntity<String> insertRole(@RequestParam(value = "role") String _role,
											 @PathVariable(value = "id") String id) {
		Role role = rsi.findByName(_role);
		if (role == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		User user = usi.findById(id);
		if (user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		user.getAccount().addRole(role);
		user = usi.insert(user);
		return ResponseEntity.ok(user.getAccount().getUsername()+" is now a " + _role);
	}

}