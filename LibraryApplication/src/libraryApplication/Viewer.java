package libraryApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Viewer extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel c;
	static String screen = "Login";
	static JLabel text = new JLabel();
	private static CardLayout cards;
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	protected static Connection myConn;
	public static JButton adminButton = new JButton("Admin");
	public static JButton search = new JButton("Search");
	public static JButton patron = new JButton("Patron");
	static boolean successfulLogin = false;
	static boolean isAdmin = false;
	static boolean isLoggedIn = false;

	private int height, width = 500;

	public Viewer() throws Exception {
		String sql_username = "username";
		String sql_passwd = "password";

		try {
			myConn = DriverManager.getConnection("url", sql_username,
					sql_passwd);
		} catch (SQLException e) {
			System.out.println("Could not connect to database.");
			e.printStackTrace();
		}

		System.out.println("DB connection successful");

		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Library Lookup");
		setLayout(new BorderLayout());
		c = new JPanel(new CardLayout());
		c.setSize(400, 400);
		buildMenu();

		// mainScreen main = new mainScreen();
		// c.add(main, "main");

		LoginCustomer log = new LoginCustomer();
		c.add(log, "login");

		JPanel controller = new JPanel();
		controller.setSize(500, 100);
		controller.setLayout(new FlowLayout());
		controller.setBackground(Color.BLUE);

		adminButton.setForeground(Color.RED);
		adminButton.setSize(50, 50);
		controller.add(adminButton);
		
		patron.setForeground(Color.RED);
		patron.setSize(50, 50);
		controller.add(patron);

		search.setForeground(Color.GREEN);
		search.setSize(50, 50);
		controller.add(search);

		text.setSize(500, 100);
		text.setFont(new Font("SansSerif", Font.BOLD, 20));
		text.setForeground(Color.BLUE);
		text.setText("Login to begin!");

		add(controller, BorderLayout.PAGE_START);
		add(c, BorderLayout.CENTER);
		add(text, BorderLayout.PAGE_END);
		// pack();
		setVisible(true);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//BookSearch searchBooks;

				try {
					BookSearch searchBooks;
					GuestSearch guestSearch;
					
					guestSearch = new GuestSearch();
					searchBooks = new BookSearch();
					
					c.add(searchBooks, "search");
					c.add(guestSearch, "searchG");

					cards = (CardLayout) (c.getLayout());
					if (successfulLogin == true) {
						cards.show(c, "search");
					}
					
					else {
						cards.show(c, "searchG");
						
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		patron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (successfulLogin == true) {
					
					PatronProfilePanel patronProfile;

					try {
						
						patronProfile = new PatronProfilePanel();

						c.add(patronProfile, "patron");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cards = (CardLayout) (c.getLayout());
					cards.show(c, "patron");

				} else {
					text.setText("You are not logged in. Please log in to continue.");
				}
			}
		});

		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (isAdmin == true) {
					Admin admin = new Admin();
					c.add(admin, "admin");

					cards = (CardLayout) (c.getLayout());
					cards.show(c, "admin");

				}
			}
		});
	}

	/**
	 * builds menu bar to be added to frame
	 */
	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");

		JMenuItem menuItem1 = new JMenuItem("Login");
		JMenuItem menuItem2 = new JMenuItem("Log Out");
		JMenuItem menuItem3 = new JMenuItem("Exit");

		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cards = (CardLayout) (c.getLayout());
				cards.show(c, "login");
			}

		});

		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isLoggedIn = false;
				isAdmin = false;
				LoginCustomer.user = null;
				LoginCustomer.pass = null;
				LoginCustomer.password.setText("");;
				adminButton.setForeground(Color.RED);
				patron.setForeground(Color.RED);
				successfulLogin = false;  //to remove the reserve button from the search panel
				cards.show(c, "login");
				text.setText("Login to begin!");
				
			}

		});

		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);
			}

		});

		fileMenu.add(menuItem1);
		fileMenu.add(menuItem2);
		fileMenu.add(menuItem3);
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

	}

	public static void main(String[] args) {

		try {
			Viewer init = new Viewer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
