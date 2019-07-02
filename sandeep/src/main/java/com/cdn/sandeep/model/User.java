package com.cdn.sandeep.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String token = UUID.randomUUID().toString();

	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;

	@PrePersist
	protected void onCreate() {
		expirationDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(expirationDate); 
		c.add(Calendar.DATE, 1);
		expirationDate = c.getTime();
	}

	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User() {

	}

}
