package EJB;

import java.util.ArrayList;

import javax.ejb.Local;

import AGBpkg.Book;

@Local
public interface MyLibEJBLocal {
	public ArrayList<Book> toBook();
}
