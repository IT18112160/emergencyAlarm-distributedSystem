package rmi_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AlarmServer extends UnicastRemoteObject implements SensorService {
	
	private int clientCount;
	public static final int RMI_PORT = 5097;
	
	protected AlarmServer() throws RemoteException {
		super();
		clientCount = 0;

	}

	public synchronized int countClients() throws RemoteException {
		// TODO Auto-generated method stub
		return ++clientCount;
	}

	public synchronized String addSensor(String sensorName, String floorNo, String roomNo) throws RemoteException {
		RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		JSONObject reqparam= new JSONObject();
		 
		reqparam.put("SensorName",sensorName);
		reqparam.put("floorNo", floorNo);
		reqparam.put("roomNo", roomNo);
		 
		request.header("Content-Type", "application/json"); 
		request.body(reqparam.toString());		 
		Response response= request.post("/registersensor");
		
		if (response.statusCode()==200) {
			return "success";
		}else {
			return null;
		}
		
	}

	public synchronized String editSensors(String sensorName, String floorNo, String roomNo) throws RemoteException {
		RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		JSONObject reqparam= new JSONObject();
		 
		reqparam.put("SensorName",sensorName);
		reqparam.put("floorNo", floorNo);
		reqparam.put("roomNo", roomNo);
		 
		request.header("Content-Type", "application/json"); 
		request.body(reqparam.toString());		 
		Response response= request.post("/updatesensor");
		
		if (response.statusCode()==200) {
			return "success";
		}else {
			return null;
		}
	}

	public synchronized DefaultTableModel getAllSensors() throws RemoteException {
		
		Object data[][] = {};
        String col[] = {"SensorName","Status", "floorNo","roomNo","smoke","co2"};
        DefaultTableModel model = new DefaultTableModel(data,col);
		
        RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		request.header("Content-Type", "application/json"); 
		Response response= request.post("/getallsensors");
		System.out.println(response.statusCode());
		JSONArray array = new JSONArray(response.print());
		System.out.println("Number of sensors: "+array.length());
		
		for(int i=0; i<array.length(); i++) {
			String status;
			JSONObject jsonObject = array.getJSONObject(i);
			String sn = jsonObject.getString("sensorName");
			String fn = jsonObject.getString("floorNo");
			String rn = jsonObject.getString("roomNo");
			int smoke  = jsonObject.getInt("smoke");
			int co2 = jsonObject.getInt("co2");
			
			if (smoke == 0 && co2 == 0) {
				status = "InActive";
			}else {
				status = "Active";
			}
			
			model.insertRow(i,new Object[]{sn,status,fn,rn,co2,smoke});
		}
		return model;
	}
	
	public static void main(String[] args) {
		System.setProperty("java.security.policy", "file:allowall.policy");
        try{
        	AlarmServer svr = new AlarmServer();
            Registry registry = LocateRegistry.createRegistry(RMI_PORT);
            registry.rebind("AlarmService", svr);
            System.out.println ("Alarm Service started....");
        }
        catch(RemoteException re){
            System.err.println(re.getMessage());
        }

	}

	public synchronized String addUser(String username, String password, String type) {
		RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		JSONObject reqparam= new JSONObject();
		 
		reqparam.put("username", username);
		reqparam.put("password", password);
		reqparam.put("type", type);
		 
		request.header("Content-Type", "application/json"); 
		request.body(reqparam.toString());		 
		Response response= request.post("/regiser");
		
		if (response.statusCode()==200) {
			return "success";
		}else {
			return null;
		}
	}

	public synchronized String loginUser(String username, String password) {
		RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		JSONObject reqparam= new JSONObject();
		 
		reqparam.put("username",username);
		reqparam.put("password", password);
		 
		request.header("Content-Type", "application/json"); 
		request.body(reqparam.toString());		 
		Response response= request.post("/login");
		
		if (response.statusCode()==200) {
			if(response.print().isEmpty()) {
				return "no_user";
			}else {
				System.out.println("success");
				JSONObject jsonObject = new JSONObject(response.print());
				String userValues = jsonObject.getString("username")+"/"+jsonObject.getString("type");
				return userValues;
			}
		}else {
			return null;
		}
	}

	public synchronized String sentEmail(String sensorName, String floorNo, String roomNo) throws RemoteException {
		RestAssured.baseURI="http://localhost:8080";
		RequestSpecification request= RestAssured.given();
		JSONObject reqparam= new JSONObject();
		 
		reqparam.put("sensorName",sensorName);
		reqparam.put("floorNo", floorNo);
		reqparam.put("roomNo", roomNo);
		 
		request.header("Content-Type", "application/json"); 
		request.body(reqparam.toString());		 
		Response response= request.post("/sentemail");
		
		if (response.statusCode()==200) {
			return "success";
		}else {
			return null;
		}
	}
	
}
