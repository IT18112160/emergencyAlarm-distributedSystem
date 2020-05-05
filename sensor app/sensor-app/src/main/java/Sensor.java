import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Sensor {
	
	
	public static void main(String[] args)  throws Exception{
		

		String[] sensors = {"newSen","newfirealarm","new"};
		Random random = new Random();
		
		int i=0;
		while(true) {
			
			
			
			if(i>9) {
				i=0;
			}
			
			
		 RestAssured.baseURI="http://localhost:8080";
		 RequestSpecification request= RestAssured.given();
		 JSONObject reqparam= new JSONObject();
		 
		 reqparam.put("SensorName",sensors[random.nextInt(3)]);
		 reqparam.put("smoke", i);
		 
		 request.header("Content-Type", "application/json");
		 
		 request.body(reqparam.toString());
		 
		 Response response= request.post("/updatesmoke");
		 
		 System.out.println(response.statusCode());
		 
		 RestAssured.baseURI="http://localhost:8080";
		 RequestSpecification request2= RestAssured.given();
		 JSONObject reqparam2= new JSONObject();
		 
		 reqparam2.put("SensorName",sensors[random.nextInt(3)]);
		 
		 if(i<5) {
		 
		 reqparam2.put("co2", i+4);
		 }else {
			 reqparam2.put("co2", i);
		 }
		 
		 request2.header("Content-Type", "application/json");
		 
		 request2.body(reqparam2.toString());
		 
		 Response response2= request2.post("/updateco2");
		 
		 
		 System.out.println(response2.statusCode());
		 
			
			Thread.sleep(25000);
			
			i=i+1;
			
		}
		
		
		
	}

}
