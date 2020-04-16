package vaalikone;

import javax.persistence.*;
import persist.Ehdokkaat;

public class CheckLogin {

	public static void main(String[] args) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");  
        EntityManager em=emf.createEntityManager();
        
        Ehdokkaat e = em.find(Ehdokkaat.class, 5);
        
        return;
        
	}

}
