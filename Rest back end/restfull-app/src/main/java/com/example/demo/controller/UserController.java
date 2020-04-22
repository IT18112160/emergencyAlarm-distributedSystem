package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/regiser")
	public String registerUser(@RequestParam String username,@RequestParam String password, @RequestParam String type) {
		
		return service.RegisterUser(username, password, type);
	}
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public User Login(@RequestBody User user) {
		
		return service.Login(user.getUsername(), user.getPassword());
	}
	

}
