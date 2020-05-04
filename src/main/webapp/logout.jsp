<%
session.invalidate();
response.sendRedirect("index.html");
System.out.println("Logged out");
%>