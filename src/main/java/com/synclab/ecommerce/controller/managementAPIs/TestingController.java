package com.synclab.ecommerce.controller.managementAPIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;

@RestController
@RequestMapping("/testing")
public class TestingController {

	@Autowired
	RoleServiceImplementation rsi;

    @PostMapping("/role/initialize")
	public ResponseEntity<String> insertRole() {
		rsi.insert(new Role("ROLE_USER","standard user"));
		rsi.insert(new Role("ROLE_MANAGER","Manager user"));
		rsi.insert(new Role("ROLE_ADMIN","admin user"));
		return ResponseEntity.ok("Roles initialized.");
	}
}
