package libraryApplication;

public class PatronBooks {
	
	public String title;
	public String date_out;
	public String date_due;
	public String branch_name;
	
	public PatronBooks(String title, String date_out, String date_due, String branch_name) {
		this.title = title;
		this.date_out=date_out;
		this.date_due = date_due;
		this.branch_name=branch_name;
	}
	

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getCheckoutDate() {
		// TODO Auto-generated method stub
		return date_out;
	}

	public String getDueDate() {
		// TODO Auto-generated method stub
		return date_due;
	}


	public String getBranchName() {
		// TODO Auto-generated method stub
		return branch_name;
	}
		

}
