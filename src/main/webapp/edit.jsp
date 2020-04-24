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
session.setAttribute("KayttajanVastaukset", KayttajanVastaukset);
List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");
session.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
  %>  

Terve, ehdokas nr: <%= userID%> <br>

		<form action="Poista" method="GET">

				<label for="fname">Poistettavan vastauksen Id:</label> <br>
				<input name="id" size="30" required minlength="1" maxlength="2"> 
        	<input type="hidden" name="ehdokasID" value= <%= userID%>><br>
        	<button type="submit">Poista</button> 
					<br>
				
			</form>
			<br>
			
			<form action="Muokkaa" method="GET">
			
        	<label for="fname">Muokattavan vastauksen Id:</label>
        	<input type="hidden" name="func" value="Ehdokas"><br>
        	<input type="hidden" name="ehdokasID" value=<%= userID%>><br>
  			<input type="text" name="Muokkaa"><br>
  			<label for="fname">Uusi vastaus numero 1-5:</label><br>
  			<input type="text" name="UusiVastaus"><br>
  			<label for="fname">Uusi kommentti:</label><br>
  			<input type="text" name="UusiKommentti"><br>
        	<button type="submit">Muokkaa</button>   
        	              
        </form>

 <%
 

		int VastausNum;
		String Kommentti = "Kommentti";%>
		<form><%
	    for (int i = 0; i < kaikkiKysymykset.size(); i++) {
            if (KayttajanVastaukset.get(i).getVastaus() == null){%>
            <label>Kysymys <%= i + 1%>:<%= kaikkiKysymykset.get(i).getKysymys()%></label><br>
       		<label>Vastaus: Ei vastausta</label><br>
       		<lable>Kommentti: Ei Kommenttia</label><br><br><%
            }
            else{
            VastausNum = KayttajanVastaukset.get(i).getVastaus();
            Kommentti = KayttajanVastaukset.get(i).getKommentti();%>
            
            <label>Kysymys <%= i + 1%>:<%= kaikkiKysymykset.get(i).getKysymys()%></label><br>
       		<label>Vastaus: <%= VastausNum%></label><br>
       		<lable>Kommentti: <%= Kommentti%></label><br><br><%
            	}
            }
			%>


</div>
</body>
</html>