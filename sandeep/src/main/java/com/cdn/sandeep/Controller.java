package com.cdn.sandeep;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdn.sandeep.model.User;
import com.cdn.sandeep.repository.UserRepository;
import com.cdn.sandeep.service.MailSenderService;
import com.cdn.sandeep.util.UserValidatorUtil;




@RestController
public class Controller {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MailSenderService mailSenderService;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {

	}

	@PostMapping("/saveuser")
	public ResponseEntity<?> saveUser(@RequestBody User user) {

		if (UserValidatorUtil.isAnyUserAttributeIsNull(user)) {
			return new ResponseEntity("one or more attribute is null", HttpStatus.CONFLICT);
		}

		if (UserValidatorUtil.isEmailNotValid(user.getEmail())) {
			return new ResponseEntity("invalid email", HttpStatus.CONFLICT);

		}
		if (UserValidatorUtil.isPasswordNotValid(user.getPassword())) {
			return new ResponseEntity("user password is not valid", HttpStatus.CONFLICT);

		}

		// checking whether user exists
		User fetchedUser = userRepository.findByEmail(user.getEmail());

		if (fetchedUser == null) {
			User savedUser = userRepository.save(user);
			if (savedUser != null) {

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("Complete Registration!");
				mailMessage.setFrom("sandeepkumar4793@gmail.com");
				mailMessage.setText("To confirm your account, please click here : "
						+ "http://localhost:8080/confirm-account?token=" + user.getToken());

				mailSenderService.sendEmail(mailMessage);

				System.out.println("Mail sent  Sucessfully");
				return new ResponseEntity(user, HttpStatus.OK);

			}

		}else {
	       
			System.out.println("user exists");
			return new ResponseEntity("user email already exists", HttpStatus.CONFLICT);

		}
		return null;
		
	}
	
	
	
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?>  confirmUserAccount( @RequestParam("token")String confirmationToken)
	{
		User userwithToken  =userRepository.findByToken(confirmationToken);
		//calculate expiration time 
		//if its expired 
		
		//else persits to table 2
		if( userwithToken != null)
		{
		
			//todo implementation
		
			
		}
		else
		{
		
		}
		
		return null;
	
	
	
	
	}
	
	

	@GetMapping("/getallusers")
	public List<User> getAll() {
		List<User> list = userRepository.findAll();
		return list;
	}

}
