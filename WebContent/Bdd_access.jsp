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
<%@ page import="java.util.Arrays" %>
<% 
out.println("Vous êtes bien connecté en session :");
out.println(request.getParameter("profile"));
out.println("avec l'utilisateur :");
out.println(request.getParameter("username"));
out.println(".");
out.println(request.getAttribute("list"));

%>
<h2></h2>
</body>
</html>