package vaalikone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

public class Ehdokas {

	private String ehdokasId;
	private String sukunimi;
	private String etunimi;
	private String salasana;
	private String tunnus;
    List<Ehdokkaat> kayttaja = null;
    List<Kysymykset> kysymykset = null;
    List<Vastaukset> vastaukset = null;
    
	public Ehdokas() {
		super();
	}

	/**
	 *
	 * @param ehdokasId
	 */
	public Ehdokas(String ehdokasId) {
		this.ehdokasId = ehdokasId;
	}

	/**
	 *
	 * @return ehdokkaan id-numero
	 */
	public String getEhdokasId() {
		return ehdokasId;
	}

	/**
	 *
	 * @param ehdokasId ehdokkaan id-numero
	 */
	public void setEhdokasId(String ehdokasId) {
		this.ehdokasId = ehdokasId;
	}

	/**
	 *
	 * @return
	 */
	public String getSukunimi() {
		return sukunimi;
	}

	/**
	 *
	 * @param sukunimi
	 */
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	/**
	 *
	 * @return
	 */
	public String getEtunimi() {
		return etunimi;
	}

	/**
	 *
	 * @param etunimi
	 */
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSalasana() {
		return salasana;
	}

	/**
	 *
	 * @param etunimi
	 */
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	public String getTunnus() {
		return tunnus;
	}

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	

	public List<Ehdokkaat> haeTiedot() {
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em = emf.createEntityManager();

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.tunnus=?1");
			q.setParameter(1, tunnus);
			kayttaja = q.getResultList();
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println("Ehdokkaan tiedot haettu");
			return (List<Ehdokkaat>)kayttaja;
	}
	
	public List<Kysymykset> haeKysymykset() {
		
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em = emf.createEntityManager();

        	Query q = em.createQuery("SELECT k FROM Kysymykset k");
            List<Kysymykset> kysymykset = q.getResultList();

				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("Kaikki kysymykset haettu");
			return (List<Kysymykset>)kysymykset;
	}
	
	public List<Vastaukset> haeVastaukset() {
		
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em = emf.createEntityManager();

            Query q = em.createQuery("SELECT v FROM Vastaukset v WHERE v.vastauksetPK.tunnus=?1");
            q.setParameter(1, Integer.parseInt(tunnus));
            List<Vastaukset> vastaukset = q.getResultList();

				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("Ehdokkaan vastaukset haettu");
			return (List<Vastaukset>)vastaukset;
	}
	
	public void poista() {
		
	}
	
	public void paivita() {
		
	}
	
	public boolean validate() {

		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em = emf.createEntityManager();

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.salasana=?1 and e.tunnus=?2");

			q.setParameter(1, Crypt.crypt(salasana));
			q.setParameter(2, tunnus);
			kayttaja = q.getResultList();
			

				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (kayttaja.size() > 0) {
			System.out.println("Login onnistui");
			
			return true;
		}
		else {
			System.out.println("Login virhe");
			return false;
		}
		} 
	}
