package com.synclab.ecommerce.controller.managementAPIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.Status;
import com.synclab.ecommerce.service.category.CategoryServiceImplementation;
import com.synclab.ecommerce.service.category.categoryService;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.status.StatusServiceImplementation;

@RestController
@RequestMapping("/testing")
public class TestingController {

	@Autowired
	private RoleServiceImplementation rsi;

    @PostMapping("/role/initialize")
	public ResponseEntity<String> insertRoles() {
		rsi.insert(new Role("ROLE_USER","standard user"));
		rsi.insert(new Role("ROLE_MANAGER","Manager user"));
		rsi.insert(new Role("ROLE_ADMIN","admin user"));
		return ResponseEntity.ok("Roles initialized.");
	}
    
    @Autowired
	private CategoryServiceImplementation csi;

    @PostMapping("/category/initialize")
	public ResponseEntity<String> insertCategories() {
		csi.insert(new Category("Other"));
		csi.insert(new Category("Electronics"));
		csi.insert(new Category("Furniture"));
		csi.insert(new Category("Clothing"));
		csi.insert(new Category("Healt"));
		csi.insert(new Category("Sport"));
		csi.insert(new Category("Kids"));
		return ResponseEntity.ok("Category initialized.");
	}
    

    @Autowired
	private StatusServiceImplementation ssi;

    @PostMapping("/status/initialize")
	public ResponseEntity<String> insertStatuses() {
		ssi.insert(new Status("STATUS_CREATED"));
		ssi.insert(new Status("STATUS_TRANSPORT"));
		ssi.insert(new Status("STATUS_DELIVERED"));
		ssi.insert(new Status("STATUS_CANCELLED"));
		return ResponseEntity.ok("Status initialized.");
	}
}
