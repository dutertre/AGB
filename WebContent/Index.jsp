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

<form action='Process' method='POST' >
Login: <input type='text' name='login' />
<p></p>
Password: <input type='password' name='password' />
<input type='hidden' name='access' value='Index.jsp' />
<p></p>
<input type='submit' value='Connect' />
</form>

<h2>Informations</h2>
<%
try{
	if (session.getAttribute("connected").equals("Mauvais login ou mot de passe.")){
		out.println(session.getAttribute("connected"));
	}
	else if (session.getAttribute("connected").equals("Bibliotecaire")){
		out.println("Vous êtes bien connecté en profil Bibliothécaire avec l'utilisateur :");
		out.println(session.getAttribute("username"));
		out.println(".");
		%>
		<h2>Accéder à la base de données</h2>
		<%
		String profile = (String) session.getAttribute("connected");
		String username = (String) session.getAttribute("login");
		%>
		<form action='Process' method='POST' >
		<%
		out.println("<input type='hidden' name='profile' value='" + profile + "' />");
		out.println("<input type='hidden' name='username' value='" + username + "' />");
		%>
		<input type='hidden' name='access' value='Bdd_biblio.jsp' />
		<input type='submit' name='bdd' value='Accéder' />
		</form>
		<%
	}
	else if (session.getAttribute("connected").equals("Adherent")){
		out.println("Vous êtes bien connecté en profil Adhérent avec l'utilisateur :");
		out.println(session.getAttribute("username"));
		out.println(".");
		%>
		<h2>Accéder à la base de données</h2>
		<%
		String profile = (String) session.getAttribute("connected");
		String username = (String) session.getAttribute("login");
		out.println("<form action='Process' method='POST' >");
		out.println("<input type='hidden' name='profile' value='" + profile + "' />");
		out.println("<input type='hidden' name='username' value='" + username + "' />");
		out.println("<input type='hidden' name='access' value='Bdd_adh.jsp' />");
		out.println("<input type='submit' name='bdd' value='Accéder' />");
		out.println("</form>");
	}
	else if (session.getAttribute("connected").equals("Temporaire")){
		out.println("Vous n'avez pas rentré de login/mot de passe reconnu.");
	}
}
catch(NullPointerException e){
	
	String profile = "Temporaire";
	String username = "Anonyme";
	out.println("Vous n'êtes pas connecté !");
	%>
	<h2>Accéder à la base de données</h2>
	<%
	out.println("<form action='Process' method='POST' >");
	out.println("<input type='hidden' name='login' value='" + "null" + "' />");
	out.println("<input type='hidden' name='password' value='" + "null" + "' />");
	out.println("<input type='hidden' name='profile' value='" + profile + "' />");
	out.println("<input type='hidden' name='username' value='" + username + "' />");
	out.println("<input type='hidden' name='access' value='Bdd_anon.jsp' />");
	out.println("<input type='submit' name='bdd' value='Accéder' />");
	out.println("</form>");
	
}
%>

</body>
</html>