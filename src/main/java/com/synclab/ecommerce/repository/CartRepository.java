package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUserId(String id);
    Void deleteByUserId(String id);
}
