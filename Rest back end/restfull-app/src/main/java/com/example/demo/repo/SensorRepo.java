package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Sensor;

public interface SensorRepo extends MongoRepository<Sensor, String> {
	
	public Sensor findByid(String id);
	public Sensor findBySensorName(String SensorName);
	

}
