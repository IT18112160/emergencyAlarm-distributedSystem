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
@CrossOrigin(origins = "http://localhost:3000")
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
	
	
	@RequestMapping("/updateco2")
	public void UpdateCO2(@RequestParam String id,@RequestParam int CO2) {
		
	      service.updateSensorCO2(id,CO2);
	}
	
	@RequestMapping("/updatesmoke")
	public void UpdateSmoke(@RequestParam String id,@RequestParam int smoke) {
		
	      service.updateSmoke(id,smoke);
	}
	
	
	
}
