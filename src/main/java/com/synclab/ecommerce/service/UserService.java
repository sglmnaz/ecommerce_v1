package com.synclab.ecommerce.service;

import java.math.BigInteger;
import java.util.Optional;

import com.synclab.ecommerce.model.User;

public interface UserService {

	Optional<User> findByFirstName(String name);

	Optional<User> findById(BigInteger id);

	User insert(User user);

}
