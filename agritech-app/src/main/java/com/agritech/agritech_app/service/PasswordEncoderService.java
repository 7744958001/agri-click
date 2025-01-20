package com.agritech.agritech_app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderService {
	 private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    public String encode(String rawPassword) {
	        return passwordEncoder.encode(rawPassword);
	    }

	    public boolean matches(String rawPassword, String encodedPassword) {
	        return passwordEncoder.matches(rawPassword, encodedPassword);
	    }
}
