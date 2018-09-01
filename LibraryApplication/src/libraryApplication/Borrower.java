package libraryApplication;

public class Borrower {
	
	private int card_no;
	private String name;
	private String address;
	private String phone;
	private double unpaid_dues;
	
	
	public Borrower(int card_no, String name, String address, String phone, double unpaid_dues) {
		super();
		this.card_no= card_no;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.unpaid_dues = unpaid_dues;
	}
	
	public int getCardNo() {
		return card_no;
	}
	
	public void setCardNo(int card_no) {
		this.card_no = card_no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDues() {
		return String.format("%.2f",unpaid_dues);
	}
	
	public void setDues(double dues) {
		this.unpaid_dues = dues;
	}

	@Override
	public String toString() {
		//work on later
		return String.format("%s, %s, %s, %s, %s\n" ,card_no, name, address, phone, unpaid_dues);
	}
}
;