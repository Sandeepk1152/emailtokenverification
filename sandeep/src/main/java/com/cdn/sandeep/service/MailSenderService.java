package com.cdn.sandeep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {
	 private JavaMailSender javaMailSender;

	  @Autowired
	  public MailSenderService(JavaMailSender javaMailSender) {
	    this.javaMailSender = javaMailSender;
	  }
	  
	  @Async
	  public void sendEmail(SimpleMailMessage email) {
		  javaMailSender.send(email);
	  }
}
