package com.workhardkj.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.workhardkj.entity.User;

public interface UserService extends UserDetailsService {
	
	User findUserById(Long id) throws Exception;
	User findUserByUsername(String username);
	void save(User user) throws Exception;
}
