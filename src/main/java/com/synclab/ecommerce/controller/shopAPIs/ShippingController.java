package com.synclab.ecommerce.controller.shopAPIs;

import com.synclab.ecommerce.model.*;
import com.synclab.ecommerce.service.courier.CourierServiceImplementation;
import com.synclab.ecommerce.service.order.OrderServiceImplementation;
import com.synclab.ecommerce.service.shipping.ShippingServiceImplementation;
import com.synclab.ecommerce.service.status.StatusServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.response.CustomResponse;

import java.util.Date;
import java.util.List;

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
    private OrderServiceImplementation orderServiceImplementation;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private CourierServiceImplementation courierServiceImplementation;

    @Autowired
	private StatusServiceImplementation statusServiceImplementation;

    // post

    @PostMapping(value = "/insert")
    public ResponseEntity<Shipping> insert(@RequestParam(name = "orderId") String orderId,
                                           @RequestParam(name = "userId") String userId,
                                           @RequestParam(name = "addressId") String addressId,
                                           @RequestParam(name = "courierId") String courierId) {

        Order order = orderServiceImplementation.findById(orderId);
        
        if (!order.getStatus().getName().equalsIgnoreCase("STATUS_CREATED"))
        	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        
        Courier courier = courierServiceImplementation.findById(courierId);
        User user = userServiceImplementation.findById(userId);
        Address address = null;
        
        List<Address> addresses = user.getAddress();
        
        for (Address _address : addresses) {
			if (_address.getAddressId().equals(addressId))
				address = _address;
		}

        if (order == null || address == null || user == null || courier == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Shipping shipping = new Shipping();
        shipping.setOrderId(orderId);
        shipping.setRecipient(user.getFirstName() + " " + user.getLastName());
        shipping.setAddress(address);
        shipping.setCourier(courier);
        shipping.setShippingDate(new Date());
        
        order.setStatus(statusServiceImplementation.findByName("STATUS_TRANSPORT"));
        orderServiceImplementation.update(order);

        shipping = shippingServiceImplementation.insert(shipping);
        return ResponseEntity.ok(shipping);

    }

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Shipping> findById(@PathVariable(value = "id") String id) {

        Shipping entity = shippingServiceImplementation.findById(id);
        return CustomResponse.getFindResponse(entity, "record not found",
                "record with id: " + id + " could not be found");
    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Shipping> deleteById(@PathVariable(value = "id") String id) {

        shippingServiceImplementation.deleteById(id);
        Shipping entity = shippingServiceImplementation.findById(id);

        return CustomResponse.getDeleteResponse(entity, "deletion failed",
                "record with id: " + id + " could not be deleted");
    }

}
