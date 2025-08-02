package com.ChrisJavaApiDev.Auth.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            //this is the methode for retrieve information about the users
        if (!username.equals("root")) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.withUsername("root")
                .password("{noop}1234") // sin codificar
                .roles("USER")
                .build();
    }
}
