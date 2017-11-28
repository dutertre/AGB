
public class Book {
	
	private int id;
	private String title;
	private String author;
	private int total;
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
	
	public int getTotal() {
		return total;
	}
	
	//sert à incrémenter le total dans le cas d'un ajout d'exemplaires
	public void setTotal(int total) {
		this.total += total;
	}
	
	public int getBorrow() {
		return borrowed;
	}
	public void setBorrow()
	{
		this.borrowed +=1;
	}
	
	//classe constructrice
	public Book(int id, String author, String title, int total, int borrowed)
	{
		this.id = id;
		this.author = author;
		this.title = title;
		this.total = total;
		this.borrowed = borrowed;
	}
	
}

