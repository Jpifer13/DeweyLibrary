package libraryApplication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mainScreen extends JPanel{
	
	private static JTextField username = new JTextField();
	private static JPasswordField password = new JPasswordField();
	private static JButton submit = new JButton("Login");
	
	JButton loginC = new JButton("Log in Customer");
	JButton loginA = new JButton("Log in Admin");
	

	public mainScreen(){
		setSize(300, 50);
		setLayout(new FlowLayout());
		loginC.setPreferredSize(new Dimension(200,100));
		loginA.setPreferredSize(new Dimension(200,100));
		
		
		loginC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//takes you to log in screen for customer
				logInCust();
			}
		});
		
		loginA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//takes you to log in screen for admin
			}
		});
		
		
		Container c = new Container();
		this.add(loginC);
		this.add(loginA);
		
		//this.add(c);
		setVisible(true);
		
		
	}
	
	private void logInCust() {
		this.remove(loginC);
		this.remove(loginA);
		
		
		username.setPreferredSize(new Dimension(100,50));
		password.setPreferredSize(new Dimension(100,50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		username.setFont(font);
		password.setFont(font);
		
		JLabel name = new JLabel("Enter Name(full)");
		this.add(name);
		
		this.add(username);
		
		JLabel word = new JLabel("Enter card number");
		this.add(word);
		
		this.add(password);
		this.add(submit);
		
		
		this.repaint();
	}
	
	public static void main(String[] args) {
		mainScreen main = new mainScreen();
	
	}

}
