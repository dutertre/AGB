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
<% 
out.println("Vous êtes bien connecté en session :");
out.println(request.getParameter("profile"));
out.println("avec l'utilisateur :");
out.println(request.getParameter("username"));
out.println(".");
%>
<p>Voici la liste des livres disponibles :</p>
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
   		<td><b>Id</b></td>
       <% 
       for (String ss : books_id_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   <td><b>Titre</b></td>
       <% 
       for (String ss : books_titles_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   	<td><b>Autheur</b></td>
       <% 
       for (String ss : books_author_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
   </tr>

   <tr>
   	<td><b>Total</b></td>
       <% 
       for (String ss : books_total_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   <tr>
   	<td><b>Empreint</b></td>
       <% 
       for (String ss : books_borrowed_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
  </tr>
   <tr>
   	<td><b>Disponible</b></td>
       <% 
       for (String ss : books_dispo_list) {
    	   %><td><% out.println(ss);%><td><% 
        }
       %>
   </tr>
   
</table>


<h2></h2>
</body>
</html>