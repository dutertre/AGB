package AGBpkg;
import java.util.ArrayList;

public class Library {
	
	public static ArrayList<Book> bookage()
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		/*bibliothèque statique
		 * 
		 */
		Book book1 = new Book(1,"Pierre Boule","La Planete des Singes", 10, 3);
		Book book2 = new Book(2,"Sylvain Tesson","Dans les forêts de Siberie", 3, 1);
		Book book3 = new Book(3,"Albert Camus","La Peste", 4, 4);
		Book book4 = new Book(4,"Haruki Murakami","1Q84 Trilogie", 2, 0);
		Book book5 = new Book(5,"Herge","Tintin au Tibet", 25, 10);
		Book book6 = new Book(5,"Herge","Objectif Lune", 23, 8);
		Book book7 = new Book(6,"Karl Marx","Das Kapital", 3, 0);
		Book book8 = new Book(7,"Timothy Lachin","Into the Cronosphere", 20, 3);
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