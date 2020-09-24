package com.synclab.ecommerce.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.repository.RoleRepository;

@Service
public class RoleServiceImplementation implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
