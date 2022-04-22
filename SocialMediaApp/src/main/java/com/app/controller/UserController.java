package com.app.controller;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.app.model.User;

import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 
	
	 @PostMapping("/Register")
	 public ResponseEntity<?> RegisterUser(@RequestBody User user) {
		
		User registerUser = new User();
		
		//email validation with regex
		
		  String regex ="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		  Pattern pattern = Pattern.compile(regex); 
		  String email = user.getEmail();
		
		//check if user is available or not
		 if(userService.existsByUsername(user.getUsername())){
			 return new ResponseEntity<>("Username is not available please try something else", HttpStatus.BAD_REQUEST);
		 }
		
		 //check if email is available or not
		 if(userService.existsByEmail(user.getEmail())){
			 return new ResponseEntity<>("email is already used please use other email other wise login with this email", HttpStatus.BAD_REQUEST);
		 }
		 
			
			  //email validation 
		 if(!pattern.matcher(email).matches()) { 
			 return new ResponseEntity<>("please enter valid email address", HttpStatus.BAD_REQUEST);
		}
			 
		 
		//registration after all validation
		registerUser.setUsername(user.getUsername());
		registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
		registerUser.setEmail(user.getEmail());
		registerUser.setRoles("ROLE_NORMAL");
		User registeredUser = userService.registerUser(registerUser);
	
		return ResponseEntity.ok(registeredUser);
	}


	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody User user){
		 
		
		//check username exit or not
		 if(!userService.existsByUsername(user.getUsername())){
			 return new ResponseEntity<>("Username is not find!", HttpStatus.BAD_REQUEST);
		 }
	
		 //after checking username and password authentication done
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		       
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
	            
	     }

	

	
}
