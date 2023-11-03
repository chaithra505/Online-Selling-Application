package com.sonata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.model.User;
import com.sonata.repo.UserRepo;

@Service
public class UserService {
	
	 @Autowired
	    private UserRepo userRepo;
	 
	 public List<User> getAllUsers(){
		 return userRepo.findAll();		 
	 }
	 

}
