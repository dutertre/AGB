<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application de Gestion de Bibliothèque</title>
</head>
<body>
<h1>Accès à la base de données</h1>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<% 
out.println("Vous êtes bien connecté en session :");
out.println(request.getParameter("profile"));
out.println("avec l'utilisateur :");
out.println(request.getParameter("username"));
out.println(".");
%>
<h2>Ajouter ou supprimer un exemplaire</h2>
<% 
out.println("<form action='Process' method='POST' >");
out.println("Auteur: <input type='text' name='newAuthor' />");
out.println("Titre: <input type='text' name='newTitle' />");
out.println("Nombre d'exemplaires: <input type='number' name='quantity' />");
out.println("<input type='hidden' name='access' value='Bdd_biblio.jsp' />");
out.println("<input type='submit' name='add' value='Ajouter' />");
out.println("</form>");
%>
<h2>Ajouter ou supprimer un livre</h2>
<% 
out.println("<form action='Process' method='POST' >");
out.println("Auteur: <input type='text' name='delAuthor' />");
out.println("Titre: <input type='text' name='delTitle' />");
out.println("<input type='hidden' name='access' value='Bdd_biblio.jsp' />");
out.println("<input type='submit' name='add' value='Supprimer le livre' />");
out.println("</form>");
%>
<h2>Rechercher un livre</h2>
<% 
//recherche
out.println("<form action='Process' method='POST' >");
out.println("Auteur ou Titre: <input type='text' name='content' />");
out.println("<input type='hidden' name='access' value='Bdd_biblio.jsp' />");
out.println("<input type='submit' name='search' value='Rechercher' />");
out.println("</form>");
//ajout
%>
<h2>Résultat de la recherche</h2>
<%
String books_titles = (String) request.getAttribute("books_titles");
String books_author = (String) request.getAttribute("books_author");
String books_id = (String) request.getAttribute("books_id");
String books_dispo = (String) request.getAttribute("books_dispo");
String books_borrowed = (String) request.getAttribute("books_borrowed");
String books_total = (String) request.getAttribute("books_total");
String books_id_list[] = books_id.split(";"); 
String books_author_list[] = books_author.split(";"); 
String books_titles_list[] = books_titles.split(";"); 
String books_dispo_list[] = books_dispo.split(";"); 
String books_borrowed_list[] = books_borrowed.split(";"); 
String books_total_list[] = books_total.split(";");

%>	
<table>

   <tr>
   		<td style="border: 1px dotted black;"><b>Id</b></td>
       <% 
       for (String ss : books_id_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   <td style="border: 1px dotted black;"><b>Titre</b></td>
       <% 
       for (String ss : books_titles_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   	<td style="border: 1px dotted black;"><b>Auteur</b></td>
       <% 
       for (String ss : books_author_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
   </tr>

   <tr>
   	<td style="border: 1px dotted black;"><b>Total</b></td>
       <% 
       for (String ss : books_total_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   	<td style="border: 1px dotted black;"><b>Empreint</b></td>
       <% 
       for (String ss : books_borrowed_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
  </tr>
   <tr>
   	<td style="border: 1px dotted black;"><b>Disponible</b></td>
       <% 
       for (String ss : books_dispo_list) {
    	   %><td style="border: 1px dotted black;"><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   
</table>


<h2></h2>
</body>
</html>