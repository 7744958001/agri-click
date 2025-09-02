package com.agritech.agritech_app.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, name = "user_name")
	private String username;

	@Column(nullable = false, name = "user_password")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> userRole;
	
	@Column(nullable = true, name="first_name")
	private String firstName;

	@Column(nullable = true, name = "last_name")
	private String lastName;
	
	@Column(nullable = true, name = "phone_number")
	private Integer phoneNumber;
	
	@Column(nullable = true, name = "user_photo_path")
	private String userfilePath;
	
	public Set<String> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<String> userRole) {
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserfilePath() {
		return userfilePath;
	}

	public void setUserfilePath(String userfilePath) {
		this.userfilePath = userfilePath;
	}
	
	
}
