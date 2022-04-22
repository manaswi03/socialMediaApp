package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Post;
import com.app.model.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	//find post by user
	public List<Post> findByUser(User user);

}
