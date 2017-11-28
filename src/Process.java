import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Process
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Library lib = new Library();
	ArrayList<Book> books = lib.bookage();
	ArrayList<Emprunt> emprunts = new ArrayList<Emprunt>();
	
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
		if(request.getParameter("access").equals("Index.jsp")) {
			login(request, response);
			getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Bdd_biblio.jsp")) {           
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_biblio.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Bdd_anon.jsp")) {
			login(request, response);
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_anon.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Bdd_adh.jsp")) {
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_adh.jsp").forward(request, response);
		}
	}
	
	//Methode pour le checher le login utilisateur et renvoyer les bons attributs.
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        
        //Login part
        if (login.equals("seb") && password.equals("pwdseb")) {
        		  HttpSession session = request.getSession();
        		  session.setMaxInactiveInterval(30);
        		  session.setAttribute("connected", "Bibliotecaire");
        		  session.setAttribute("username", login);
        		  
        }
        
        else if (login.equals("max") && password.equals("pwdmax")) {
	        	  HttpSession session = request.getSession();
	  		  session.setMaxInactiveInterval(30);
	  		  session.setAttribute("connected", "Adherent");
	  		  session.setAttribute("username", login);
        }

        else {
        		  HttpSession session = request.getSession();
        		  session.setMaxInactiveInterval(30);
        		  session.setAttribute("connected", "Temporaire");
        		  session.setAttribute("username", "Anonyme");
        }    
	    }
	
	//Méthode pour créer une bibliothèque
	public void getLibrary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//compteurs pour la recherche
			int cmp = 0;
			int breakpoint =0;
			String books_titles = "";
			String books_author = "";
			String books_id = "";
			String books_dispo = "";
			String books_borrowed = "";
			String books_total = "";
			HttpSession session = request.getSession();
			
			//ajouter un livre
			try {
				String newAuthor = (String) request.getParameter("newAuthor");
				String newTitle = (String) request.getParameter("newTitle");
				String quantity = (String) request.getParameter("quantity");
				System.out.println(newAuthor + newTitle + quantity);
				
				//ajout de livre
				for(int i=0; i<books.size(); i++) {
					if (newTitle.equals(books.get(i).getTitle()) && newAuthor.equals(books.get(i).getAuthor())) {
						System.out.println("existe deja en " + books.get(i).getTotal() + "exemplaires");
						System.out.println("on rajoute "+ Integer.parseInt(quantity) + "exemplaires");
						
						books.get(i).setTotal(Integer.parseInt(quantity));
						//si on a ajouté la quantité désirée on sort de la boucle
						breakpoint=i;
						break;
						}
					//dans le cas contraire on incrémente le compteur
					else {
						cmp++;
						System.out.println("nouveau livre");
						}	
				}
				//si le compteur est plus petit que là où on s'est arrêté c'est qu'il faut ajouter le livre avec le nombre d'exemplaires désirés
				if (cmp != breakpoint) {
					//le nouveau livre prend l'id suivant dans la liste
					Book book = new Book(books.size()+1, newAuthor, newTitle, Integer.parseInt(quantity), 0);
					books.add(book);
					System.out.println("ajout de "+ newTitle);
					}
				}
				catch(NullPointerException e)
				{
					System.out.println("catch at ajout");
				}
				
				try {
			///process EMPRUNT

                String empruntage = (String) request.getParameter("empruntage");
                String author = (String) request.getParameter("author");
                String title = (String) request.getParameter("title");
                String date = (String) request.getParameter("date");
                String user = (String) request.getParameter("user");
                
                //on va chercher le livre dans la liste
                for(int i=0; i<books.size(); i++) {
                    if (title.equals(books.get(i).getTitle()) && author.equals(books.get(i).getAuthor())) {

                        if(empruntage.equals("Emprunt")) {
                        books.get(i).setBorrow();
                        Emprunt emprunt = new Emprunt(books.get(i), user, date, true);
                        emprunts.add(emprunt);
                        System.out.println(author + title + "emprunté");
                        }
                        //sinon c'est une réservation
                        else {
                        	Emprunt emprunt = new Emprunt(books.get(i), user, date, false);
                            emprunts.add(emprunt);
                            System.out.println(author + title + "réservé");
                        }
                    break;
                        	
                        }
                }


            }
            catch(NullPointerException e) {
                System.out.println("catch at resa");
            }
			
			try {
				String content = (String) request.getParameter("content");
				
			//fonction recherche
				for(int i=0; i<books.size(); i++) {
					if(content.toLowerCase().contains(books.get(i).getTitle().toLowerCase()) || content.toLowerCase().contains(books.get(i).getAuthor().toLowerCase())) {
					books_titles += books.get(i).getTitle();
					 books_titles += ";";
					 books_author += books.get(i).getAuthor();
					 books_author += ";";
					 books_dispo += books.get(i).getTotal() - books.get(i).getBorrow();
					 books_dispo += ";";
					 books_borrowed += books.get(i).getBorrow();
					 books_borrowed += ";";
					 books_total += books.get(i).getTotal();
					 books_total += ";";
					}
			}
			
			}
			catch(NullPointerException e) {
				System.out.println("catch at search");
			}
			session.setAttribute("books", books);
			session.setAttribute("books_titles", books_titles);
			session.setAttribute("books_author", books_author);
			session.setAttribute("books_id", books_id);
			session.setAttribute("books_dispo", books_dispo);
			session.setAttribute("books_borrowed", books_borrowed);
			session.setAttribute("books_total", books_total);
				
			
			
	}
	}


        
  


