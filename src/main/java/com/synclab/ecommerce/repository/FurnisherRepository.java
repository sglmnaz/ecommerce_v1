package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnisherRepository extends JpaRepository<FurnisherRepository, Long> {

}
