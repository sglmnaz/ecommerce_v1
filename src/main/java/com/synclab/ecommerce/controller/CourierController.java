package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.Courier;
import com.synclab.ecommerce.service.courier.CourierServiceImplementation;
import com.synclab.ecommerce.utility.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
public class CourierController {

    @Autowired
    private CourierServiceImplementation courierServiceImplementation;

    // post

    @PostMapping(value = "/insert")
    public ResponseEntity<Courier> insert(@RequestBody Courier request) {

        if (request == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Courier entity = courierServiceImplementation.insert(request);

        return ResponseEntity.ok(entity);
    }

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Courier> findById(@PathVariable(value = "id") String id) {

        Courier entity = courierServiceImplementation.findById(id);
        return CustomResponse.getFindResponse(entity, "record not found",
                "record with id: " + id + " could not be found");
    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Courier> deleteById(@PathVariable(value = "id") String id) {

        courierServiceImplementation.deleteById(id);
        Courier entity = courierServiceImplementation.findById(id);

        return CustomResponse.getDeleteResponse(entity, "deletion failed",
                "record with id: " + id + " could not be deleted");
    }

}
