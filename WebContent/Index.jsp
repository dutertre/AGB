<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application de Gestion de Bibliothèque</title>
</head>
<body>

<h1>Application de Gestion de Bibliothèque</h1>
<h2>Connexion</h2>
<%
out.println("<form action='Process' method='POST' >");
out.println("Login: <input type='text' name='login' />");
%>
<p></p>
<%
out.println("Password: <input type='password' name='password' />");
out.println("<input type='hidden' name='access' value='Index.jsp' />");
%>
<p></p>
<%
out.println("<input type='submit' value='Connect' />");
out.println("</form>");
%>

<h2>Informations</h2>

<%

try{
	if (request.getAttribute("connected").equals("Mauvais login ou mot de passe.")){
		out.println(request.getAttribute("connected"));
	}
	else if (request.getAttribute("connected").equals("Bibliotecaire")){
		out.println("Vous êtes bien connecté en profil Bibliothécaire avec l'utilisateur :");
		out.println(request.getParameter("login"));
		out.println(".");
		%>
		<h2>Accéder à la base de données</h2>
		<%
		String profile = (String) request.getAttribute("connected");
		String username = (String) request.getParameter("login");
		out.println("<form action='Process' method='POST' >");
		out.println("<input type='hidden' name='profile' value='" + profile + "' />");
		out.println("<input type='hidden' name='username' value='" + username + "' />");
		out.println("<input type='hidden' name='access' value='Bdd_biblio.jsp' />");
		out.println("<input type='submit' name='bdd' value='Accéder' />");
		out.println("</form>");
	}
	else if (request.getAttribute("connected").equals("Adherent")){
		out.println("Vous êtes bien connecté en profil Adhérent avec l'utilisateur :");
		out.println(request.getParameter("login"));
		out.println(".");
		%>
		<h2>Accéder à la base de données</h2>
		<%
		String profile = (String) request.getAttribute("connected");
		String username = (String) request.getParameter("login");
		out.println("<form action='Process' method='POST' >");
		out.println("<input type='hidden' name='profile' value='" + profile + "' />");
		out.println("<input type='hidden' name='username' value='" + username + "' />");
		out.println("<input type='hidden' name='access' value='Bdd_adh.jsp' />");
		out.println("<input type='submit' name='bdd' value='Accéder' />");
		out.println("</form>");
	}
}
catch(NullPointerException e){
	String profile = "Anonyme";
	String username = "Temporaire";
	out.println("Vous n'êtes pas connecté !");
	%>
	<h2>Accéder à la base de données</h2>
	<%
	out.println("<form action='Process' method='POST' >");
	out.println("<input type='hidden' name='profile' value='" + profile + "' />");
	out.println("<input type='hidden' name='username' value='" + username + "' />");
	out.println("<input type='hidden' name='access' value='Bdd_anon.jsp' />");
	out.println("<input type='submit' name='bdd' value='Accéder' />");
	out.println("</form>");
	
}
%>
</body>
</html>