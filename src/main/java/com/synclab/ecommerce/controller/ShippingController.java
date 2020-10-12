package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.*;
import com.synclab.ecommerce.service.Order.OrderServiceImplementation;
import com.synclab.ecommerce.service.address.AddressServiceImplementation;
import com.synclab.ecommerce.service.courier.CourierServiceImplementation;
import com.synclab.ecommerce.service.shipping.ShippingServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingServiceImplementation shippingServiceImplementation;

    @Autowired
    private AddressServiceImplementation addressServiceImplementation;

    @Autowired
    private OrderServiceImplementation orderServiceImplementation;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private CourierServiceImplementation courierServiceImplementation;

    // post

    @PostMapping(value = "/insert")
    public ResponseEntity<Shipping> insert(@RequestParam(name = "orderId") Long orderId,
                                           @RequestParam(name = "userId") Long userId, @RequestParam(name = "addressId") Long addressId,
                                           @RequestParam(name = "courierId") Long courierId) {

        Order order = orderServiceImplementation.findById(orderId);
        Address address = addressServiceImplementation.findById(addressId);
        User user = userServiceImplementation.findById(userId);
        Courier courier = courierServiceImplementation.findById(courierId);

        if (order == null || address == null || user == null || courier == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Shipping shipping = new Shipping();
        shipping.setAddress(address);
        shipping.setOrder(order);
        shipping.setCourier(courier);
        shipping.setRecipient(user.getFirstName() + " " + user.getLastName());

        shipping = shippingServiceImplementation.insert(shipping);
        return ResponseEntity.ok(shipping);

    }

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Shipping> findById(@PathVariable(value = "id") Long id) {

        Shipping entity = shippingServiceImplementation.findById(id);
        return CustomResponse.getFindResponse(entity, "record not found",
                "record with id: " + id + " could not be found");
    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Shipping> deleteById(@PathVariable(value = "id") Long id) {

        shippingServiceImplementation.deleteById(id);
        Shipping entity = shippingServiceImplementation.findById(id);

        return CustomResponse.getDeleteResponse(entity, "deletion failed",
                "record with id: " + id + " could not be deleted");
    }

}
