
public class Book {
	
	private int id;
	private String title;
	private String author;
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	//classe constructrice
	public Book(int id, String author, String title)
	{
		this.id = id;
		this.author = author;
		this.title = title;
	}
	
}

