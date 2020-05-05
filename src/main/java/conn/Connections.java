package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import persist.Ehdokkaat;

public class Connections {
	
	private static DataSource pool=null;
	
	public static Connection getConnection() throws SQLException {
		if (pool!=null) {
			return pool.getConnection();
		}
		// The configuration object specifies behaviors for the connection pool.
		HikariConfig config = new HikariConfig();
		 // Configure which instance and what database user to connect with.
		config.setDriverClassName(System.getProperty("drivername")); // see appengine-web.xml
		config.setJdbcUrl("jdbc:mysql://localhost:3306/"+System.getProperty("databasename")); // see appengine-web.xml
		config.setUsername(System.getProperty("localusername")); // see appengine-web.xml
		config.setPassword(System.getProperty("localpassword")); // see appengine-web.xml
		
		  // Initialize the connection pool using the configuration object.
		pool = new HikariDataSource(config);
		  
		return pool.getConnection();
	}
	
	public static void addSQL(Ehdokkaat ehdokas) throws SQLException{
		

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(ehdokas);
			em.getTransaction().commit();

}
}