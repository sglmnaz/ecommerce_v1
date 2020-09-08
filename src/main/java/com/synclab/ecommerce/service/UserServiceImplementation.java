package com.synclab.ecommerce.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findByFirstName(String name) {
		return userRepository.findByFirstName(name);
	}

	@Override
	public User insert(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(BigInteger id) {
		return userRepository.findById(id);
	}

}
