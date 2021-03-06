package uiSnakeGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	Component component = new Component();
	
	public JPanel contentPane;
	//the component of the panel
	JLabel lb_title_game;
	JLabel lb_password;
	JPanel pn_login;
	JLabel lb_username;
	public JButton btn_login;
	public JButton btn_register;
	private JTextField txt_username;
	private JTextField txt_password;
	
	JButton btn_exit;
	
	/*
	 * create methods
	 * Resize(int width, int height) to change size of the panel
	 */
	public void Resize(int width, int height) {
		component.resizeLabel(lb_title_game, 34, 84, 11, 223, 54,width, height);
		component.resizePanel(pn_login, 68, 76, 223, 192, width, height);
		component.resizeLabel(lb_username, 13, 10, 35, 75, 17, width, height);
		component.resizeLabel(lb_password, 13, 10, 84, 75, 17, width, height);
		component.resizeTextbox(txt_username, 14, 82, 30, 131, 28, width, height);
		component.resizeTextbox(txt_password, 14, 82, 79, 131, 28, width, height);
		component.resizeButton(btn_login,18, 10, 133, 91, 39, width, height);
		component.resizeButton(btn_register,18, 111, 133, 102, 39, width, height);
		component.resizeButton(btn_exit,18,  122, 285, 117, 39, width, height);
	}
	
	/**
	 * Create the frame.
	 */
	public Login() {
		//setTitle("Home Screen");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		//setContentPane(contentPane);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		/*
		JLabel lb_title_game = new JLabel("SNAKE GAME");
		lb_title_game.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		lb_title_game.setBounds(68, 11, 223, 54);
		contentPane.add(lb_title_game);
		*/
		lb_title_game = component.createLabelgameTitile();
		contentPane.add(lb_title_game);
		
		pn_login = new JPanel();
		pn_login.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn_login.setBounds(68, 76, 223, 192);
		contentPane.add(pn_login);
		pn_login.setLayout(null);
		
		
		/*
		JButton btn_login = new JButton("Login");
		btn_login.setBackground(new Color(255, 255, 255));
		btn_login.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btn_login.setBounds(10, 133, 91, 39);
		pn_login.add(btn_login);
		*/
		btn_login = component.createButton("Login", 10, 133, 91, 39, 18);
		pn_login.add(btn_login);
		
		/*
		JButton btn_register = new JButton("Register");
		btn_register.setBackground(new Color(255, 255, 255));
		btn_register.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btn_register.setBounds(111, 133, 102, 39);
		*/
		btn_register = component.createButton("Register", 111, 133, 102, 39, 18);
		pn_login.add(btn_register);
		
		/*
		JLabel lb_username = new JLabel("Username:");
		lb_username.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_username.setBounds(10, 35, 75, 17);
		pn_login.add(lb_username);
		*/
		lb_username = component.createInformationlabel("Username: ",10, 35, 75, 17, 13);
		pn_login.add(lb_username);
		
		/*
		JLabel lb_password = new JLabel("Password:");
		lb_password.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_password.setBounds(10, 84, 75, 17);
		pn_login.add(lb_password);
		*/
		lb_password = component.createInformationlabel("Password: ",10, 84, 75, 17, 13);
		pn_login.add(lb_password);
		
		/*
		txt_username = new JTextField();
		txt_username.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_username.setBounds(82, 30, 131, 28);
		pn_login.add(txt_username);
		txt_username.setColumns(10);
		*/
		txt_username = component.createTextbox(82, 30, 131, 28);
		pn_login.add(txt_username);
		
		/*
		txt_password = new JTextField();
		txt_password.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_password.setColumns(10);
		txt_password.setBounds(82, 79, 131, 28);
		pn_login.add(txt_password);
		*/
		txt_password = component.createTextbox(82, 79, 131, 28);
		pn_login.add(txt_password);
		
		/*
		JButton btn_exit = new JButton("EXIT");
		btn_exit.setBackground(Color.RED);
		btn_exit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btn_exit.setBounds(118, 295, 117, 39);
		contentPane.add(btn_exit);
		*/
		btn_exit = component.createButton("EXIT", 122, 285, 117, 39, 18);
		contentPane.add(btn_exit);
		/*
		 	Event of components:
		 */
		/* 
			Event of exit button
			use to exit program
		 */
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		
		/*
			Event of register button
			use to open register form
		*/
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
			}
		});
		
		/*
		 	Event of login button
		 	use to get username and password to check when login
		 */
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
