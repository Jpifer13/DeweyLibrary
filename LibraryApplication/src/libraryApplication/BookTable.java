package libraryApplication;


import java.util.List;

import javax.swing.table.AbstractTableModel;


public class BookTable extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TITLE_COL = 0;
	private static final int AUTHOR_COL = 1;
	private static final int PUBLISHER_COL = 2;
	private static final int BOOKID_COL = 3;
	

	private String[] columnNames = { "Title", "Author", "Publisher", "Book ID"};
	private List<BookData> books;

	public BookTable (List<BookData> book) {
		this.books = book;
	}



	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	

	@Override
	public Object getValueAt(int row, int col) {

		BookData tempBook = books.get(row);
		
		

		switch (col) {
		case TITLE_COL:
			return tempBook.getTitle();
		
		case AUTHOR_COL:
			return tempBook.getAuthorName();	
		
		case PUBLISHER_COL:
			return tempBook.getPublisherName();
			
		case BOOKID_COL:
			return tempBook.getBookId();
			
		default:
			return tempBook.getTitle();
		}
	}

	

}
