package com.synclab.ecommerce.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.UserRepository;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

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
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User UpdateById(Long id, User user) throws Exception {
		
		if(findById(id) == null)
			throw new RecordNotFoundException();
		
		User newUser = user;
		newUser.setUserId(id);
		
		return userRepository.save(newUser);

	}

	@Override
	public User PatchById(Long id, User user) throws Exception {
		
		User newUser = findById(id).get();
		if (newUser == null)
			throw new RecordNotFoundException();
		
		if(user.getUserId() != null)
			newUser.setUserId(user.getUserId());
		if(user.getAccount() != null)
			newUser.setAccount(user.getAccount());
		if(user.getAddresses() != null)
			newUser.setAddresses(user.getAddresses());
		if(user.getCart() != null)
			newUser.setCart(user.getCart());
		if(user.getFirstName() != null)
			newUser.setFirstName(user.getFirstName());
		if(user.getLastName() != null)
			newUser.setLastName(user.getLastName());
		if(user.getLastLoginDate() != null)
			newUser.setLastLoginDate(user.getLastLoginDate());
		if(user.getSignupDate() != null)
			newUser.setSignupDate(user.getSignupDate());
		
		
		userRepository.save(newUser);
		return newUser;
		
	}

	@Override
	public void DeleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
		
	}

	@Override
	public User findByAccount(Account account) {
		return userRepository.findByAccount(account);
	}

}
