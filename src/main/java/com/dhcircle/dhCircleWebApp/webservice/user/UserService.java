package com.dhcircle.dhCircleWebApp.webservice.user;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhcircle.dhCircleWebApp.model.user.Role;
import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.user.RoleRepository;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//user.setActive(true);
		user.setEnabled(false);
		return userRepository.save(user);
	}
}
