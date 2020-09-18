package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByEmail(String email);
	
}