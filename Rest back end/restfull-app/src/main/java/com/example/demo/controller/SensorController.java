package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.SensorService;
import com.example.demo.data.RegisterSensorRequest;
import com.example.demo.data.UpdateRequest;
import com.example.demo.data.updateCO2Request;
import com.example.demo.model.Sensor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SensorController {
	
	@Autowired
	SensorService service;

	
	@RequestMapping(path = "/registersensor",method = RequestMethod.POST)
	public String AddSensor(@RequestBody RegisterSensorRequest request) {
		
		return service.RegisterSensor(request.SensorName, request.floorNo, request.roomNo);
	
	}
	
	@RequestMapping("/getallsensors")
	public List<Sensor> getAllSensors(){
		
		return service.getAllSensors();
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path="/updateco2")
	public void UpdateCO2(@RequestBody updateCO2Request request) {
		
	      service.updateSensorCO2(request.SensorName,request.co2);
	}
	
	@RequestMapping(path="/updatesmoke",method=RequestMethod.POST)
	public void UpdateSmoke(@RequestBody UpdateRequest request) {
		
	      service.updateSmoke(request.SensorName,request.smoke);
	}
	
	
	
}
