package libraryApplication;

public class BookData {
	
	public String title;
	public String publisher;
	public String author;
	public int id;
	
	/**
	 * BookData constructor to create a book
	 * 
	 * @param title The title of the book
	 * @param author The author of the book
	 * @param id The assigned book id
	 * @param publisher The publisher of the book
	 */
	
	public BookData(String title, String author, String publisher, int id) {
		this.title=title;
		this.author=author;
		this.id=id;
		this.publisher=publisher;
	}
	
	public String toString(){
		return "Book: \t"+this.title+"\t by "+this.author+"\t published by "+this.publisher +"\t book id = "+this.id ;
			
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getAuthorName() {
		// TODO Auto-generated method stub
		return author;
	}

	public int getBookId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getPublisherName() {
		// TODO Auto-generated method stub
		return publisher;
	}

}
