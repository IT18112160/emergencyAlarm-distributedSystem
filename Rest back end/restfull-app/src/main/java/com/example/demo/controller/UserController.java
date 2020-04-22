package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/regiser")
	public String registerUser(@RequestParam String username,@RequestParam String password, @RequestParam String type) {
		
		return service.RegisterUser(username, password, type);
	}
	
	@RequestMapping("/login")
	public String Login(@RequestParam String username, @RequestParam String password) {
		
		return service.Login(username, password);
	}
	

}
