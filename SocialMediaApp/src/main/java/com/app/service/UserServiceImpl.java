package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Post;
import com.app.model.User;
import com.app.repository.PostRepository;
import com.app.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	

	
	@Override
	public User registerUser(User user) {
		User save = userRepo.save(user);
		return save;
	}

	public Boolean existsByPassword(String password) {
		Boolean existsByPassword = userRepo.existsByPassword(password);
		return existsByPassword;
	}

	
	public Boolean existsByUsername(String username) {
		Boolean existsByUsername = userRepo.existsByUsername(username);
		return existsByUsername;
	}

	@Override
	public User findByUserId(int userId) {
		User findByUserId = userRepo.findByUserId(userId);
		return findByUserId;
	}

	@Override
	public Boolean existsByEmail(String email) {
		Boolean existsByEmail = userRepo.existsByEmail(email);
		return existsByEmail;
	}

	

	

}
