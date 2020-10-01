package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Stock;

@Repository	
public interface StockItemRepository extends JpaRepository<Stock, Long> {

}
