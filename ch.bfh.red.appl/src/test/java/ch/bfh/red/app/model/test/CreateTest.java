package ch.bfh.red.app.model.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.bfh.red.app.model.profile.Patient;


public class CreateTest {

	@Test
	public void test() {
		
		EntityManager em = Persistence.createEntityManagerFactory(
				"redapp").createEntityManager();
	
		Patient patient = new Patient();
		patient.setFirstname("Bluberio");
		patient.setName("Bluber");
		patient.setIndependenceLevel(2);
	
//		ArrayList<Task> tasks = new ArrayList<Task>();
//		tasks.add(task1);
//		tasks.add(task2);
//		user.setTasks(tasks);


		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();	

		Patient patient2 = em.find(Patient.class, new Long(1));
		System.out.println(patient2.getId());
	}

}
