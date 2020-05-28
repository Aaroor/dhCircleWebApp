package com.dhcircle.dhCircleWebApp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhcircle.dhCircleWebApp.model.user.ConfirmationToken;
import com.dhcircle.dhCircleWebApp.model.user.User;
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken findByUser(User user);
}
