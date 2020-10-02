package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.StockItem;

@Repository	
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

}
