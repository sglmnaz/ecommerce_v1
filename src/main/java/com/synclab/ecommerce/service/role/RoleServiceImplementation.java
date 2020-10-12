package com.synclab.ecommerce.service.role;

import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role insert(Role role) {
        return roleRepository.save(role);
    }


}
