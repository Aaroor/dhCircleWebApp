package com.dhcircle.dhCircleWebApp.webcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;

@Controller
@RequestMapping(value = "/admin")
public class AdminPageController {
	@Autowired
	UserRepository userRepo;
	@GetMapping(value = "/home")
	public String viewRootPage() {
		return "adminHome";
	}
	
	@GetMapping(value = "/user-list")
	public String viewUserList(Model model) {
		model.addAttribute("lUsers", userRepo.findAll());
		return "user-list";
	}
	
	@GetMapping(value = "/edit-user/{id}")
	public String getEditUser(@PathVariable long id, Model model) {
//		AjaxResponseBody result = new AjaxResponseBody();
		User exeUser = userRepo.findById(id).get();
		model.addAttribute("user", exeUser);
		return "user/admin-edit-register";
//		result.setUser(exeUser);
//		result.setStatus("Done");
//		return ResponseEntity.ok(result);
		
	}
}
