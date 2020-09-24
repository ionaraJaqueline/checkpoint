package daoImplDataBase;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAO;




public abstract class InDatabaseDAO extends DAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static EntityManagerFactory emf;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("lanchonete");
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void close() {
		emf.close();
	}

}
