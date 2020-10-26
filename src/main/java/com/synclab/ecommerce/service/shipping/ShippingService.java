package com.synclab.ecommerce.service.shipping;

import com.synclab.ecommerce.model.Shipping;

import java.util.List;

public interface ShippingService {

    // insert
    Shipping insert(Shipping record);

    // retrieve
    Shipping findById(String id);

    List<Shipping> findAll();

    // update
    Shipping update(Shipping record);

    // delete
    void deleteById(String id);

    void deleteAll();

}
