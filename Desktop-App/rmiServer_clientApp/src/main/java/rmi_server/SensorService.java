package rmi_server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface SensorService extends Remote {
	public String addSensor(String sensorName, String floorNo, String roomNo) throws RemoteException;
	public String editSensors(String sensorName, String floorNo, String roomNo) throws RemoteException;
	public DefaultTableModel getAllSensors() throws RemoteException;
	
	public int countClients() throws RemoteException;
	public String addUser(String username, String password, String type)throws RemoteException;
	public String loginUser(String username, String password)throws RemoteException;
	
	public String sentEmail(String sensorName, String floorNo, String roomNo) throws RemoteException;
	
}
