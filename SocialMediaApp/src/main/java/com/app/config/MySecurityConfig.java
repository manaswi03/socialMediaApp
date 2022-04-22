package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.service.CustomUserDetailsService;


//Confugration for spring security
@Configuration
@EnableWebSecurity
public class MySecurityConfig  extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//to make public to user and post url
		http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/post/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
	}
	
	
	//to authonticate the user details
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	 }
	
	
	//to encode the password
	@Bean
	public PasswordEncoder passwordEncoder(){

			return new BCryptPasswordEncoder();
	 }

}
