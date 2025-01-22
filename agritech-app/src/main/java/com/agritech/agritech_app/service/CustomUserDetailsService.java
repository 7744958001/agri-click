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
        // Replace this with your actual user fetching logic
        if ("mukundpokale8001@gmail.com".equals("mukundpokale8001@gmail.com")) {
            return new User("mukundpokale8001@gmail.com", "$2a$10$srAunfr2xZh5dWhI9upFNOIzgqxIzVvUH1ZbBZwHg5UeNiDNsXE5q", Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
