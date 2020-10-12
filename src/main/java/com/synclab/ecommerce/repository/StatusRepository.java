package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);

}
