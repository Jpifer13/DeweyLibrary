package libraryApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginCustomer extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField username = new JTextField();
	protected static JPasswordField password = new JPasswordField();
	JButton submit = new JButton("Login");
	static String user;
	static String pass;
	boolean valid = false;
	
	
	public LoginCustomer(){
		setSize(500, 500);
		FlowLayout flowLayout = (FlowLayout) this.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		

		username.setPreferredSize(new Dimension(100,50));
		password.setPreferredSize(new Dimension(100,50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		username.setFont(font);
		password.setFont(font);

		
		
		
		//MouseListener mL = new EventHandle();
		//KeyListener kL = new EventHandle();

		JLabel name = new JLabel("Enter Name(full)");
		this.add(name);
		
		add(username);
		
		JLabel word = new JLabel("Enter card number");
		this.add(word);
		
		add(password);
		add(submit);
		
		
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				user = username.getText();
				pass =new String(password.getPassword());
				
				//System.out.println(user);
				//System.out.println(pass);
				
				try {
					valid = Operations.checkValidLogin(Integer.parseInt(pass));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(valid == true){
					Viewer.isLoggedIn = true;
					boolean admin = Operations.checkAdmin(Integer.parseInt(pass));
					Viewer.patron.setForeground(Color.GREEN);
					if(admin == true) {
						Viewer.adminButton.setForeground(Color.GREEN);
					}
					Viewer.isAdmin = admin;
					System.out.println("Successfully logged in.");
					Viewer.screen = "main";
					Viewer.successfulLogin = true;
					Viewer.text.setText("Successfully logged in as " + user + ". Select search to look up books or check in/out to check in or out a book/s.");
					//mainScreen main = new mainScreen();
					//Viewer.c.add(main, "main");
					//Viewer.changeScreen();
				}
				else{
					Viewer.text.setText("Your input was incorrect");
				}
			}
		});
		
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
