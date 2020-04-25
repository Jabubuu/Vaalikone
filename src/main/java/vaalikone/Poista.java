package vaalikone;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persist.Vastaukset;

public class Poista extends HttpServlet {
	
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
    	
    	int eID = Integer.parseInt(request.getParameter("ehdokasId"));
        Ehdokas ehdokas = new Ehdokas();
        ehdokas.setEhdokasId(Integer.toString(eID)); 
        
        String PoistaID = request.getParameter("id");// Kysymyksen ID

        	if ((PoistaID != null) && (Integer.parseInt(PoistaID)) < 20){
        		
                try {
                    EntityManagerFactory  emf=Persistence.createEntityManagerFactory("vaalikones");
                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();
            		int id = Integer.parseInt(PoistaID);
            		Query q = em.createQuery("SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1 AND v.vastauksetPK.kysymysId=?2");
            		q.setParameter(1, eID);//ehdokas param
            		q.setParameter(2, id);//Kysymyksen param
            		List<Vastaukset> Poistettu = q.getResultList();
            		Poistettu.get(0).setVastaus(null);
            		Poistettu.get(0).setKommentti("Ei kommenttia");
            		em.getTransaction().commit();
                    
            		
    				if (em.getTransaction().isActive()) {
    					em.getTransaction().rollback();
    				}
    				em.close();
                  }
                  catch(Exception e) {
                	  e.printStackTrace();
                  }
                System.out.println("Kysymys id: " + PoistaID + " poistettu");
            		request.getRequestDispatcher("home.jsp").forward(request, response);

        	}	else {
        		System.out.println("Tyhja tai vaara ID");
        		request.getRequestDispatcher("EditError.jsp").forward(request, response);
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