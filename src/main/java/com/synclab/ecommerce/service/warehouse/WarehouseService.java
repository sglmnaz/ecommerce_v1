package com.synclab.ecommerce.service.warehouse;

import com.synclab.ecommerce.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    // insert
    Warehouse insert(Warehouse entity);

    // retrieve
    Warehouse findById(Long id);

    List<Warehouse> findAll();

    // update
    Warehouse update(Warehouse entity);

    // delete
    void deleteById(Long id);

}
