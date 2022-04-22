package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Post;
import com.app.model.User;
import com.app.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	
	 @Autowired
	 private PostRepository postRepository;

	@Override
	public List<Post> findByUser(User user) {
		List<Post> findByUser = postRepository.findByUser(user);
		return findByUser;
	}

	@Override
	public Post addPost(Post post) {
		Post save = postRepository.save(post);
		return save;
	}
	
	
	

}
