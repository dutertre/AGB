import java.io.IOException;
import java.util.ArrayList;

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
		login(request, response);
		getLibrary(request, response);
		getServletContext().getRequestDispatcher("/Bdd_access.jsp").forward(request, response);
		
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
			System.out.println(books);
			request.setAttribute("list", books);
			
	}
	}


        
  


