package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.model.Post;
import com.app.model.User;

@Service
public interface PostService {
	
	public List<Post> findByUser(User user);
	
	public Post addPost(Post post);


}
