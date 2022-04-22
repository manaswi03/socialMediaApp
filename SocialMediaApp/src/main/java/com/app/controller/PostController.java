package com.app.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.handler.FileUploadHelper;
import com.app.model.Post;
import com.app.model.User;
import com.app.repository.PostRepository;
import com.app.service.PostService;
import com.app.service.UserService;


//to posting 
@RestController
@RequestMapping("/post")
public class PostController {
	
	
	 @Autowired
	 private FileUploadHelper fileUploadHelper;
	 
	 
	 @Autowired
	 private PostService postService;
	
	 @Autowired
	 private UserService userService;
	
	 
	 
	 //To Upload the post 
	 @PostMapping("/{userId}/upload-post")
	 public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("userId") int userId)
	 {
			
		 try 
		 {
			 //it checked file is uploaded or not
			 if(file.isEmpty())
			 {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			 }
			 
			 //it check is that jpg formated img or not
			 if(!file.getContentType().equals("image/jpeg"))
			 {
				 return ResponseEntity.status(HttpStatus. INTERNAL_SERVER_ERROR).body("Only jpg is allow ");
			 }
			 
			 //after file is uploaded 
			 boolean f= fileUploadHelper.uploadFile(file);
			 if(f)
			 {
				 //return ResponseEntity.ok("File is successfully uploaded");
				 

				 Date utilDate = new Date();
		         java.sql.Date date = new java.sql.Date(utilDate.getTime());
		         
		         User user = userService.findByUserId(userId);
				 Post p = new Post();
				 p.setUser(user);
				 p.setPostImg(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
				 p.setCreatedDate(date);
				 
				 //saving the post in the database
				 Post save = postService.addPost(p);
				 return ResponseEntity.ok(save);

			 }
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 //if some othe error occure
		 return ResponseEntity.status (HttpStatus. INTERNAL_SERVER_ERROR).body("Some thing went wrong time again leter");
	 }
	 
	 
	 
	 //to display all the post 
	 @GetMapping("allPost/{userId}")
	 public ResponseEntity<?> getPostByUser(@PathVariable("userId") int userId) {
		 
		 //display the post of perticular user
		 List<Post> list = postService.findByUser(userService.findByUserId(userId));
		 
		 //check the post is there or not
		 if(list.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO POST");
		}
		 
		 //to display all the post
		 return ResponseEntity.ok(list);
	 }
	 
}
