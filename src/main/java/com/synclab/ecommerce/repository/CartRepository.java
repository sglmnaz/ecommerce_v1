package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
    Void deleteByUser_userId(Long userId);
}
