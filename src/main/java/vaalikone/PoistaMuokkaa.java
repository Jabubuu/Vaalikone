package vaalikone;

import java.io.IOException;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.utils.SystemProperty;

import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

public class PoistaMuokkaa extends HttpServlet {
	
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

        // hae http-sessio ja luo uusi jos vanhaa ei ole viel√§ olemassa
        HttpSession session = request.getSession(true);

        //hae k√§ytt√§j√§-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        //jos k√§ytt√§j√§-oliota ei l√∂ydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new Kayttaja();
            logger.log(Level.FINE, "Luotu uusi k‰ytt‰j‰olio");
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
        
        String strFunc = request.getParameter("func");
        String Poista = request.getParameter("Poista");
        
        if (strFunc.equals("Ehdokas")){
        	
        	if (Poista != null){
        		em.getTransaction().begin();
        		int id = Integer.parseInt(Poista);
//        		Query q = em.createQuery("UPDATE vaalikone.vastaukset SET VASTAUS = 1 WHERE EHDOKAS_ID = 1 AND KYSYMYS_ID = 1");
        		Query q = em.createQuery("SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1 AND v.vastauksetPK.kysymysId=?2");
        		q.setParameter(1, 1);//ehdokas param
        		q.setParameter(2, id);//Kysymyksen param
        		List<Vastaukset> Poistettu = q.getResultList();
        		Poistettu.get(0).setVastaus(null);
        		Poistettu.get(0).setKommentti("Ei kommenttia");
        		em.getTransaction().commit();
        	}
        	
        	Query q = em.createQuery(
                    "SELECT k FROM Kysymykset k");
            List<Kysymykset> kaikkiKysymykset = q.getResultList();
            
            q = em.createQuery(
            		"SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1");
            q.setParameter(1, 1); //t‰m‰ on t‰‰ll‰ jotta voidaan muuttujalla sitten hakea oikea ehdokas
            List<Vastaukset> KayttajanVastaukset = q.getResultList();
            
            request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
            request.setAttribute("KayttajanVastaukset", KayttajanVastaukset);
            
        	request.getRequestDispatcher("/EditRemove.jsp")
            .forward(request, response);
        }
    }
    
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
    
    public static int par(String num) {
    	int nm = Integer.parseInt(num);
    	return nm;
    }

}


