package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique = true)
	private String username;
	
	@NonNull
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String roles;
	
	private int isEnabled;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String username, String password, String email, String roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
	
	
	
	public int isEnabled() {
		return isEnabled;
	}

	public void setEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", roles=" + roles + "]";
	}

	
	

}
