package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sensor;
import com.example.demo.repo.SensorRepo;

@Service
public class SensorService {
	
	@Autowired
	SensorRepo repo;
	
	public String  RegisterSensor(String SensorName,String floorNo,String roomNo) {
		
		try {
			
			repo.save(new Sensor(SensorName, floorNo, roomNo));
			return "success";
		}catch(Exception e) {
			
			return "error";
			
		}
		
	}
	
	public List<Sensor> getAllSensors(){
		
		return repo.findAll();
	}

}
