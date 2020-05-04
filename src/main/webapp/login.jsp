<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import = "java.io.*,java.util.*" %>
<%@page import="vaalikone.Ehdokas"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="container">

<%  
String title = "Welcome Back to my website";
String userIDKey = new String("userID");
String userID = new String("ABCD");
	
// Check if this is new comer on your Webpage.
if (session.isNew() ){
   title = "Welcome to my website";
   session.setAttribute(userIDKey, userID);
   session.setAttribute("session","TRUE"); 
} 
userID = (String)session.getAttribute(userIDKey);

/* Ehdokas ehdokas =(Ehdokas)request.getAttribute("ehdokas");
session.setAttribute("ehdokas",ehdokas); 
String id = ehdokas.getEhdokasId();
session.setAttribute("id",id);  */



  %>  
    <p><%= title%></p>
  <p>You are successfully logged in!</p>  
Terve, ehdokas nr: <%= userID%>
Teesti: <%= userID%>

		<br>
		<form action="Edit.jsp" method="post">
			<button type="submit">Muokkaa vastauksia</button>
		</form>
		<br>
		<form action="Remove.jsp" method="post">
			<button type="submit">Poista vastaus</button>
		</form>
		<br>
		
		<form action="vastaus.jsp" method="post">
			<button type="submit">Vastaa kysymyksiin</button>
		</form>
		
<a href="logout.jsp">Kirjaudu ulos</a>

</div>
</body>
</html>