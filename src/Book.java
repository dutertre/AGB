
public class Book {
	
	private int id;
	private String title;
	private String author;
	private int dispo;
	private int borrowed;
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
	
	public int getDispo() {
		return dispo;
	}
	
	public int getBorrow() {
		return borrowed;
	}

	//classe constructrice
	public Book(int id, String author, String title, int dispo, int borrowed)
	{
		this.id = id;
		this.author = author;
		this.title = title;
		this.dispo = dispo;
		this.borrowed = borrowed;
	}
	
}

