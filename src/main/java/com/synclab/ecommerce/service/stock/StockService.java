package com.synclab.ecommerce.service.stock;

import java.util.List;

import com.synclab.ecommerce.model.Stock;

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
