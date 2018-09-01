package libraryApplication;
import java.sql.*;

import java.util.*;

//import finalLibraryDatabase.BookData;


public class LibraryDatabase {
	
	
	private Connection myConn;
	
	public LibraryDatabase() throws Exception {
				
		String sql_username = "jp5321";
		String sql_passwd = "t9zUNsUcl";
		myConn = DriverManager.getConnection("jdbc:mysql://webdev.cislabs.uncw.edu/narayan13", sql_username, sql_passwd);
		
		System.out.println("DB connection successful");
	}
	/**
	 * shows the entire books table
	 * @return
	 * @throws Exception
	 */
	
	
	public List<BookData> getAllBooks() throws Exception{
		List<BookData> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
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
	
	/**
	 * Find books by title
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<BookData> searchBookbyTitle (String name) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = myConn.prepareStatement("select title, author_name, publisher_name, book_id from books natural join book_authors where title = ?");
			myStmt.setString(1, name);
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
	public List<BookData> searchBookbyAuthor (String author) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = myConn.prepareStatement("select title, author_name, publisher_name, book_id from books natural join book_authors where author_name = ?");
			myStmt.setString(1, author);
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
	public List<BookData> searchBookbyPublisher (String publisher) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = myConn.prepareStatement("select title, author_name, publisher_name, book_id from books natural join book_authors where publisher_name = ?");
			myStmt.setString(1, publisher);
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
	
	
	
	public List<BookData> searchBook (int book_id) throws SQLException{
		List<BookData> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = myConn.prepareStatement("select * from books where book_id = ?");
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
	
	
	
	/**
	 * converts book tuples to a book (using constructor)
	 * @param myRs
	 * @return
	 * @throws SQLException
	 */

	private BookData convertRowToBook(ResultSet myRs) throws SQLException {
		
		String title = myRs.getString("title");
		
		String author = myRs.getString("author_name");
		String publisher = myRs.getString("publisher_name");
		int id=myRs.getInt("book_id");
		
		
		
		BookData tempbook = new BookData(title, author, publisher, id);
		return tempbook;

	}
	
	
	
	
	public void reserveBook(String cardNo, String book_id) throws SQLException { //need to handle checking out from multiple branches
		PreparedStatement myStmt = null;
		
		String query = " insert into book_loans (book_id, branch_id, card_no, date_out, date_due)"
		        + " values (?, 9745, ?, curdate(), curdate()+7)";
		
		myStmt = myConn.prepareStatement(query);
		
		myStmt.setString(1, book_id);
		myStmt.setString(2, cardNo);
		
		
		
		myStmt.execute();
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//LibraryDatabase lb = new LibraryDatabase();
		//System.out.println(lb.getAllBooks());
		//System.out.println(lb.getAllBorrowers());
		//System.out.println(lb.searchBorrower(4001));
		//System.out.println(lb.searchBook(1002));
		
		

	}

}
