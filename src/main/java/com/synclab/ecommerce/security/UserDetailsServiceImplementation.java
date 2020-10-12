package com.synclab.ecommerce.security;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private UserServiceImplementation userServiceImp;

    public UserDetailsServiceImplementation(UserServiceImplementation userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userServiceImp.findByAccount_username(username);
        return new UserPrincipal(user);

    }

}
