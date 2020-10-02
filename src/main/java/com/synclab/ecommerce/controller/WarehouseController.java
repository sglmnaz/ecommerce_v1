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

import com.synclab.ecommerce.model.Warehouse;
import com.synclab.ecommerce.service.warehouse.WarehouseServiceImplementation;
import com.synclab.ecommerce.utility.response.CustomResponse;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseServiceImplementation stockServiceImplementation;

	// post

	@PostMapping(value = "/insert")
	public ResponseEntity<Warehouse> insert(@RequestBody Warehouse request) {

		if (request == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		Warehouse entity = stockServiceImplementation.insert(request);

		return ResponseEntity.ok(entity);
	}

	// get

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Warehouse> findById(@PathVariable(value = "id") Long id) {

		Warehouse entity = stockServiceImplementation.findById(id);
		return CustomResponse.getFindResponse(entity, "record not found",
				"record with id: " + id + " could not be found");
	}

	// delete

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Warehouse> deleteById(@PathVariable(value = "id") Long id) {

		stockServiceImplementation.deleteById(id);
		Warehouse entity = stockServiceImplementation.findById(id);

		return CustomResponse.getDeleteResponse(entity, "deletion failed",
				"record with id: " + id + " could not be deleted");
	}

}
