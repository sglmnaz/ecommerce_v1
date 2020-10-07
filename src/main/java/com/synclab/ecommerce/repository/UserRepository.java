package com.synclab.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByFirstName(String name);
	User findByAccount(Account account);
	User findByAccount_username(String username);
	User findByAccount_email(String email);
	List<User> findByAddress(Address address);
	
}
