package com.dhcircle.dhCircleWebApp.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dhcircle.dhCircleWebApp.model.user.ConfirmationToken;
import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.user.ConfirmationTokenRepository;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;

@Controller
@RequestMapping(value = "/")
public class LoginController {
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewRootPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminLogin");
		return modelAndView;
	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String loginPage(@RequestParam(value = "error", required = false)) {
//		return "adminLogin";
//	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminLogin");
		return modelAndView;
	}

	@GetMapping(value = "/registration")
	public String registrationPage() {
		return "admin-register";
	}

	@GetMapping(value = "/subscriber-login")
	public String subscriberLoginPage() {
		return "subscriberLogin";
	}

	@GetMapping(value = "/view")
	public String viewPage() {
		return "guestHome";
	}

	@GetMapping(value = "/access-denied")
	public String error404Page() {
		return "error404";
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepo.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setEnabled(true);
			userRepo.save(user);
			modelAndView.setViewName("email-verified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error-confirm");
		}

		return modelAndView;
	}
	@GetMapping(value="/email")
	public String getEmailPage(){
		return "email/email-template";
	}

//	@RequestMapping(value = "/error", method = RequestMethod.GET)
//    public ModelAndView errorPage(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error404");
//        return modelAndView;
//    }
}
