package libraryApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class PatronProfilePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable currentLoans, pastDue;


	private List<PatronBooks> searchResults, searchResultsLate;
	private SpringLayout layout;
	
	
	private JScrollPane scrollPane, scrollPaneLate;
	
	public PatronProfilePanel() throws NumberFormatException, SQLException {
		
		
		List<Borrower> patron = Operations.searchforBorrower(Integer.parseInt(LoginCustomer.pass));
		
				
		currentLoans = new JTable();
		pastDue = new JTable();
		layout = new SpringLayout();
		setLayout(layout);
		
		JLabel patronName = new JLabel ("Patron Name: " + patron.get(0).getName());
		layout.putConstraint(SpringLayout.WEST, patronName, 350, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, patronName, 10, SpringLayout.NORTH, this);
		add(patronName);
		
		
		patronName.setFont(new Font("Dialog", Font.BOLD, 25));
		
		
		
		JLabel dues = new JLabel ("Unpaid Dues: $" + patron.get(0).getDues());
		layout.putConstraint(SpringLayout.WEST, dues, 350, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dues, 10, SpringLayout.SOUTH, patronName);
		add(dues);
		
		dues.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel current = new JLabel ("Current Book Loans");
		layout.putConstraint(SpringLayout.WEST, current, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, current, 150, SpringLayout.NORTH, this);
		add(current);
		current.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JLabel lateBooks = new JLabel ("Overdue Books");
		layout.putConstraint(SpringLayout.WEST, lateBooks, 400, SpringLayout.EAST, current);
		layout.putConstraint(SpringLayout.NORTH, lateBooks, 0, SpringLayout.NORTH, current);
		add(lateBooks);
		lateBooks.setFont(new Font("Dialog", Font.BOLD, 18));
		
		//Current Loans Table
		searchResults = Operations.checkedOutBooks(Integer.parseInt(LoginCustomer.pass));
		
		PatronTable model = new PatronTable(searchResults);
		
		currentLoans.setModel(model);
		currentLoans.setAutoCreateRowSorter(true);
		
		scrollPane = new JScrollPane(currentLoans);
		
		
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, current);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, current);
		add(scrollPane);
		 
		
		
		//Past Due Table
		searchResultsLate = Operations.pastDueBooks(Integer.parseInt(LoginCustomer.pass));
		
		PatronTable model1 = new PatronTable(searchResultsLate);
		
		pastDue.setModel(model1);
		pastDue.setAutoCreateRowSorter(true);
		
		scrollPaneLate = new JScrollPane(pastDue);
		
		
		layout.putConstraint(SpringLayout.WEST, scrollPaneLate, 0, SpringLayout.WEST, lateBooks);
		layout.putConstraint(SpringLayout.NORTH, scrollPaneLate, 10, SpringLayout.SOUTH, lateBooks);
		add(scrollPaneLate);
		
		
	}

}
