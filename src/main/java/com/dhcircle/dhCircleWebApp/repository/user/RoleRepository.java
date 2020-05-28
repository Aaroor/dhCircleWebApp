package com.dhcircle.dhCircleWebApp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhcircle.dhCircleWebApp.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByRole(String role);
}
