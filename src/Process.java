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
			HttpSession session = request.getSession();
  		  	session.setMaxInactiveInterval(30);
  		  	session.setAttribute("connected", "Temporaire");
  		  	session.setAttribute("username", "Anonyme");
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_anon.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Bdd_adh.jsp")) {
			getLibrary(request, response);
			getServletContext().getRequestDispatcher("/Bdd_adh.jsp").forward(request, response);
		}
		else if(request.getParameter("access").equals("Logout")) {
			logout(request, response);
			getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
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

        //else {
        //		  HttpSession session = request.getSession();
        	//	  session.setMaxInactiveInterval(30);
        	//	  session.setAttribute("connected", "Temporaire");
        	//	  session.setAttribute("username", "Anonyme");
        //}    
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
			
			//ajouter ou retirer des exemplaires
			try {
				String newAuthor = (String) request.getParameter("newAuthor");
				String newTitle = (String) request.getParameter("newTitle");
				String quantity = (String) request.getParameter("quantity");
				System.out.println(newAuthor + newTitle + quantity);
				
				//ajout d'exemplaires
				for(int i=0; i<books.size(); i++) {
					if (newTitle.equals(books.get(i).getTitle()) && newAuthor.equals(books.get(i).getAuthor())) {
						
						System.out.println("existe deja en " + books.get(i).getTotal() + "exemplaires");
						System.out.println("on effectue l'opération "+ Integer.parseInt(quantity) + "exemplaires");
						//on règle comme quantité disponible la quantité présente + ce qu'on ajoute
						books.get(i).setTotal(books.get(i).getTotal()+Integer.parseInt(quantity));
						if (books.get(i).getTotal() < 0) books.get(i).setTotal(0);
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
			//supprimer un livre
			try {
				String author = (String) request.getParameter("author");
				String title = (String) request.getParameter("title");
				String remove = (String) request.getParameter("remove");
				//suppression d'un livre
				if(remove.equals("Suppression")) {
				for(int i=0; i<books.size(); i++) {
					if (title.equals(books.get(i).getTitle()) && author.equals(books.get(i).getAuthor())) {
						
						System.out.println("existe en " + books.get(i).getTotal() + " exemplaires, on va le supprimer");
						//on règle comme quantité disponible la quantité présente + ce qu'on ajoute
						books.remove(i);
						break;
					}
				}
				}
				
			}
			catch(NullPointerException e)
			{
				System.out.println("catch at suppression");
			}
				try {
			///process EMPRUNT / restitution

                String empruntage = (String) request.getParameter("empruntage");
                String author = (String) request.getParameter("author");
                String title = (String) request.getParameter("title");
                String user = (String) request.getParameter("user");
                
                //on va chercher le livre dans la liste
                for(int i=0; i<books.size(); i++) {
                    if (title.equals(books.get(i).getTitle()) && author.equals(books.get(i).getAuthor())) {

                        if(empruntage.equals("Emprunt")) {
                        	//on parcourt la liste des emprunts pour voir si la réservation existe
                        	for(int j = 0; j<emprunts.size(); j++)
                        	{
                        		//on trouve une réservation avec le livre et l'utilisateur
                        		if(user.equals(emprunts.get(j).getUser()) && emprunts.get(j).getBook() == books.get(i))
                        		{
                        			//on passe emprunt à true
                        			emprunts.get(j).setEmprunt(true);
                        			System.out.println("Réservation annulée pour" + user);
                        			//on marque le livre comme emprunté
                        			books.get(i).setBorrow();
                        			System.out.println(author + title + "emprunté");
                        			cmp++;
                        			break;
                        		
                        		}
                        		
                        }
                        	// Quand on a terminé la boucle
                        //si le compteur est nul c'est un emprunt qui n'était pas réservé
                    		if(cmp == 0) {
                        		//on marque le livre emprunté
                        books.get(i).setBorrow();
                        //on enregistre un nouvel emprunt
                        Emprunt emprunt = new Emprunt(books.get(i), user, "plop", true);
                        emprunts.add(emprunt);
                        System.out.println(author + title + " emprunté");
                            }
                        }
                        
                        //cas restitution
                        else if (empruntage.equals("Restitution"))
                        {
                        	//on parcourt la liste des emprunts pour voir si l'emprunt existe
                        	for(int j = 0; j<emprunts.size(); j++)
                        	{
                        		//on trouve un emprunt avec le livre et l'utilisateur
                        		if(user.equals(emprunts.get(j).getUser()) && emprunts.get(j).getBook() == books.get(i))
                        		{
                        			//on le supprime
                        			emprunts.remove(j).setEmprunt(true);
                        			System.out.println(user +" a restitué " + title);
                        			//on marque le livre comme rendu
                        			books.get(i).setReturn();
                        			break;
                        		}
                        		
                        }
                        	
                        }
                    }
                        	     	
                }

            }
            catch(NullPointerException e) {
                System.out.println("catch at emprunt");
            }
				
				///process réservation

                try {
				String resa = (String) request.getParameter("resa");
                String author = (String) request.getParameter("author");
                String title = (String) request.getParameter("title");
                String date = (String) request.getParameter("date");
                
                //on va chercher le livre dans la liste
                for(int i=0; i<books.size(); i++) {
                    if (title.equals(books.get(i).getTitle()) && author.equals(books.get(i).getAuthor())) {

                        if(resa.equals("Reservation")) {
                        books.get(i).setBorrow();
                        Emprunt emprunt = new Emprunt(books.get(i), (String)session.getAttribute("username"), date, false);
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
              ///process cancel

                try {
				String cancel = (String) request.getParameter("cancel");
                String author = (String) request.getParameter("author");
                String title = (String) request.getParameter("title");
                if (cancel.equals("Annuler")){
                //on va chercher le livre dans la liste
                for(int i=0; i<books.size(); i++) {
                    if (title.equals(books.get(i).getTitle()) && author.equals(books.get(i).getAuthor())) {

                    //on parcourt la liste des emprunts pour voir si la réservation existe
                    	for(int j = 0; j<emprunts.size(); j++)
                    	{
                    		//on trouve une réservation avec le livre et l'utilisateur
                    		if(session.getAttribute("username").equals(emprunts.get(j).getUser()) && emprunts.get(j).getBook() == books.get(i))
                    		{
                    			emprunts.remove(j);
                    			System.out.println("Réservation annulée");
                    		}		
                    	}

                    break;
                        	
                        }
                }
                }

            }
            catch(NullPointerException e) {
                System.out.println("catch at cancel");
            }
			
			//SEARCH
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


        
  