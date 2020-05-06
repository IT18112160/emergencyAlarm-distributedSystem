package clientApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import rmi_server.AlarmServer;
import rmi_server.SensorService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class LoginRegisterClient extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JLabel headderLable;
	private JCheckBox adminSelect;
	private JButton loginBtn;
	private String asAdmin;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginRegisterClient frame = new LoginRegisterClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	System.currentTimeMillis();
                ClientApp.refreshTable();
            }
        };
        timer.schedule(task, 15000,15000);
        
	}


	public LoginRegisterClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 32);
		contentPane.add(panel);
		
		headderLable = new JLabel("USER LOGIN");
		headderLable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(headderLable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 45, 414, 186);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		username = new JTextField();
		username.setBounds(111, 41, 193, 20);
		panel_1.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(180, 16, 94, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(180, 72, 101, 14);
		panel_1.add(lblNewLabel_2);
		
		loginBtn = new JButton("LOGIN");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				System.setProperty("java.security.policy", "file:allowall.policy");
		        SensorService service = null;
		        if(validateFeilds(username.getText().toString(), password.getText().toString())) {
		        	if (loginBtn.getText().toString().equals("REGISTER")) {
		        		try {
				            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
				            if (adminSelect.isSelected()) {
								asAdmin = "admin";
							}else {
								asAdmin = "user";
							}
				            if (service.addUser(username.getText().toString(), password.getText().toString(), asAdmin).equals("success")) {
				            	JOptionPane.showMessageDialog(null, "New User added successfully....");
				            	setVisible(false);
							} 
				            
				        } catch (Exception ex) {
				            System.err.println(ex.getMessage());
				        }
						
					}else {
						try {
				            service = (SensorService) Naming.lookup("//localhost:"+AlarmServer.RMI_PORT+"/AlarmService");
				            String value = service.loginUser(username.getText().toString(), password.getText().toString());
				            if (value.equals("no_user") || value==null) {
				            	JOptionPane.showMessageDialog(null, "Invalid Login details...");
							}else {
								System.out.println(value);
								String[] valueArray = value.split("/");
								ClientApp app = new ClientApp();
								app.openClientApp(valueArray[0], valueArray[1]);
								setVisible(false);
								
							} 
				        } catch (Exception ex) {
				            System.err.println(ex.getMessage());
				        }
					}
		        
		        
		        }
			}
		});
		loginBtn.setBounds(157, 152, 117, 23);
		panel_1.add(loginBtn);
		
		password = new JPasswordField();
		password.setBounds(111, 96, 193, 20);
		panel_1.add(password);
		
		adminSelect = new JCheckBox("As admin");
		adminSelect.setBounds(177, 123, 97, 23);
		adminSelect.setVisible(false);
		panel_1.add(adminSelect);
		
	}
	
	public void openFrameAsRegister() {
		headderLable.setText("New User Register");
		adminSelect.setVisible(true);
		loginBtn.setText("REGISTER");
		setVisible(true);
	}
	
	private boolean validateFeilds(String un, String pass) {
		if(un.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter username and password","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {			
			return true;
		}

	}
}
