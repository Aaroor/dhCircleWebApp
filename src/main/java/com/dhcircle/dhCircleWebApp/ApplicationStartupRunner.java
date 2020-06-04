package com.dhcircle.dhCircleWebApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.dhcircle.dhCircleWebApp.model.user.Role;
import com.dhcircle.dhCircleWebApp.repository.user.RoleRepository;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;


@Component
public class ApplicationStartupRunner implements ApplicationRunner {
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		System.out.println("Start");
//		List<Role> lRole = new ArrayList<Role>();
//		Role role = new Role();
//		role.setRole("SUBSCRIBER");
//		lRole.add(role);
//		Role role1 = new Role();
//		role1.setRole("ADMIN");
//		lRole.add(role1);
//		Role role2 = new Role();
//		role2.setRole("SUPERADMIN");
//		lRole.add(role2);
//		roleRepo.saveAll(lRole);
////		roleRepo.save(role);
//		System.out.println("End");
		//System.out.println(userRepo.findByEmail("aarooran.kanthasamy@team4.cms.lk"));
		//User u =userRepo.findByEmail("aarooran.kanthasamy@team4.cms.lk");
//		User u =userRepo.findById(23L).get();
//		userRepo.delete(u);
		
//		User user =userRepo.findByEmail("aaroor2015@gmail.com");
//		user.setEnabled(true);
//		userRepo.save(user);
		//userRepo.save(userRepo.findByEmail("aaroor2015@gmail.com").setEnabled(true));
		
//		User user = userRepo.findById(28L).get();
//		userRepo.deleteById(28L);
		
		
		
		
		
		
	}

}
