package com.synclab.ecommerce.service.stock;

import com.synclab.ecommerce.model.Stock;

import java.util.List;

public interface StockService {

    // insert
    Stock insert(Stock entity);

    // retrieve
    Stock findById(Long id);

    List<Stock> findAll();

    // update
    Stock update(Stock entity);

    // delete
    void deleteById(Long id);

}
