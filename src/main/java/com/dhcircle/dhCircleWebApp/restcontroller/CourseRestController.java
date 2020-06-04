package com.dhcircle.dhCircleWebApp.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcircle.dhCircleWebApp.courseWrapper.CategoryWrapper;
import com.dhcircle.dhCircleWebApp.model.course.CourseCategory;
import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.courses.CourseCategoryRepository;
import com.dhcircle.dhCircleWebApp.repository.courses.CoursesRepository;
import com.dhcircle.dhCircleWebApp.repository.courses.ResourcesRepository;
import com.dhcircle.dhCircleWebApp.response.AjaxResponseBody;

@RestController
@RequestMapping(value = "/api/admin")
public class CourseRestController {
	@Autowired
	private CourseCategoryRepository courseCategoryRepo;
	@Autowired
	private CoursesRepository coursesRepo;
	@Autowired
	private ResourcesRepository resoourceRepo;
	
	@GetMapping(value="/all-course-category-list")
	public List<CourseCategory> getAllUsers(){
		return courseCategoryRepo.findAll();
	}

	@PostMapping(value = "/create-course-category")
	public ResponseEntity<?> createCourseCategory(@ModelAttribute CategoryWrapper categoryWrapper, Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		System.out.println("Front end Category : " + categoryWrapper);
		if (errors.hasErrors()) {
			result.setMessage(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			result.setStatus("Failure");
			return ResponseEntity.badRequest().body(result);
		}

		if (courseCategoryRepo.findByTopicName(categoryWrapper.getTopicName()) == null) {
			CourseCategory category = new CourseCategory();
			category.setTopicName(categoryWrapper.getTopicName());
			courseCategoryRepo.save(category);
			result.setStatus("Done");
			return ResponseEntity.ok(result);
		} else {
			System.out.println("Failure not save");
			result.setStatus("Failure");
			result.setMessage("Course category already available in the system. Please try with different name");
			return ResponseEntity.ok(result);
		}

	}

}
