package com.dhcircle.dhCircleWebApp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhcircle.dhCircleWebApp.model.user.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
    User findByUserName(String userName);
    User findByEmailIgnoreCase(String email);
}
