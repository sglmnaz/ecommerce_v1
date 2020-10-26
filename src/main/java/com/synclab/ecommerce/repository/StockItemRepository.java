package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.StockItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends MongoRepository<StockItem, String> {

}
