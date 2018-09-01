package libraryApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PatronTable extends AbstractTableModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TITLE = 0;
	private static final int DATE_OUT = 1;
	private static final int DATE_DUE = 2;
	private static final int BRANCH_NAME = 3;
	
	private String[] columnNames = { "Title", "Checkout Date", "Due Date", "Branch Name"};
	private List<PatronBooks> booklist;
	
	public PatronTable (List<PatronBooks> booklist) {
		this.booklist = booklist;
	}
	

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return booklist.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		PatronBooks tempPatron = booklist.get(rowIndex);
		
		

		switch (columnIndex) {
		case TITLE:
			return tempPatron.getTitle();
		
		case DATE_OUT:
			return tempPatron.getCheckoutDate();	
		
		case DATE_DUE:
			return tempPatron.getDueDate();
			
		case BRANCH_NAME:
			return tempPatron.getBranchName();
			
		default:
			return tempPatron.getTitle();
		}
		
		
	}

}
