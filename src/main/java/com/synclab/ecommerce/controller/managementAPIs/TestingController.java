package com.synclab.ecommerce.controller.managementAPIs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Courier;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.Status;
import com.synclab.ecommerce.service.category.CategoryServiceImplementation;
import com.synclab.ecommerce.service.category.categoryService;
import com.synclab.ecommerce.service.courier.CourierService;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.status.StatusServiceImplementation;

@RestController
@RequestMapping("/testing")
public class TestingController {

	@Autowired
	private RoleServiceImplementation roleSI;
    @Autowired
	private CategoryServiceImplementation categorySI;
    @Autowired
	private CourierService courierSI;
    @Autowired
	private StatusServiceImplementation statusSI;

    //Requests
    
    @PostMapping("/role/initialize")
	public ResponseEntity<String> insertRoles() {
		roleSI.insert(new Role("ROLE_USER","standard user"));
		roleSI.insert(new Role("ROLE_MANAGER","Manager user"));
		roleSI.insert(new Role("ROLE_ADMIN","admin user"));
		return ResponseEntity.ok("Roles collection initialized.");
	}
    
    @PostMapping("/category/initialize")
	public ResponseEntity<String> insertCategories() {
		categorySI.insert(new Category("Other"));
		categorySI.insert(new Category("Electronics"));
		categorySI.insert(new Category("Furniture"));
		categorySI.insert(new Category("Clothing"));
		categorySI.insert(new Category("Healt"));
		categorySI.insert(new Category("Sport"));
		categorySI.insert(new Category("Kids"));
		return ResponseEntity.ok("Category collection initialized.");
	}

    @PostMapping("/status/initialize")
	public ResponseEntity<String> insertStatuses() {
		statusSI.insert(new Status("STATUS_CREATED"));
		statusSI.insert(new Status("STATUS_TRANSPORT"));
		statusSI.insert(new Status("STATUS_DELIVERED"));
		statusSI.insert(new Status("STATUS_CANCELLED"));
		return ResponseEntity.ok("Status collection initialized.");
	}
    
    @PostMapping("/courier/initialize")
	public ResponseEntity<String> insertCouriers() {
    	courierSI.insert(new Courier("Bartolini"));
    	courierSI.insert(new Courier("SDA"));
    	courierSI.insert(new Courier("FedEx"));
    	courierSI.insert(new Courier("UPS"));
    	courierSI.insert(new Courier("DHL"));
		return ResponseEntity.ok("Courier collection initialized.");
	}
    
    // rabbitmq
    
    private final String queue = "synclab.ecommerce.com";
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
	@Bean
	public Queue myQueue() {
	    return new Queue(queue, false);
	}
    
    @PostMapping("/email")
	public ResponseEntity<?> sendMessage(@RequestBody String message) {
    	
    	rabbitTemplate.convertAndSend(queue, message);
    	System.out.println("message sent.");
    	
		return ResponseEntity.ok("message sent.");
	}
    
}
