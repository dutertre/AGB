import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Process
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("access").equals("Bdd_access.jsp")) {
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_access.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Index.jsp")) {
			login(request, response);
			getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
		}
	}
	
	//Methode pour le checher le login utilisateur et renvoyer les bons attributs.
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        
        //Login part
        if (login.equals("seb") && password.equals("pwdseb")) {
        		  request.setAttribute("connected", "Bibliotecaire");              
        }
        
        else if (login.equals("max") && password.equals("pwdmax")) {
  		  	  request.setAttribute("connected", "Adherent");
        }

        else {
        		request.setAttribute("connected", "Mauvais login ou mot de passe.");
        }    
	    }
	
	//Méthode pour créer une bibliothèque
	public void getLibrary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Library lib = new Library();
			ArrayList<Book> books = lib.bookage();
			
			String books_titles = "";
			String books_author = "";
			String books_id = "";
			String books_dispo = "";
			String books_borrowed = "";
			String books_total = "";
			
			for(int i=0; i<books.size(); i++) {
				 books_titles += books.get(i).getTitle();
				 books_titles += ";";
				 books_author += books.get(i).getAuthor();
				 books_author += ";";
				 books_id += books.get(i).getId();
				 books_id += ";";
				 books_dispo += books.get(i).getTotal() - books.get(i).getBorrow();
				 books_dispo += ";";
				 books_borrowed += books.get(i).getBorrow();
				 books_borrowed += ";";
				 books_total += books.get(i).getTotal();
				 books_total += ";";
			}
			request.setAttribute("books_titles", books_titles);
			request.setAttribute("books_author", books_author);
			request.setAttribute("books_id", books_id);
			request.setAttribute("books_dispo", books_dispo);
			request.setAttribute("books_borrowed", books_borrowed);
			request.setAttribute("books_total", books_total);
			
	}
	}


        
  


