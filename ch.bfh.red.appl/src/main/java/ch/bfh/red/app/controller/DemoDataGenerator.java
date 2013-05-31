package ch.bfh.red.app.controller;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Medication.DosisUnitEnum;
import ch.bfh.red.app.model.profile.Patient;

public class DemoDataGenerator {

	final static String[] medis = { "Riatlin", "Morphium", "UML4ever", "IT",
			"Quality Assurance", "Research and Development", "Production" };

	final static String[] fnames = { "Erich", "Pierre", "Olivier", "Mario",
			"Jan", "Guido" };
	final static String[] lnames = { "Badertscher", "Fierz", "Büchel", "Super",
			"Locher", "Bucher" };
	final static String cities[] = { "Bern", "Burgdorf", "Biel", "Neverland" };


	public static void create() {

		EntityManager em = Persistence.createEntityManagerFactory("redapp")
				.createEntityManager();

		// Test: filling bean with the id of a patient

		Patient patient = new Patient();
		patient.setFirstname("Bluberio");
		patient.setName("Bluber");
		patient.setCity("Bern");
		patient.setIndependenceLevel(2);

		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		
		
//		em.getTransaction().begin();
//		Random r = new Random(0);
//		int i = 0;
//		for (String cM : medis) {
//				Medication m = new Medication();
//				m.setEntry(cM);
//				m.setDosisUnit(DosisUnitEnum.values()[r.nextInt(DosisUnitEnum.values().length)]);
//				m.setDosis((i * i) + 1L);
//				
//				em.persist(m);
//				i++;
//		}
//
//		em.getTransaction().commit();
	}

}
