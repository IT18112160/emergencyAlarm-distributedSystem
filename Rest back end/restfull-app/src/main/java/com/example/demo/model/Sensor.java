package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sensor {
	
	@Id
	private String id;
	private String SensorName;
	private String floorNo;
	private String roomNo;
	private int CO2=0;
	private int smoke=0;
	
public Sensor(String SensorName,String floorNo,String roomNo ) {
	
	this.floorNo=floorNo;
	this.roomNo=roomNo;
	this.SensorName=SensorName;
	
}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getCO2() {
		return CO2;
	}

	public void setCO2(int cO2) {
		CO2 = cO2;
	}

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}


	public String getFloorNo() {
		return floorNo;
	}


	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}


	public String getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public String getSensorName() {
		return SensorName;
	}


	public void setSensorName(String sensorName) {
		SensorName = sensorName;
	}
	
	
	

}
