package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
}
