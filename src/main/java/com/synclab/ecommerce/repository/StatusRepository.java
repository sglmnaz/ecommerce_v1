package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
	Status findByName (String name);

}
