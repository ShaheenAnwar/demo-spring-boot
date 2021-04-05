package com.sa.SBSecureApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sa.SBSecureApp.model.User;
import com.sa.SBSecureApp.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//we have to get username from repo
		User user = repo.findByUsername(username);
		if (user==null)
			throw new UsernameNotFoundException("Invalid User");
		//here we need to return the object of UserDetails interface. We need to create a class to implement that 
		return new UserPrincipal(user);
	}

}
