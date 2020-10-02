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

import com.synclab.ecommerce.model.Furnisher;

import com.synclab.ecommerce.service.furnisher.FurnisherServiceImplementation;
import com.synclab.ecommerce.utility.response.CustomResponse;

@RestController
@RequestMapping("/furnisher")
public class FurnisherController {

	@Autowired
	private FurnisherServiceImplementation furnisherServiceImplementation;

	// post

	@PostMapping(value = "/insert")
	ResponseEntity<Furnisher> insert(@RequestBody Furnisher request) {

		if (request == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		Furnisher entity = furnisherServiceImplementation.insert(request);

		return ResponseEntity.ok(entity);
	}

	// get

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Furnisher> findById(@PathVariable(value = "id") Long id) {

		Furnisher entity = furnisherServiceImplementation.findById(id);
		return CustomResponse.getFindResponse(entity, "record not found",
				"record with id: " + id + " could not be found");
	}

	// delete

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Furnisher> deleteById(@PathVariable(value = "id") Long id) {

		furnisherServiceImplementation.deleteById(id);
		Furnisher entity = furnisherServiceImplementation.findById(id);

		return CustomResponse.getDeleteResponse(entity, "deletion failed",
				"record with id: " + id + " could not be deleted");
	}

}
