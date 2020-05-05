package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.EmailSentRequest;
import com.example.demo.model.Sensor;

@RestController
public class EmailController {

	@RequestMapping(path = "/sentemail",method = RequestMethod.POST,consumes = "application/json")
	public String sendNotification(@RequestBody EmailSentRequest request) {
		
		System.out.println("Email Sent.............");
		System.out.println("Sensorname: "+request.sensorName+" FloorNo: "+request.floorNo+" RoomNo"+request.roomNo);
		
		
		return "success";
	}
	
	
}
