package vaalikone;

import java.io.IOException;
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

public class loginprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String loginID = request.getParameter("id");
		String loginpassword = request.getParameter("password");
		String cryptedloginpassword = CryptMain.crypt(loginpassword);
		boolean LoginOK = false;
		List<Ehdokkaat> ehdokas = null;

		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=?1 and e.salasana=?2");
			q.setParameter(1, Integer.parseInt(loginID));
			q.setParameter(2, cryptedloginpassword);
			ehdokas = q.getResultList();

			if (ehdokas.size() > 0) {

				HttpSession session = request.getSession(true);
				Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");
				if (usr == null) {
					usr = new Kayttaja();
					session.setAttribute("usrobj", usr);
				}

				request.setAttribute("ehdokas", ehdokas);
				request.getRequestDispatcher("/ehdokaspage.jsp").forward(request, response);

				RequestDispatcher req = request.getRequestDispatcher("ehdokaspage.jsp");
				req.forward(request, response);

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