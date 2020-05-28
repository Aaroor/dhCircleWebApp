package com.dhcircle.dhCircleWebApp.webservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
}
