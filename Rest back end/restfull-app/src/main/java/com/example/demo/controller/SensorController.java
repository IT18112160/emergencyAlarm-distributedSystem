package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.SensorService;
import com.example.demo.model.Sensor;

@RestController
@CrossOrigin("http://localhost:3000/")
public class SensorController {
	
	@Autowired
	SensorService service;

	
	@RequestMapping("/registersensor")
	public String AddSensor(@RequestParam String SensorName, @RequestParam String floorNo, @RequestParam String roomNo) {
		
		return service.RegisterSensor(SensorName, floorNo, roomNo);
	
	}
	
	@RequestMapping("/getallsensors")
	public List<Sensor> getAllSensors(){
		
		return service.getAllSensors();
	}
	
	
	
}
