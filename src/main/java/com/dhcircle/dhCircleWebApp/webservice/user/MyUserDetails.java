package com.dhcircle.dhCircleWebApp.webservice.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dhcircle.dhCircleWebApp.model.user.Role;
import com.dhcircle.dhCircleWebApp.model.user.User;

/**
 * @author aarooran
 *
 */
public class MyUserDetails implements UserDetails {
	
	private User user;
	private String firstName;
	private String lastName;
	private String userName;
	
	public MyUserDetails(User user) {
        this.user = user;
    }
	
	public User getUser() {
		return this.user;
	}

	public String getUserName() {
		return this.user.getUserName();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return this.user.getLastName();
	}
	public String getFirstName() {
		return this.user.getFirstName();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}

}
