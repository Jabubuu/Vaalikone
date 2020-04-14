 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persist.Kysymykset;

/**
 * Vaalikone-servlet, vastaa vaalikoneen varsinaisesta toiminnallisuudesta
 *
 * @author Jonne
 */
public class VastausEdit extends HttpServlet {
	
    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new Kayttaja();
            logger.log(Level.FINE, "Luotu uusi k�ytt�j�olio");
            session.setAttribute("usrobj", usr);
        }
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	response.getWriter().println("EMF+EM EI Onnistu");
          	
          	e.printStackTrace(response.getWriter());
          	
          	return;
        }
  
//        request.getRequestDispatcher("/login.jsp")
//        .forward(request, response);
//       
        
        String strFunc = request.getParameter("func");

        if (strFunc == null) {
        	
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            
            String loginID = request.getParameter("id");
            String loginPassword = request.getParameter("password");
            

        }
        
        strFunc = "haeEhdokas";
        
        if ("haeEhdokas".equals(strFunc)) {
        	
        	Query q  = em.createQuery("SELECT k FROM Kysymykset k");
        	
        	List<Kysymykset> kaikkiKysymykset = q.getResultList();
        	
        	request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
        	
        	request.getRequestDispatcher("/ehdokaspage.jsp")
        	.forward(request, response);
       
//            response.sendRedirect(request.getContextPath() + "/ehdokaspage.jsp");
        }
        
    }
    
//	protected void checkLogin(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		String ID = request.getParameter("id");
//		String password = request.getParameter("password");
//		
////		UserDAO userDao = new UserDAO();
//		
//		try {
//			User user = userDao.checkLogin(email, password);
//			String destPage = "login.jsp";
//			
//			if (user != null) {
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
//				destPage = "ehdokaspage.jsp";
//			} else {
//				String message = "Invalid email/password";
//				request.setAttribute("message", message);
//			}
//			
//			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//			dispatcher.forward(request, response);
//			
//		} catch (SQLException | ClassNotFoundException ex) {
//			throw new ServletException(ex);
//		}
//	}
    
    
    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
