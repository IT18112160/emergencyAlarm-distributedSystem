package clientApp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import rmi_server.AlarmServer;
import rmi_server.SensorService;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import com.sun.tools.xjc.reader.RawTypeSet.Mode;

public class ClientApp extends Thread {

	private JFrame frame;
	private static JTable table;
	private JLabel userdata;
	private String un;
	private JButton btnNewButton;
	private JButton addUserBtn;
	private static JPanel panel;
	private String userType;
	private static JButton reloadBtn;
	
	
	public ClientApp() throws InterruptedException {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Smart fire alarm system");
		table = new JTable();
		frame.setBounds(100, 100, 582, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 106, 540, 174);
		frame.getContentPane().add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 82, 540, 21);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sensor name");
		lblNewLabel.setBounds(10, 5, 81, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Floor No");
		lblNewLabel_1.setBounds(197, 5, 59, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Room No");
		lblNewLabel_3.setBounds(266, 5, 67, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CO2");
		lblNewLabel_4.setBounds(374, 5, 30, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("SMOKE");
		lblNewLabel_2.setBounds(463, 5, 67, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setBounds(136, 5, 51, 14);
		panel_2.add(lblNewLabel_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 28, 540, 43);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		userdata = new JLabel("New label");
		userdata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userdata.setBounds(10, 11, 230, 14);
		panel_3.add(userdata);
		
		btnNewButton = new JButton("Add Sensor");
		btnNewButton.setBounds(325, 9, 106, 23);
		panel_3.add(btnNewButton);
		
		addUserBtn = new JButton("Add User");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginRegisterClient login = new LoginRegisterClient();
				login.openFrameAsRegister();
				login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		addUserBtn.setBounds(221, 9, 94, 23);
		panel_3.add(addUserBtn);
		
		JButton logoutbtn = new JButton("LOGOUT");
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				LoginRegisterClient client = new LoginRegisterClient();
				client.setVisible(true);
			}
		});
		logoutbtn.setBounds(441, 9, 89, 23);
		panel_3.add(logoutbtn);
		
		reloadBtn = new JButton("");
		reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("refresh..........");
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setRowCount(0);
				loadDatatoTable();
				
			}
		});
		reloadBtn.setBounds(538, 334, 12, 6);
		frame.getContentPane().add(reloadBtn);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEditSensor addSensor = new AddEditSensor();
				addSensor.setVisible(true);
				addSensor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		
		loadDatatoTable();
		
		table.setShowVerticalLines(false);
		table.setRowHeight(30);
		table.setRowMargin(5);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Update sensor details") == 0) {
					int row = table.getSelectedRow();
					System.out.println(table.getValueAt(row, 0));
					AddEditSensor addSensor = new AddEditSensor();
					addSensor.editSensor(table.getValueAt(row,0).toString(), table.getValueAt(row,2).toString(), table.getValueAt(row,3).toString());
					addSensor.setVisible(true);
					addSensor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				
				
				
			}
		});
		table.setBackground(Color.WHITE);
		
		
	}
	
	public static void loadDatatoTable() {
		
		SwingUtilities.updateComponentTreeUI(table);
		System.setProperty("java.security.policy", "file:allowall.policy");
        SensorService service = null;
        try {
            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
            
            table.setModel(service.getAllSensors());
             
        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
        ProgressRenderer pcr = new ProgressRenderer(0,10);
		TableColumnModel tcm = table.getColumnModel ();
		tcm.getColumn(5).setCellRenderer (pcr);
		tcm.getColumn(4).setCellRenderer (pcr);
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		panel.add(table);
	}
	
	public void openClientApp(String username, String userType) throws InterruptedException {
		userdata.setText("Username: "+username+ " (Admin)");
		frame.setVisible(true);
		System.out.println(username +" "+ userType);
		
		if (userType.equals("user")) {
			userdata.setText("Username: "+username);
			btnNewButton.setVisible(false);
			addUserBtn.setVisible(false);
			table.setEnabled(false);
		}
		
	}
	
	public static void refreshTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		loadDatatoTable();
		System.out.println("refresh method...");
		SwingUtilities.updateComponentTreeUI(table);
	}
	
	public static void sentEmail() {
		System.setProperty("java.security.policy", "file:allowall.policy");
        SensorService service = null;
        try {
            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
            if (service.sentEmail("s", "s", "s").equals("success")) {
            	System.out.println("Warning Email has been sent..........");
            	
			} 
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
	}
}


class ProgressRenderer extends JProgressBar implements TableCellRenderer {
	
	  public ProgressRenderer(int min, int max) {
	    super(min, max);
	    this.setStringPainted(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
		  this.setValue((Integer) value);
	    
	    if (((Integer) value).intValue() > 5) {
	    	this.setForeground(Color.RED);
	    	//JOptionPane.showMessageDialog(null, "CO2 or smoke level is high", "System Alert ", JOptionPane.WARNING_MESSAGE);
	    	ClientApp.sentEmail();
	    	
		}else {
			this.setForeground(Color.GRAY);
		}
	    return this;
	  }
}


