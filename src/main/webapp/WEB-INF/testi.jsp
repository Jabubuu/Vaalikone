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
 </div>
</body>
</html>









