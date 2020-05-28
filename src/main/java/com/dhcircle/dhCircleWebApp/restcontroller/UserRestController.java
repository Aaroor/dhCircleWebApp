package com.dhcircle.dhCircleWebApp.restcontroller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcircle.dhCircleWebApp.adminWrapper.UserWrapper;
import com.dhcircle.dhCircleWebApp.model.user.ConfirmationToken;
import com.dhcircle.dhCircleWebApp.model.user.Mail;
import com.dhcircle.dhCircleWebApp.model.user.Role;
import com.dhcircle.dhCircleWebApp.model.user.User;
import com.dhcircle.dhCircleWebApp.repository.user.ConfirmationTokenRepository;
import com.dhcircle.dhCircleWebApp.repository.user.RoleRepository;
import com.dhcircle.dhCircleWebApp.repository.user.UserRepository;
import com.dhcircle.dhCircleWebApp.response.AjaxResponseBody;
import com.dhcircle.dhCircleWebApp.webservice.user.EmailSenderService;
import com.dhcircle.dhCircleWebApp.webservice.user.UserService;

@RestController
@RequestMapping(value="/api/admin")
public class UserRestController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserService userService;
	
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
    private EmailSenderService emailSenderService;
	
	@PostMapping(value = "/create-user")
	public ResponseEntity<?> createUser(@ModelAttribute UserWrapper userWrapper,Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		System.out.println("User Request : "+userWrapper);
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            result.setStatus("Failure");
            return ResponseEntity.badRequest().body(result);

        }
        
        User user = new User();
        user.setUserName(userWrapper.getUserName());
        user.setFirstName(userWrapper.getFirstName());
        user.setLastName(userWrapper.getLastName());
        user.setPassword(userWrapper.getPassword());
        user.setPhoneNumber(userWrapper.getPhoneNumber());
        user.setEmail(userWrapper.getEmail());
        user.setRoles(new HashSet<>(Arrays.asList(roleRepo.findByRole(userWrapper.getUserRole()))));
        userService.saveUser(user);
        result.setMessage("successfully create");
        result.setStatus("Done");
        return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/edit-system-user")
	public ResponseEntity<?> editSystemUser(@ModelAttribute UserWrapper userWrapper,Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		System.out.println("User Request : "+userWrapper);
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            result.setStatus("Failure");
            return ResponseEntity.badRequest().body(result);

        }
        
        User user = userRepo.findById(userWrapper.getId()).get();
        user.setUserName(userWrapper.getUserName());
        user.setFirstName(userWrapper.getFirstName());
        user.setLastName(userWrapper.getLastName());
      //  user.setPassword(userWrapper.getPassword());
        user.setPhoneNumber(userWrapper.getPhoneNumber());
        user.setEmail(userWrapper.getEmail());
      //  user.setRoles(new HashSet<>(Arrays.asList(roleRepo.findByRole(userWrapper.getUserRole()))));
        userRepo.save(user);
        
        result.setMessage("successfully create");
        result.setStatus("Done");
        return ResponseEntity.ok(result);
	}
	
	
	@PostMapping(value = "/create-subscriber")
	@Transactional
	public ResponseEntity<?> createSubscriber(@ModelAttribute UserWrapper userWrapper,Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		System.out.println("User Request : "+userWrapper);
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            result.setStatus("Failure");
            return ResponseEntity.badRequest().body(result);

        }
        
        User existingUser = userRepo.findByEmailIgnoreCase(userWrapper.getEmail());
        if(existingUser != null) {
        	result.setMessage("This email already exists!");
        	result.setStatus("Failure");
        }
        try {
	        User user = new User();
	        user.setUserName(userWrapper.getUserName());
	        user.setFirstName(userWrapper.getFirstName());
	        user.setLastName(userWrapper.getLastName());
	        user.setPassword(userWrapper.getPassword());
	        user.setPhoneNumber(userWrapper.getPhoneNumber());
	        user.setEmail(userWrapper.getEmail());
	        user.setRoles(new HashSet<>(Arrays.asList(roleRepo.findByRole("SUBSCRIBER"))));
	        userService.saveUser(user);
	        ConfirmationToken confirmationToken = new ConfirmationToken(user);
	        confirmationTokenRepository.save(confirmationToken);
	       // SimpleMailMessage mailMessage = new SimpleMailMessage();
	        Mail mail = new Mail();
	        mail.setEmail(user.getEmail());
	        mail.setMessage("http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
	        mail.setObject("test mail");
	        
//	        mailMessage.setTo(user.getEmail());
//	        mailMessage.setSubject("Complete Registration!");
//	        mailMessage.setFrom("aaroor2015@gmail.com");
//	        mailMessage.setText("To confirm your account, please click here : "
//	                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
//	        emailSenderService.sendEmail(mailMessage);
	        
	        emailSenderService.send(mail);
	        
	        result.setMessage("Successfully create a subscriber please confirm the account through "+userWrapper.getEmail());
	        result.setStatus("Done");
        }catch(Exception e) {
        	StringWriter exceptionError = new StringWriter();
        	e.printStackTrace(new PrintWriter(exceptionError));
        	result.setMessage(exceptionError.toString());
        	result.setStatus("Failure");
        }
        
        return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/all-user-list")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@PostMapping(value = "/change-user-status")
	public AjaxResponseBody addUserVisibility(@RequestBody UserWrapper request) {
		System.out.println("Request : "+request);
		User exeUser = userRepo.findById(request.getId()).get();
		//exeUser.setActive(request.isActive());
		exeUser.setEnabled(request.isActive());
		userRepo.save(exeUser);
		AjaxResponseBody response = new AjaxResponseBody();
		response.setStatus("Done");
		if(!request.isActive()) {
			response.setMessage("Successfully de-activated this account");
		}else {
			response.setMessage("Successfully activated this account");
		}
		return response;
	}
	
	@PostMapping(value = "/remove-user")
	public ResponseEntity<?> removeUser(@ModelAttribute UserWrapper userWrapper,Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		System.out.println("User Request : "+userWrapper);
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            result.setStatus("Failure");
            return ResponseEntity.badRequest().body(result);

        }
        
        User user = userRepo.findById(userWrapper.getId()).get();
//        ConfirmationToken cof=confirmationTokenRepository.findByUser(user);
//        System.out.println(cof);
//        if(cof!=null) {
//        	confirmationTokenRepository.delete(cof);
//        }
//        Set<Role> lRole = user.getRoles();
     //   roleRepo.deleteAll(lRole);
        userRepo.delete(user);
        result.setMessage("successfully create");
        result.setStatus("Done");
        return ResponseEntity.ok(result);
	}
	
	
	
	
	
}
