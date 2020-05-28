package com.dhcircle.dhCircleWebApp.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;

@Controller
@RequestMapping(value = "/admin")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@GetMapping(value = "/registraion")
	public String registrationPage() {
		return "admin-register";
	}
	
	@GetMapping(value = "/user-profile")
	public String userProfilePage() {
		return "user-profile";
	}
	
	@GetMapping(value = "/add-subscriber")
	public String subscriberRegistration() {
		return "user/subscriber-register";
	}
	
	@GetMapping(value = "/add-paid-subscriber")
	public String paidSubscriberRegistration() {
		return "user/paid-subscriber-register";
	}
	

   
}
