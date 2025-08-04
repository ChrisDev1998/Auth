package com.ChrisJavaApiDev.Auth.Security;

import com.ChrisJavaApiDev.Auth.Entities.Users;
import com.ChrisJavaApiDev.Auth.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            //this is the methode for retrieve information about the users
        Users user= usersRepository.findByUsername(username).get();

        return User.withUsername(user.getUsername())
                .password("{noop}" + user.getPassword()) // sin codificar
                .roles(user.getRoles())
                .build();
    }
}
