package fr.sid.miage.dicegameCharlesMassicard;

//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;

import javafx.application.Application;

/**	
 * @author Anne-Laure CHARLES	
 * @author Louis MASSICARD (user name : louis)	
 * @version 	
 * @since %G% - %U% (%I%)	
 *	
 */
public class Launcher {

	/**	
	 * Main function.	
	 * 	
	 * @param args Arguments passed to application's Jar (here, not used).	
	 */	
	  public static void main(String[] args) {
	     
//		  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//	      EntityTransaction entityTransaction = entityManager.getTransaction();
//	      entityTransaction.begin();
//	      
//	      Usager usager= new Usager("Yacia","Adel", "2Rue ludovic beauchet", 54000, "Nancy", "0768548385", "adel.yacia@gmail.com", new Date());
//	      entityManager.persist(usager);
//	
//	      entityManager.getTransaction().commit();
//	      entityManager.close();
//	      
//	      entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//	      entityTransaction = entityManager.getTransaction();
//	      entityTransaction.begin();
//	      @SuppressWarnings("unchecked")
//	      List<Usager> rows = entityManager.createNativeQuery( "SELECT * FROM USAGER;", Usager.class ).getResultList();
//	      rows.forEach(x -> System.out.println(x.toString()));
//	      entityManager.close();
	      
	      Application.launch(App.class, args);
	  }
}