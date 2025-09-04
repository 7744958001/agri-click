package com.agritech.agritech_app.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agritech.agritech_app.dto.UserRegistrationRequest;
import com.agritech.agritech_app.entity.User;
import com.agritech.agritech_app.service.UserService;
import com.agritech.agritech_app.util.ValidationUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/authentication")
@Validated
public class UserAuthentication {

	private final UserService userService;

	public UserAuthentication(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
		if (!ValidationUtil.isValidEmail(request.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
		}
		
		boolean isRegistered = userService.registerUser(request);
		if (isRegistered) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with the given email");
		}
	}
	
	//update user details
	@PostMapping("/updateUserDetails")
	public ResponseEntity<String> updateUserDetails(@Validated @RequestBody User request) {
		boolean isRegistered = false;
		if (!ValidationUtil.isValidEmail(request.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
		}else {
			 isRegistered = userService.updateUserDetails(request);
		}
		if (isRegistered) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User Details Updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Getting issue while updating user");
		}
	}
	
	//get user details for update
	
	@GetMapping("/getUserDetails")
	public ResponseEntity<?> getUserDetails(@Validated HttpSession httpSession) {
	    String userName = (String) httpSession.getAttribute("userName");
		User user = userService.getUserDetails(userName);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			Map<String, Object> error = new HashMap<>();
			error.put("code", 403);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		}
	}
}
