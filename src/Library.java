import java.util.ArrayList;

public class Library {
	
	public static ArrayList<Book> bookage()
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		/*bibliothèque statique
		 * 
		 */
		Book book1 = new Book(1,"Pierre Boule","La Planète des Singes");
		Book book2 = new Book(2,"Sylvain Tesson","Dans les forêts de Sibérie");
		Book book3 = new Book(3,"Eric Zemmour","Le Suicide Français");
		Book book4 = new Book(4,"Eric Zemmour","Le Suicide Français");
		Book book5 = new Book(5,"Haruki Murakami","1Q84 Trilogie");
		Book book6 = new Book(6,"Hergé","Tintin au Congo");
		Book book7 = new Book(7,"Karl Marx","Das Kapital");
		Book book8 = new Book(8,"Timothy Lachin","Into the Cronosphere");
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		bookList.add(book6);
		bookList.add(book7);
		bookList.add(book8);

		return bookList;
		
	}
	
	
	
}