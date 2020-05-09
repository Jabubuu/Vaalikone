package vaalikone;

import java.io.IOException;
import java.util.List;

//import java.io.PrintWriter;  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persist.Ehdokkaat;


public class Controller extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  


        String tunnus=request.getParameter("tunnus");  
        String salasana=request.getParameter("password");
        String haettuID = "";
        Ehdokas ehdokas = new Ehdokas(); 
        ehdokas.setSalasana(salasana);  
        ehdokas.setTunnus(tunnus);

        boolean status=ehdokas.validate();
        

        if(status){  
        	
        	
        	 HttpSession session=request.getSession();
        	 session.invalidate();
        	 List<Ehdokkaat> lista = null;
        	 lista = ehdokas.haeTiedot();
        	 for (Ehdokkaat haettuEhdokas : lista) {
        		 
        		 haettuID = String.valueOf(haettuEhdokas.getEhdokasId());
        	 }
        	 

        	request.setAttribute("kayttaja", ehdokas.haeTiedot());

        	request.setAttribute("ehdokas", ehdokas);
        	request.setAttribute("ehdokasID", haettuID);
            RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
            rd.forward(request, response);  
        }  
        else{  
            RequestDispatcher rd=request.getRequestDispatcher("loginerror.jsp");  
            rd.forward(request, response);  
        }

    }
	
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}  