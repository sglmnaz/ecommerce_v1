package com.synclab.ecommerce.service.user;

import java.util.List;
import java.util.Optional;

import com.synclab.ecommerce.model.User;

public interface UserService {

	Optional<User> findByFirstName(String name);

	Optional<User> findById(Long id);

	List<User> findAll();

	User insert(User user);

}
