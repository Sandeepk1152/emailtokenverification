package com.cdn.sandeep.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cdn.sandeep.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByToken(String token);

}
