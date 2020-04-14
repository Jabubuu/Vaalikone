<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page import="vaalikone.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> <%
		List<Vastaukset> KayttajanVastaukset = (List<Vastaukset>) request.getAttribute("KayttajanVastaukset");
		List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");
	    
	    for (int i = 0; i < kaikkiKysymykset.size(); i++) {
            %>
           <b>Kysymys <%= i + 1%>: <%= kaikkiKysymykset.get(i).getKysymys()%> Sinun(ehdokkaan) vastaus: <%= KayttajanVastaukset.get(i).getVastaus()%> Kommentti: <%= KayttajanVastaukset.get(i).getKommentti()%></b><br>
            <%
                }
	    	%>
        <form action="#" method="GET">
        	<label for="fname">Poistettavan vastauksen Id:</label>
        	<input type="hidden" name="func" value="Ehdokas"><br>
  			<input type="text" name="Poista"><br>
        	<input id="Nappi" type="submit" Value="Poista">                  
        </form>
</body>
</html>