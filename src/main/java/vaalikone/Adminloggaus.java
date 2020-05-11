package vaalikone;

import java.io.IOException;
import java.security.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;  



public class Adminloggaus extends HttpServlet {

	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
	String acc = request.getParameter("tunnus");
	String pw = request.getParameter("password");
	String secpw = crypt(pw);
	String secacc = crypt(acc);
	
	// tunnus admin
	// salasana 1234
	String corpw = "81dc9bdb52d04dc20036dbd8313ed055";
	String coracc = "21232f297a57a5a743894a0e4a801fc3";
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
	
	if (corpw.equals(secpw) & coracc.equals(secacc)) {
//		response.sendRedirect("AddEhdokas.html");
	
        RequestDispatcher rd=request.getRequestDispatcher("AddEhdokas.jsp");  
        rd.forward(request, response);  
		}
	else {
	//	response.sendRedirect("loginerror.jsp");
		
		RequestDispatcher rd=request.getRequestDispatcher("loginerror.jsp");  
        rd.forward(request, response);  
	}
	
	  }
	  public static String crypt(String str) {
	      if (str == null || str.length() == 0) {
	          throw new IllegalArgumentException("String to encript cannot be null or zero length");
	      }

	      MessageDigest digester;
	      try {
	          digester = MessageDigest.getInstance("MD5");

	          digester.update(str.getBytes());
	          byte[] hash = digester.digest();
	          StringBuffer hexString = new StringBuffer();
	          for (int i = 0; i < hash.length; i++) {
	              if ((0xff & hash[i]) < 0x10) {
	                  hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	              } else {
	                  hexString.append(Integer.toHexString(0xFF & hash[i]));
	              }
	          }
	          return hexString.toString();
	      } catch (NoSuchAlgorithmException e) {
	          e.printStackTrace();
	      }
	      return "";
	  }
	
	
	
	
	
}
