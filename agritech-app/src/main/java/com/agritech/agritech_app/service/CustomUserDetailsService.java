package com.agritech.agritech_app.service;

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
        // Dummy user for demonstration. Replace this with database logic.
        if ("user".equals(username)) {
            return new User(
                "user",
                "$2a$10$u7WbHV1yZ6WQ8Y0Tycb4xOkFs8x/n6/HMbAvQKOPMfU9LFOsRmcJS", // "password" (BCrypt hashed)
                Collections.emptyList() // No roles
            );
        }
        throw new UsernameNotFoundException("User not found");
    }
}
