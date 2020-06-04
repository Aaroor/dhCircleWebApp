package com.dhcircle.dhCircleWebApp.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhcircle.dhCircleWebApp.repository.courses.CourseCategoryRepository;
import com.dhcircle.dhCircleWebApp.repository.courses.CoursesRepository;
import com.dhcircle.dhCircleWebApp.repository.courses.ResourcesRepository;

@Controller
@RequestMapping(value = "/admin")
public class CoursePageController {
	@Autowired
	CoursesRepository coursesRepo;
	@Autowired
	CourseCategoryRepository courseCategoryRepo;
	@Autowired
	ResourcesRepository resourcesRepo;
	
	@GetMapping(value = "/course-management")
	public String showCourseManagementPage(Model model) {
		return "courses/course-management";
	}
	
	@GetMapping(value = "/category-list")
	public String viewUserList(Model model) {
		model.addAttribute("lCategory", courseCategoryRepo.findAll());
		return "courses/course-category-list";
	}

}
