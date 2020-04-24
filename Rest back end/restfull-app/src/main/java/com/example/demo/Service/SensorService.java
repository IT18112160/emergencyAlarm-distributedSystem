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
	
	public void updateSensorCO2(String SensorName,int CO2) {
		
		
		
		try {
			
			List<Sensor> list= repo.findAll();
			
			for(Sensor sensor:list) {
				
				if(sensor.getSensorName().equals(SensorName)) {
					
					sensor.setCO2(CO2);
					
					repo.save(sensor);
					System.out.println("Fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuucccccccccccckkkkkkk");
			
				}
		}
		}catch(Exception e)
		{
			
			
		}
	}
	
	
	public void updateSmoke(String SensorName,int smoke) {
		
		try {
			
     
			List<Sensor> list= repo.findAll();
			
			for(Sensor sensor:list) {
				
				if(sensor.getSensorName().equals(SensorName)) {
					
					sensor.setSmoke(smoke);
					
					repo.save(sensor);
					
				}
			}
			
			
			
		}catch(Exception e)
		{
			
			
		}
	}

}
