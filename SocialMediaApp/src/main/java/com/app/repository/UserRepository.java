package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	
	//find user by username
	public User findByUsername(String username);
	
	//check if username is exit or not
    public Boolean existsByUsername(String username);
    
  //check if email is exit or not
    public Boolean existsByEmail(String email);
    
    
    public Boolean existsByPassword(String password);
    
    //find user by user id
    public User findByUserId(int userId);

}
