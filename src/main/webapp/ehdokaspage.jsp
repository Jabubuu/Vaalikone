<%-- 
    Document   : vastaus
    Created on : 09-Apr-2015, 12:50:47
    Author     : Jonne
--%>

<%@page import="vaalikone.Kayttaja"%>
<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diginide Vaalikone 2.0</title>

<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
  <div id="container">
	<%
	
	List<Ehdokkaat> ehdokas = (List<Ehdokkaat>) request.getAttribute("ehdokas");
	Kayttaja usr = (Kayttaja) request.getAttribute("user");
	
	%>
	
	<h1> Tervetuloa <%= session.getAttribute("loginname")%></h1>
	<h1> ID:  <%= session.getAttribute("userid")%></h1>
	<h1> Func:  <%= session.getAttribute("func")%></h1>

              	<%  for (Ehdokkaat haettuEhdokas : ehdokas) { %>
            
			<h2>Tiedot:</h2>
            <h3>Ehdokas numero: <%= haettuEhdokas.getEhdokasId()%></h3>
            <ul>
                <li><b>Nimi:</b><%= haettuEhdokas.getEtunimi()%> <%= haettuEhdokas.getSukunimi()%></li>
                <li><b>Ikä:</b><%= haettuEhdokas.getIka()%></li>
                <li><b>Kotipaikkakunta:</b><%= haettuEhdokas.getKotipaikkakunta()%></li>
                <li><b>Ammatti:</b><%= haettuEhdokas.getAmmatti()%></li>
                <li><b>Puolue:</b><%= haettuEhdokas.getPuolue()%></li>
            </ul>
            <h3>Miksi haluat eduskuntaan?</h3>
            <p><%= haettuEhdokas.getMiksiEduskuntaan()%></p>
            <h3>Mitä asioita haluat edistää?</h3>
            <p><%= haettuEhdokas.getMitaAsioitaHaluatEdistaa()%></p>
			<%
             }
			%>
	
	
	<br>
				<form action="testi.jsp" method="post" id="loginForm">
				<button type="submit">Takaisin</button>
			</form>
 </div>
</body>
</html>









