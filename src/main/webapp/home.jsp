<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="vaalikone.Ehdokas"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>

<!DOCTYPE html>
<html>
<head>
<title>Ehdokas home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">

<%  

String title = "";
String editTeksti = "";
String userID = "";
String user = "";
List<Ehdokkaat> kayttaja = null;

// Check if this is new comer on your Webpage.
if (session.isNew()){
	userID = (String)request.getAttribute("ehdokasID");
   title = "Terve, ehdokas numero: " + userID;
   
   session.setAttribute("ID",userID);
	kayttaja = (List<Ehdokkaat>) request.getAttribute("kayttaja");
	session.setAttribute("kayttaja", kayttaja);
}

userID = (String)session.getAttribute("ID");
kayttaja = (List<Ehdokkaat>) session.getAttribute("kayttaja");

  %>  
  <p><%= title%></p>
  <p><%= editTeksti%></p>  


		<br>
		<form action="PoistaMuokkaa" method="post">
		<input type="hidden" name="ehdokasID" value=<%=userID%>><br>
			<button type="submit">Muokkaa vastauksia</button>
		</form>

		<form action="Vaalikone" method="post">
			<button type="submit">Vastaa kysymyksiin</button>
		</form>
		<br>
		
<a href="logout.jsp">Kirjaudu ulos</a>
<br>
<%
	%>
	
			<%  for (Ehdokkaat haettuEhdokas : kayttaja) { %>
            
			<h2>Tiedot:</h2>
            <h3>Ehdokas numero: <%= haettuEhdokas.getEhdokasId()%></h3>
            <ul>
                <li><b>Nimi:</b><%= haettuEhdokas.getEtunimi()%> <%= haettuEhdokas.getSukunimi()%></li>
                <li><b>Ik‰:</b><%= haettuEhdokas.getIka()%></li>
                <li><b>Kotipaikkakunta:</b><%= haettuEhdokas.getKotipaikkakunta()%></li>
                <li><b>Ammatti:</b><%= haettuEhdokas.getAmmatti()%></li>
                <li><b>Puolue:</b><%= haettuEhdokas.getPuolue()%></li>
            </ul>
            <h3>Miksi haluat eduskuntaan?</h3>
            <p><%= haettuEhdokas.getMiksiEduskuntaan()%></p>
            <h3>Mit‰ asioita haluat edist‰‰?</h3>
            <p><%= haettuEhdokas.getMitaAsioitaHaluatEdistaa()%></p>
			<%
             }
			%>


</div>
</body>
</html>