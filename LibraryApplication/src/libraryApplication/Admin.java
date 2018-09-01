package libraryApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Admin extends JPanel implements ActionListener {

	private static CardLayout cards;
	JButton editUser = new JButton("Edit User");
	JButton addUser = new JButton("Add User");
	JButton addBook = new JButton("Add Book");
	JButton editBook = new JButton("Edit Book");
	JButton payDue = new JButton("Pay Due");
	JButton mostCheckedOut = new JButton("Top Ten Books");
	JPanel panel = this;
	Container cont = new Container();
	protected static Connection myConn = Viewer.myConn;
	private static Statement myStmt = null;
	private static ResultSet myRs = null;
	private static String title;
	private static String publisher;
	private static int initBID;
	private static int initCard;
	private static int bookID;
	private static String author;
	private static int branch;
	private static int copies;
	private static boolean edit = false;
	private static int cardNO;
	private static String name;
	private static String address;
	private static String phone;
	private static double amountDue;

	int buttonHeight = 75;
	int buttonWidth = 200;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {

		cont.setLayout(new FlowLayout());
		cont.setSize(new Dimension(1000, 750));

		createOptions();
		setPreferredSize(new Dimension(1200, 800));

		editBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container edBook = new Container();
				SpringLayout layout = new SpringLayout();
				edBook.setPreferredSize(new Dimension(900, 750));
				edBook.setLayout(layout);
				JButton search = new JButton("Search");
				JButton clear = new JButton("Clear");
				clear.setSize(50, 100);
				search.setSize(50, 100);
				JLabel label = new JLabel();
				label.setFont(new Font("SansSerif", Font.BOLD, 20));
				label.setForeground(Color.BLACK);
				label.setText("Enter bookId you want to edit:");

				JLabel bID = new JLabel("Book ID: ");
				JTextField bIDText = new JTextField(20);
				JLabel tite = new JLabel("Title: ");
				JTextField titeText = new JTextField(20);
				JLabel pub = new JLabel("Publisher: ");
				JTextField pubText = new JTextField(20);

				edBook.add(bID);
				edBook.add(bIDText);
				edBook.add(tite);
				edBook.add(titeText);
				edBook.add(pub);
				edBook.add(pubText);
				edBook.add(search);
				edBook.add(clear);

				layout.putConstraint(SpringLayout.WEST, bID, 100, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, bID, 50, SpringLayout.NORTH, edBook);
				layout.putConstraint(SpringLayout.WEST, bIDText, 400, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, bIDText, 55, SpringLayout.NORTH, edBook);

				layout.putConstraint(SpringLayout.WEST, tite, 100, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, tite, 100, SpringLayout.NORTH, edBook);
				layout.putConstraint(SpringLayout.WEST, titeText, 400, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, titeText, 105, SpringLayout.NORTH, edBook);

				layout.putConstraint(SpringLayout.WEST, pub, 100, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, pub, 150, SpringLayout.NORTH, edBook);
				layout.putConstraint(SpringLayout.WEST, pubText, 400, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, pubText, 155, SpringLayout.NORTH, edBook);

				layout.putConstraint(SpringLayout.WEST, search, 100, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, search, 200, SpringLayout.NORTH, edBook);
				layout.putConstraint(SpringLayout.WEST, clear, 200, SpringLayout.WEST, edBook);
				layout.putConstraint(SpringLayout.NORTH, clear, 200, SpringLayout.NORTH, edBook);

				search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (edit == false) {
							edit = true;
							Viewer.text.setText("Search by book ID leave other fields empty.");
							bookID = Integer.parseInt(bIDText.getText());
							initBID = Integer.parseInt(bIDText.getText());
							try {
								// myStmt = Viewer.myConn.createStatement();
								PreparedStatement stmt = myConn
										.prepareStatement("select * from books where book_id = ?");
								stmt.setInt(1, bookID);
								myRs = stmt.executeQuery();
								while (myRs.next()) {

									title = myRs.getString(2);
									publisher = myRs.getString(3);

								}

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}

							bIDText.setText(Integer.toString(bookID));
							titeText.setText(title);
							pubText.setText(publisher);
							search.setText("Edit");
						}

						else {
							edit = false;
							bookID = Integer.parseInt(bIDText.getText());
							title = titeText.getText();
							publisher = pubText.getText();

							try {
								// myStmt = Viewer.myConn.createStatement();
								PreparedStatement stmt = myConn.prepareStatement("call updateBookAllData(?, ?, ?, ?)");
								stmt.setInt(1, initBID);
								stmt.setInt(2, bookID);
								stmt.setString(3, title);
								stmt.setString(4, publisher);
								myRs = stmt.executeQuery();
								Viewer.text.setText("Successfully update book with initial id of: " + initBID);

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}

						}
					}
				});

				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						bIDText.setText("");
						titeText.setText("");
						pubText.setText("");
						search.setText("Search");
					}
				});

				panel.add(edBook, "edBook");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "edBook");

			}
		});
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container adBook = new Container();
				SpringLayout layout = new SpringLayout();
				adBook.setPreferredSize(new Dimension(900, 750));
				adBook.setLayout(layout);
				JButton search = new JButton("Add Book");
				search.setSize(50, 100);
				JButton clear = new JButton("Clear");
				clear.setSize(50, 100);

				JLabel bID = new JLabel("Book ID: ");
				JTextField bIDText = new JTextField(20);
				JLabel tite = new JLabel("Title: ");
				JTextField titeText = new JTextField(20);
				JLabel pub = new JLabel("Publisher: ");
				JTextField pubText = new JTextField(20);
				JLabel athor = new JLabel("Author: ");
				JTextField athorText = new JTextField(20);
				JLabel bran = new JLabel("Branch: ");
				JTextField branchText = new JTextField(20);
				JLabel cop = new JLabel("Number of Copies: ");
				JTextField copiesText = new JTextField(20);

				adBook.add(bID);
				adBook.add(bIDText);
				adBook.add(tite);
				adBook.add(titeText);
				adBook.add(pub);
				adBook.add(pubText);
				adBook.add(athor);
				adBook.add(athorText);
				adBook.add(bran);
				adBook.add(branchText);
				adBook.add(cop);
				adBook.add(copiesText);
				adBook.add(search);
				adBook.add(clear);

				layout.putConstraint(SpringLayout.WEST, bID, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, bID, 50, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, bIDText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, bIDText, 55, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, tite, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, tite, 100, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, titeText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, titeText, 105, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, pub, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, pub, 150, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, pubText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, pubText, 155, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, athor, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, athor, 200, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, athorText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, athorText, 205, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, bran, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, bran, 250, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, branchText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, branchText, 255, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, cop, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, cop, 300, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, copiesText, 400, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, copiesText, 305, SpringLayout.NORTH, adBook);

				layout.putConstraint(SpringLayout.WEST, search, 100, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, search, 350, SpringLayout.NORTH, adBook);
				layout.putConstraint(SpringLayout.WEST, clear, 200, SpringLayout.WEST, adBook);
				layout.putConstraint(SpringLayout.NORTH, clear, 200, SpringLayout.NORTH, adBook);

				search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						bookID = Integer.parseInt(bIDText.getText());
						title = titeText.getText();
						publisher = pubText.getText();
						author = athorText.getText();
						branch = Integer.parseInt(branchText.getText());
						copies = Integer.parseInt(copiesText.getText());

						try {
							// myStmt = Viewer.myConn.createStatement();
							PreparedStatement stmt = myConn.prepareStatement("call addBook(?, ?, ?, ?, ?, ?)");
							stmt.setInt(1, bookID);
							stmt.setString(2, title);
							stmt.setString(3, publisher);
							stmt.setString(4, author);
							stmt.setInt(5, branch);
							stmt.setInt(6, copies);
							myRs = stmt.executeQuery();
							Viewer.text.setText("Successfully added book with id of: " + bookID);

						} catch (SQLException e) {
							System.out.println("Error connecting to database.");
							e.printStackTrace();
						}

					}
				});

				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						bIDText.setText("");
						titeText.setText("");
						pubText.setText("");
						athorText.setText("");
						branchText.setText("");
						copiesText.setText("");
					}
				});

				panel.add(adBook, "adBook");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "adBook");

			}
		});

		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container edUser = new Container();

				SpringLayout layout = new SpringLayout();
				edUser.setPreferredSize(new Dimension(900, 750));
				edUser.setLayout(layout);
				JButton search = new JButton("Search User");
				search.setSize(50, 100);
				JButton clear = new JButton("Clear");
				clear.setSize(50, 100);

				JLabel cID = new JLabel("Card Number: ");
				JTextField cIDText = new JTextField(20);
				JLabel nam = new JLabel("Name: ");
				JTextField nameText = new JTextField(20);
				JLabel addr = new JLabel("Address: ");
				JTextField addressText = new JTextField(20);
				JLabel phon = new JLabel("Phone: ");
				JTextField phoneText = new JTextField(20);

				edUser.add(cID);
				edUser.add(cIDText);
				edUser.add(nam);
				edUser.add(nameText);
				edUser.add(addr);
				edUser.add(addressText);
				edUser.add(phon);
				edUser.add(phoneText);
				edUser.add(search);
				edUser.add(clear);

				layout.putConstraint(SpringLayout.WEST, cID, 100, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, cID, 50, SpringLayout.NORTH, edUser);
				layout.putConstraint(SpringLayout.WEST, cIDText, 400, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, cIDText, 55, SpringLayout.NORTH, edUser);

				layout.putConstraint(SpringLayout.WEST, nam, 100, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, nam, 100, SpringLayout.NORTH, edUser);
				layout.putConstraint(SpringLayout.WEST, nameText, 400, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, nameText, 105, SpringLayout.NORTH, edUser);

				layout.putConstraint(SpringLayout.WEST, addr, 100, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, addr, 150, SpringLayout.NORTH, edUser);
				layout.putConstraint(SpringLayout.WEST, addressText, 400, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, addressText, 155, SpringLayout.NORTH, edUser);

				layout.putConstraint(SpringLayout.WEST, phon, 100, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, phon, 200, SpringLayout.NORTH, edUser);
				layout.putConstraint(SpringLayout.WEST, phoneText, 400, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, phoneText, 205, SpringLayout.NORTH, edUser);

				layout.putConstraint(SpringLayout.WEST, search, 100, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, search, 350, SpringLayout.NORTH, edUser);
				layout.putConstraint(SpringLayout.WEST, clear, 210, SpringLayout.WEST, edUser);
				layout.putConstraint(SpringLayout.NORTH, clear, 350, SpringLayout.NORTH, edUser);

				search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						initCard = Integer.parseInt(cIDText.getText());
						cardNO = Integer.parseInt(cIDText.getText());

						if (edit == false) {
							edit = true;
							Viewer.text.setText("Search by card number leave other fields empty.");

							try {
								// myStmt = Viewer.myConn.createStatement();
								PreparedStatement stmt = myConn
										.prepareStatement("select * from borrowers where card_no = ?");
								stmt.setInt(1, cardNO);
								myRs = stmt.executeQuery();
								while (myRs.next()) {

									name = myRs.getString(2);
									address = myRs.getString(3);
									phone = myRs.getString(4);

								}

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}

							cIDText.setText(Integer.toString(cardNO));
							nameText.setText(name);
							addressText.setText(address);
							phoneText.setText(phone);
							search.setText("Edit");
						}

						else {
							edit = false;
							cardNO = Integer.parseInt(cIDText.getText());
							name = nameText.getText();
							address = addressText.getText();
							phone = phoneText.getText();

							try {
								// myStmt = Viewer.myConn.createStatement();
								PreparedStatement stmt = myConn.prepareStatement("call updateUser(?, ?, ?, ?, ?)");
								stmt.setInt(1, initCard);
								stmt.setInt(2, cardNO);
								stmt.setString(3, name);
								stmt.setString(4, address);
								stmt.setString(5, phone);
								myRs = stmt.executeQuery();
								Viewer.text
										.setText("Successfully update user with initial card number of: " + initCard);

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}
						}

					}
				});

				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cIDText.setText("");
						nameText.setText("");
						addressText.setText("");
						phoneText.setText("");
						search.setText("Search");
					}
				});

				panel.add(edUser, "edUser");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "edUser");
			}
		});

		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container adUser = new Container();
				SpringLayout layout = new SpringLayout();
				adUser.setPreferredSize(new Dimension(900, 750));
				adUser.setLayout(layout);
				JButton search = new JButton("Add User");
				search.setSize(50, 100);
				JButton clear = new JButton("Clear");
				clear.setSize(50, 100);
				JRadioButton adm = new JRadioButton("Admin");

				JLabel cID = new JLabel("Card Number: ");
				JTextField cIDText = new JTextField(20);
				JLabel nam = new JLabel("Name: ");
				JTextField nameText = new JTextField(20);
				JLabel addr = new JLabel("Address: ");
				JTextField addressText = new JTextField(20);
				JLabel phon = new JLabel("Phone: ");
				JTextField phoneText = new JTextField(20);

				adUser.add(cID);
				adUser.add(cIDText);
				adUser.add(nam);
				adUser.add(nameText);
				adUser.add(addr);
				adUser.add(addressText);
				adUser.add(phon);
				adUser.add(phoneText);
				adUser.add(search);
				adUser.add(clear);
				adUser.add(adm);

				layout.putConstraint(SpringLayout.WEST, cID, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, cID, 50, SpringLayout.NORTH, adUser);
				layout.putConstraint(SpringLayout.WEST, cIDText, 400, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, cIDText, 55, SpringLayout.NORTH, adUser);

				layout.putConstraint(SpringLayout.WEST, nam, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, nam, 100, SpringLayout.NORTH, adUser);
				layout.putConstraint(SpringLayout.WEST, nameText, 400, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, nameText, 105, SpringLayout.NORTH, adUser);

				layout.putConstraint(SpringLayout.WEST, addr, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, addr, 150, SpringLayout.NORTH, adUser);
				layout.putConstraint(SpringLayout.WEST, addressText, 400, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, addressText, 155, SpringLayout.NORTH, adUser);

				layout.putConstraint(SpringLayout.WEST, phon, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, phon, 200, SpringLayout.NORTH, adUser);
				layout.putConstraint(SpringLayout.WEST, phoneText, 400, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, phoneText, 205, SpringLayout.NORTH, adUser);

				layout.putConstraint(SpringLayout.WEST, adm, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, adm, 250, SpringLayout.WEST, adUser);

				layout.putConstraint(SpringLayout.WEST, search, 100, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, search, 350, SpringLayout.NORTH, adUser);
				layout.putConstraint(SpringLayout.WEST, clear, 210, SpringLayout.WEST, adUser);
				layout.putConstraint(SpringLayout.NORTH, clear, 350, SpringLayout.NORTH, adUser);

				search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						cardNO = Integer.parseInt(cIDText.getText());
						name = nameText.getText();
						address = addressText.getText();
						phone = phoneText.getText();

						try {
							// myStmt = Viewer.myConn.createStatement();
							PreparedStatement stmt = myConn.prepareStatement("call addUser(?, ?, ?, ?)");
							stmt.setInt(1, cardNO);
							stmt.setString(2, name);
							stmt.setString(3, address);
							stmt.setString(4, phone);
							myRs = stmt.executeQuery();
							Viewer.text.setText("Successfully added user with card number of: " + cardNO
									+ "click clear to add another user");

							if (adm.isSelected()) {
								PreparedStatement stm = myConn.prepareStatement("call addAdmin(?, ?)");
								stm.setInt(1, cardNO);
								stm.setString(2, name);
								myRs = stm.executeQuery();
							}

						} catch (SQLException e) {
							System.out.println("Error connecting to database.");
							e.printStackTrace();
						}

					}
				});

				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cIDText.setText("");
						nameText.setText("");
						addressText.setText("");
						phoneText.setText("");
					}
				});

				panel.add(adUser, "adUser");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "adUser");

			}
		});

		payDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container payD = new Container();
				SpringLayout layout = new SpringLayout();
				payD.setPreferredSize(new Dimension(900, 750));
				payD.setLayout(layout);
				JButton search = new JButton("Search");
				JButton clear = new JButton("Clear");
				clear.setSize(50, 100);
				search.setSize(50, 100);
				JLabel label = new JLabel();
				label.setFont(new Font("SansSerif", Font.BOLD, 20));
				label.setForeground(Color.BLACK);
				label.setText("Enter bookId you want to edit:");

				JLabel dues = new JLabel();
				dues.setFont(new Font("SansSerif", Font.BOLD, 20));
				dues.setForeground(Color.BLACK);
				JLabel cID = new JLabel("Card Number: ");
				JTextField cIDText = new JTextField(20);
				JLabel due = new JLabel("Enter amount: ");
				JTextField dueText = new JTextField(20);
				JRadioButton add = new JRadioButton("Add");
				JRadioButton subtract = new JRadioButton("Subtract");

				payD.add(cID);
				payD.add(cIDText);
				payD.add(due);
				payD.add(dueText);
				payD.add(dues);
				payD.add(add);
				payD.add(subtract);
				payD.add(search);
				payD.add(clear);

				layout.putConstraint(SpringLayout.WEST, cID, 100, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, cID, 50, SpringLayout.NORTH, payD);
				layout.putConstraint(SpringLayout.WEST, cIDText, 400, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, cIDText, 55, SpringLayout.NORTH, payD);

				layout.putConstraint(SpringLayout.WEST, due, 100, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, due, 100, SpringLayout.NORTH, payD);
				layout.putConstraint(SpringLayout.WEST, dueText, 400, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, dueText, 105, SpringLayout.NORTH, payD);

				layout.putConstraint(SpringLayout.WEST, dues, 100, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, dues, 150, SpringLayout.NORTH, payD);

				layout.putConstraint(SpringLayout.WEST, add, 100, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, add, 200, SpringLayout.WEST, payD);

				layout.putConstraint(SpringLayout.WEST, subtract, 150, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, subtract, 200, SpringLayout.WEST, payD);

				layout.putConstraint(SpringLayout.WEST, search, 100, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, search, 250, SpringLayout.NORTH, payD);
				layout.putConstraint(SpringLayout.WEST, clear, 200, SpringLayout.WEST, payD);
				layout.putConstraint(SpringLayout.NORTH, clear, 250, SpringLayout.NORTH, payD);

				search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						cardNO = Integer.parseInt(cIDText.getText());

						if (edit == false) {
							edit = true;
							Viewer.text.setText("Search by card number leave other fields empty.");

							try {
								// myStmt = Viewer.myConn.createStatement();
								PreparedStatement stmt = myConn
										.prepareStatement("select unpaid_dues from borrowers where card_no = ?");
								stmt.setInt(1, cardNO);
								myRs = stmt.executeQuery();
								while (myRs.next()) {

									amountDue = myRs.getDouble(1);

								}

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}

							cIDText.setText(Integer.toString(cardNO));
							dues.setText(Double.toString(amountDue));
							search.setText("Update");
						}

						else {
							edit = false;
							cardNO = Integer.parseInt(cIDText.getText());

							try {
								// myStmt = Viewer.myConn.createStatement();
								if (add.isSelected()) {
									amountDue = amountDue + Double.parseDouble(dueText.getText());
									PreparedStatement stmt = myConn.prepareStatement("call updateDues(?, ?)");
									stmt.setInt(1, cardNO);
									stmt.setDouble(2, amountDue);
									myRs = stmt.executeQuery();
									Viewer.text.setText(
											"Successfully added " + amountDue + " to user with card number: " + cardNO);
								}
								if (subtract.isSelected()) {
									amountDue = amountDue - Double.parseDouble(dueText.getText());
									PreparedStatement stmt = myConn.prepareStatement("call updateDues(?, ?)");
									stmt.setInt(1, cardNO);
									stmt.setDouble(2, amountDue);
									myRs = stmt.executeQuery();
									Viewer.text.setText("Successfully subtracted " + Double.parseDouble(dueText.getText())
											+ " from user with card number: " + cardNO);
								}

							} catch (SQLException e) {
								System.out.println("Error connecting to database.");
								e.printStackTrace();
							}
						}

					}
				});

				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cIDText.setText("");
						dueText.setText("");
						dues.setText("");
						search.setText("Search");
					}
				});
				
				

				panel.add(payD, "payD");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "payD");

			}
		});
		
		mostCheckedOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Container mostCheck = new Container();
				
				JPanel panel1 = new JPanel();
				ArrayList<String> topBooks = new ArrayList<String>();
				mostCheck.setPreferredSize(new Dimension(900, 750));
				mostCheck.setLayout(new BorderLayout());

				
				panel1.setSize(900, 800);
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				try {
					PreparedStatement stmt = myConn.prepareStatement("select * from (select book_id, title,  count(*) as c from (book_loans natural join books) group by book_id)as b order by c desc;");
					
					myRs = stmt.executeQuery();
					for(int i = 0; i < 10;i++) {
						myRs.next();
						int tempBID = myRs.getInt(1);
						String tempTitle = myRs.getString(2);
						int tempTimes = myRs.getInt(3);
						String bookIDTimes = ("Book id: " + Integer.toString(tempBID) + " Title: '" + tempTitle + "' amount of times checked out: " + Integer.toString(tempTimes));
						topBooks.add(bookIDTimes);
						
					}
					

				} catch (SQLException e) {
					System.out.println("Error connecting to database.");
					e.printStackTrace();
				}
				
				for(int j = 0; j < topBooks.size();j++) {
					JTextField temp = new JTextField();
					temp.setFont(new Font("SansSerif", Font.BOLD, 20));
					temp.setForeground(Color.BLACK);
					temp.setPreferredSize(new Dimension(100, 50));
					temp.setAlignmentX(Component.CENTER_ALIGNMENT);
					temp.setText(topBooks.get(j));
					panel1.add(temp);
				}

				mostCheck.add(panel1, BorderLayout.CENTER);
				panel.add(mostCheck, "mostCheck");

				cards = (CardLayout) (panel.getLayout());
				cards.show(panel, "mostCheck");

			}
		});

		setLayout(new CardLayout());
		add(cont, "main");
		setVisible(true);

	}

	private void createOptions() {

		editBook.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		addBook.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		editUser.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		addUser.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		payDue.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		mostCheckedOut.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

		cont.add(editBook);
		cont.add(addBook);
		cont.add(addUser);
		cont.add(editUser);
		cont.add(payDue);
		cont.add(mostCheckedOut);

	}

	public static void main(String[] args) {

		Admin init = new Admin();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
