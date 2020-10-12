package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstName(String name);

    User findByAccount(Account account);

    User findByAccount_username(String username);

    User findByAccount_email(String email);

    List<User> findByAddress(Address address);

}
