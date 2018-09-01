package libraryApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Operations {

	private static Statement myStmt = null;
	private static ResultSet myRs = null;

	

	public static boolean checkValidLogin(int cardNO) throws Exception {
		boolean valid = false;
		int check = 0;
		int card_no;

		try {
			// myStmt = Viewer.myConn.createStatement();
			PreparedStatement stmt = Viewer.myConn.prepareStatement("select card_no from borrowers where card_no = ?");
			stmt.setInt(1, cardNO);
			myRs = stmt.executeQuery();
			// myRs = myStmt.executeQuery("select card_no from admins where card_no = " +
			// cardNO);
			while (myRs.next()) {
				card_no = myRs.getInt(1);
				check = card_no;
			}

		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
			e.printStackTrace();
		}
		if (cardNO == check) {
			valid = true;
		}

		return valid;

	}

	public static boolean checkAdmin(int cardNO) {
		boolean admin = false;
		int check = 0;
		int card_no;

		try {
			// myStmt = Viewer.myConn.createStatement();
			PreparedStatement stmt = Viewer.myConn.prepareStatement("select card_no from admins where card_no = ?");
			stmt.setInt(1, cardNO);
			myRs = stmt.executeQuery();
			// myRs = myStmt.executeQuery("select card_no from admins where card_no = " +
			// cardNO);
			while (myRs.next()) {
				card_no = myRs.getInt(1);
				check = card_no;
			}

		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
			e.printStackTrace();
		}
		if (cardNO == check) {
			admin = true;
		}

		return admin;
	}
	

	
	public static List<Borrower> searchforBorrower (int card_no) throws SQLException{
		List<Borrower> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select * from borrowers where card_no = ?");
			myStmt.setString(1, Integer.toString(card_no));
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Borrower temp = convertRowToBorrower(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}

	/**
	 * converts borrower tuples to a borrower (using constructor)
	 * 
	 * @param myRs
	 * @return
	 * @throws SQLException
	 */
	private static Borrower convertRowToBorrower(ResultSet myRs) throws SQLException {
		int card_no = myRs.getInt("card_no");
		String name = myRs.getString("name");
		String address = myRs.getString("address");
		String phone = myRs.getString("phone");
		double unpaid_dues = myRs.getDouble("unpaid_dues");

		Borrower tempborrower = new Borrower(card_no, name, address, phone, unpaid_dues);
		return tempborrower;
	}
	

		
	/**
	 * shows the entire books table
	 * @return
	 * @throws Exception
	 */
	public static List<BookData> getAllBooks() throws Exception{
		List<BookData> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = Viewer.myConn.createStatement();
			myRs = myStmt.executeQuery("select title, author_name, publisher_name, book_id from books natural join book_authors");
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
			
		}
		
	}
	
	
	
	public static List<BookData> getAllBooksfromBranch(String branch_number) throws Exception{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, author_name, publisher_name, book_id from book_copies natural join books natural join branches natural join book_authors where branch_id = ? and no_copies>0");
			myStmt.setString(1, branch_number);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}
	
	
	
	
	
	/**
	 * Find books by title and branch
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static List<BookData> searchBookbyTitle (String name, String branch_num) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		
		
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, author_name, publisher_name, book_id from book_copies natural join books natural join branches natural join book_authors where branch_id = ? and title = ? and no_copies>0");
			myStmt.setString(1, branch_num);
			myStmt.setString(2, name);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}
	
	
	/**
	 * Find books by author
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static List<BookData> searchBookbyAuthor (String author, String branch_num) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		
		
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, author_name, publisher_name, book_id from book_copies natural join books natural join branches natural join book_authors where branch_id = ?  and author_name = ? and no_copies>0");
			myStmt.setString(2, author);
			myStmt.setString(1, branch_num);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}
	
	/**
	 * Find books by publisher_name
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static List<BookData> searchBookbyPublisher (String publisher, String branch_num) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, author_name, publisher_name, book_id from book_copies natural join books natural join branches natural join book_authors where branch_id = ?  and publisher_name = ? and no_copies>0");
			myStmt.setString(2, publisher);
			myStmt.setString(1, branch_num);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}
	
	
	
	public static List<BookData> searchBookbyId (int book_id) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, author_name, publisher_name, book_id from books natural join book_authors where book_id = ?");
			myStmt.setString(1, Integer.toString(book_id));
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				BookData tempbook = convertRowToBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
		
	}
	
	public static List<PatronBooks> checkedOutBooks(int card_no) throws SQLException{
		List<PatronBooks> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, date_out, date_due, branch_name from books natural join book_loans natural join branches where card_no = ? and date_returned is null");
			myStmt.setString(1, Integer.toString(card_no));
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				PatronBooks tempbook = convertRowToPatronBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
	}
	
	public static List<PatronBooks> pastDueBooks(int card_no) throws SQLException{
		List<PatronBooks> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = Viewer.myConn.prepareStatement("select title, date_out, date_due, branch_name from books natural join book_loans natural join branches where card_no = ? and date_due<curdate() and date_returned is null");
			myStmt.setString(1, Integer.toString(card_no));
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				PatronBooks tempbook = convertRowToPatronBook(myRs);
				list.add(tempbook);
			}
			
			return list;
		}
		finally {
			myRs.close();
			myStmt.close();
		
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * converts book tuples to a book (using constructor)
	 * @param myRs
	 * @return
	 * @throws SQLException
	 */

	private static BookData convertRowToBook(ResultSet myRs) throws SQLException {
		
		String title = myRs.getString("title");
		
		String author = myRs.getString("author_name");
		String publisher = myRs.getString("publisher_name");
		int id=myRs.getInt("book_id");
		
		
		
		BookData tempbook = new BookData(title, author, publisher, id);
		return tempbook;

	}
	
	
	private static PatronBooks convertRowToPatronBook(ResultSet myRs) throws SQLException {
		String title = myRs.getString("title");
		String date_out = myRs.getString("date_out");
		String date_due = myRs.getString("date_due");
		String branch_name =myRs.getString("branch_name");

		
		PatronBooks booklist = new PatronBooks(title, date_out, date_due, branch_name);
		return booklist;

	}
	
	
	
	
	public static void reserveBook(String cardNo, String book_id, String branch_number) throws SQLException { //need to handle checking out from multiple branches
		PreparedStatement myStmt = null;
		
		
		
		
		
		String query = " insert into book_loans (book_id, branch_id, card_no, date_out, date_due)"
		        + " values (?, ?, ?, curdate(), date_add(curdate(), Interval 7 DAY))";
		
		myStmt = Viewer.myConn.prepareStatement(query);
		
		myStmt.setString(1, book_id);
		myStmt.setString(2, branch_number);
		myStmt.setString(3, cardNo);
		
		
		
		myStmt.execute();
		
	}
	
	public static String getLevel(String cardNo) throws SQLException {
		String level = null;
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = Viewer.myConn.createStatement();
			myRs = myStmt.executeQuery("select card_no, PatronLevel(total) as reader_level from loan_totals");
			
			while (myRs.next() && level == null) {
				
				if (myRs.getInt("card_no")== Integer.parseInt(cardNo)){
					level = myRs.getString("reader_level");
				}
				
			}
			return level;
			
			
		}
		finally {
			myRs.close();
			myStmt.close();
			
		}
		

	}
}
