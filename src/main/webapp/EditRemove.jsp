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
		int VastausNum;
		String Kommentti = "Kommentti";%>
		<form><%
	    for (int i = 0; i < kaikkiKysymykset.size(); i++) {
            if (KayttajanVastaukset.get(i).getVastaus() == null){%>
          	<%
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
	    	</form>
        <form action="#" method="GET">
        	<label for="fname">Poistettavan vastauksen Id:</label>
        	<input type="hidden" name="func" value="Ehdokas"><br>
        	<input type="hidden" name="ehdokasID" value=<%request.getAttribute("ehdokasID"); %>><br>
  			<input type="text" name="Poista"><br>
        	<input id="Nappi" type="submit">                  
        </form>
        <form action="#" method="GET">
        	<label for="fname">Muokattavan vastauksen Id:</label>
        	<input type="hidden" name="func" value="Ehdokas"><br>
        	<input type="hidden" name="ehdokasID" value=<%request.getAttribute("ehdokasID"); %>><br>
  			<input type="text" name="Muokkaa"><br>
  			<label for="fname">Uusi vastaus numero 1-5:</label><br>
  			<input type="text" name="UusiVastaus"><br>
  			<label for="fname">Uusi kommentti:</label><br>
  			<input type="text" name="UusiKommentti"><br>
        	<input id="Nappi" type="submit">                  
        </form>
</body>
</html>