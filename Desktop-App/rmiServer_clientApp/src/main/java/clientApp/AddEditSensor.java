package clientApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rmi_server.AlarmServer;
import rmi_server.SensorService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class AddEditSensor extends JFrame {

	private JPanel contentPane;
	private JTextField sensorName;
	private JTextField floorNo;
	private JTextField roomNo;
	private JButton addBtn;
	

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddSensor frame = new AddSensor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	//}

	/**
	 * Create the frame.
	 */
	public AddEditSensor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 167);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Floor No");
		lblNewLabel_1.setBounds(10, 48, 76, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Sensor Name");
		lblNewLabel.setBounds(10, 11, 89, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Room No");
		lblNewLabel_2.setBounds(10, 84, 76, 14);
		panel.add(lblNewLabel_2);
		
		sensorName = new JTextField();
		sensorName.setBounds(96, 8, 196, 20);
		panel.add(sensorName);
		sensorName.setColumns(10);
		
		floorNo = new JTextField();
		floorNo.setBounds(96, 45, 196, 20);
		panel.add(floorNo);
		floorNo.setColumns(10);
		
		roomNo = new JTextField();
		roomNo.setBounds(96, 81, 196, 20);
		panel.add(roomNo);
		roomNo.setColumns(10);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (addBtn.getText().equals("Add")) {
					System.setProperty("java.security.policy", "file:allowall.policy");
			        SensorService service = null;
			        if(validateFeilds(sensorName.getText(), floorNo.getText(), roomNo.getText())) {
			        try {
			            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
			            if (service.addSensor(sensorName.getText(), floorNo.getText(), roomNo.getText()).equals("success")) {
			            	JOptionPane.showMessageDialog(null, "New Sensor added successfully....");
			            	ClientApp.refreshTable();
			            	setVisible(false);
						} 
			        } catch (Exception ex) {
			            System.err.println(ex.getMessage());
			        }
			        }
				}else {
					System.setProperty("java.security.policy", "file:allowall.policy");
			        SensorService service = null;
			        if(validateFeilds(sensorName.getText(), floorNo.getText(), roomNo.getText())) {
			        try {
			            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
			            if (service.editSensors(sensorName.getText(), floorNo.getText(), roomNo.getText()).equals("success")) {
			            	JOptionPane.showMessageDialog(null, "Sensor details edited successfully....");
			            	ClientApp.refreshTable();
			            	setVisible(false);
						} 
			        } catch (Exception ex) {
			            System.err.println(ex.getMessage());
			        }
			        }
				}
				
				
			}
		});
		addBtn.setBounds(203, 122, 89, 23);
		panel.add(addBtn);
	}
	
	
	
	
	
	public void editSensor(String sn, String fn, String rn){
		sensorName.setText(sn);
		floorNo.setText(fn);
		roomNo.setText(rn);
		addBtn.setText("Edit");
		sensorName.disable();		
	}





	private boolean validateFeilds(String sn, String fn, String rn) {
		if(sn.isEmpty() || fn.isEmpty() || rn.isEmpty()) {
			JOptionPane.showMessageDialog(null, "please fill all feilds...","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			
			return true;
		}
		
		//return true;
	}
}
