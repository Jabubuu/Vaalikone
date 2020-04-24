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

        String ehdokasID=request.getParameter("id");  
        String salasana=request.getParameter("password");  
        Ehdokas ehdokas = new Ehdokas();
        ehdokas.setEhdokasId(ehdokasID);  
        ehdokas.setSalasana(salasana);   

        boolean status=ehdokas.validate();
        

        if(status){  
        	
        	 HttpSession session=request.getSession();
        	 session.invalidate();
        	request.setAttribute("kayttaja", ehdokas.haeTiedot());
        	request.setAttribute("ehdokas", ehdokas);
        	request.setAttribute("ehdokasID", ehdokasID);
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