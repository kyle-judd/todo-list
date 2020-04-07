package com.workhardkj.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workhardkj.entity.Role;
import com.workhardkj.entity.User;
import com.workhardkj.repositories.RoleRepository;
import com.workhardkj.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(username));
		if(optionalUser.isPresent()) {
			User retrievedUser = optionalUser.get();
			return new org.springframework.security.core.userdetails.User
					(retrievedUser.getUsername(), retrievedUser.getPassword(), mapRolesToAuthorities(retrievedUser.getRoles()));
		} else {
			throw new UsernameNotFoundException("User with username of " + username + " could not be found!");
		}
		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getType())).collect(Collectors.toList());
	}

	@Override
	public User findUserById(Long id) throws Exception {
		Optional<User> optionalUser = userRepository.findById(id);
		User retrievedUser;
		if(optionalUser.isPresent()) {
			retrievedUser = optionalUser.get();
		} else {
			retrievedUser = null;
		}
		return retrievedUser;
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(username));
		User retrievedUser;
		if(optionalUser.isPresent()) {
			retrievedUser = optionalUser.get();
		} else {
			retrievedUser = null;
		}
		return retrievedUser;
	}

	@Override
	public void save(User user) throws Exception {
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		Optional<Role> optionalRole = roleRepository.findRoleByType("ROLE_USER");
		if(optionalRole.isPresent()) {
			user.setRoles(Arrays.asList(optionalRole.get()));
		} else {
			throw new Exception("Could not find role");
		}
		userRepository.save(user);
	}

}
