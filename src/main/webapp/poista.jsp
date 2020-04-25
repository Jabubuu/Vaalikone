<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>

<!DOCTYPE html>
<html>
<head>
<title>Ehdokas edit</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">

		<form action="home.jsp" method="post" id="loginForm">
			<button type="submit">Takaisin</button>
		</form>

<%  

String userID = (String)session.getAttribute("ID");
List<Ehdokkaat> kayttaja = (List<Ehdokkaat>) session.getAttribute("kayttaja");
List<Vastaukset> KayttajanVastaukset = (List<Vastaukset>) request.getAttribute("KayttajanVastaukset");
List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");
  %>  

Terve, ehdokas nr: <%= userID%> <br>

		<form action="Poista" method="GET">

				<label for="fname">Poistettavan vastauksen Id:</label> <br>
				<input name="id" size="30" required minlength="1" maxlength="2"> 
        	<input type="hidden" name="ehdokasID" value= <%= userID%>><br>
					<br>
				<button type="submit">Poista</button>
			</form>

</div>
</body>
</html>