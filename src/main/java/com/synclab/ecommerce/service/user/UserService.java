package com.synclab.ecommerce.service.user;

import java.util.List;
import java.util.Optional;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.User;

public interface UserService {

	//C
	User insert(User user);
    //R
	Optional<User> findById(Long id);
	User findByAccount(Account account);
	Optional<User> findByFirstName(String name);
	List<User> findAll();
    //U
    User UpdateById(Long id, User user) throws Exception;
    User PatchById(Long id, User user) throws Exception;
    //D
    void DeleteById(Long id);
    void deleteAll();

}
