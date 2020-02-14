package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.facade.RoleRepository;
import com.example.facade.UserRepository;
import com.example.facade.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void save(User user,Collection<? extends Long> set) {

		ArrayList<Long> ids = new ArrayList<Long>();
		ids.addAll(set);
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(roleRepository.findAllById(ids)));
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
}
