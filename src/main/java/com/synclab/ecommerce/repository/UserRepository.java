package com.synclab.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByFirstName(String name);
	User findByAccount(Account account);
	
}
