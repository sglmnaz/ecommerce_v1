package com.synclab.ecommerce.service;

import java.util.Optional;

import com.synclab.ecommerce.model.User;

public interface UserService {

	Optional<User> findByFirstName(String name);

	Optional<User> findById(long id);

	User insert(User user);

}
