package vaalikone;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persist.Ehdokkaat;
import vaalikone.Kayttaja;

public class loginprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String loginID = request.getParameter("id");
		String loginpassword = request.getParameter("password");
		String cryptedloginpassword = Crypt(loginpassword);
		List<Ehdokkaat> ehdokas = null;
		String username = request.getParameter("username");
		int userid;
	//	String kayttajatunnus = request.getParameter("username");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.kayttajatunnus=?1 and e.salasana=?2"); //e.ehdokasId=?1
			// q.setParameter(1, Integer.parseInt(loginID));
			q.setParameter(2, cryptedloginpassword);
			q.setParameter(1, username);
			ehdokas = q.getResultList();
			
			if (ehdokas.size() > 0) {

				HttpSession session = request.getSession(true);
				Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");
				if (usr == null) {
					usr = new Kayttaja();
					
				}
				
                for (Ehdokkaat loginehdokas : ehdokas) {
                	username = loginehdokas.getKayttajatunnus();
                	//userid = loginehdokas.getEhdokasId();
                
                	
                	usr.setusername(username);
                //	usr.setuserid(userid);
                	String func = "Ehdokas";
                	session.setAttribute("usrobj", usr);
                	session.setAttribute("func", func);
                	session.setAttribute("loginname", username);

                }
				
                request.setAttribute("user", usr);
			// request.setAttribute("func", "Ehdokas");
				request.getRequestDispatcher("/PoistaMuokkaa").forward(request, response);
				
				// RequestDispatcher req = request.getRequestDispatcher("/PoistaMuokkaa");
				// req.forward(request, response);

				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();

			} else {
				request.getRequestDispatcher("/loginerror.jsp").forward(request, response);
				System.out.println("0 results returned");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String Crypt(String str) {
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
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