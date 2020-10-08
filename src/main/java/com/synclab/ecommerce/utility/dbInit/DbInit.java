package com.synclab.ecommerce.utility.dbInit;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;

import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

@Service
public class DbInit implements CommandLineRunner{ //this class creates some placeholder entities in the db for testing purpose

	private UserServiceImplementation userRepository;
	private AccountServiceImplementation accountRepository;
	private RoleServiceImplementation roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public DbInit(UserServiceImplementation userRepository,
			AccountServiceImplementation accountRepository,
			RoleServiceImplementation roleRepository,
			PasswordEncoder passwordEncoder ) {
		
		this.userRepository = userRepository;
		this.accountRepository  = accountRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void run(String... args) throws Exception {
		generateUsers(10);
	}

	
	private void generateUsers(Integer n) { //generate users
		for (int i = 0; i < n; i++) {
			ArrayList<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByName("ROLE_USER"));
			Account account = new Account("user"+i, "email" + i + "@synclab.net",passwordEncoder.encode("password") ,null, null , roles) ;
			User user = new User(accountRepository.insert(account),"firstname"+i,"lastname"+i,null);
			userRepository.insert(user);
		}
	}
	
	private void generateRoles() { // generate roles
		roleRepository.insert(new Role("ROLE_ADMIN",null));
		roleRepository.insert(new Role("ROLE_MANAGER",null));
		roleRepository.insert(new Role("ROLE_USER",null));
	}
	

}
