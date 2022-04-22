package com.app.service;

import org.springframework.stereotype.Service;

import com.app.model.User;

@Service
public interface UserService {
	
	public User registerUser(User user);
	
	
	 public Boolean existsByUsername(String username);
	    
	    
	 public Boolean existsByPassword(String password);

	 public User findByUserId(int userId);
	 
	 public Boolean existsByEmail(String email);



}
