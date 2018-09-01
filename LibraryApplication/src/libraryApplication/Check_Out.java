package libraryApplication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Check_Out extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField cardNO = new JTextField("Card Number");
	JTextField bookID = new JTextField("Book ID");
	JButton submit = new JButton("Check Out");
	
	
	public Check_Out(){
		setSize(300, 50);
		setLayout(new FlowLayout());
		submit.setPreferredSize(new Dimension(100,50));
		cardNO.setPreferredSize(new Dimension(100,50));
		bookID.setPreferredSize(new Dimension(100,50));
		
		
		Container c = new Container();
		this.add(cardNO);
		this.add(bookID);
		this.add(submit);
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		Check_Out init = new Check_Out();
	}

}
