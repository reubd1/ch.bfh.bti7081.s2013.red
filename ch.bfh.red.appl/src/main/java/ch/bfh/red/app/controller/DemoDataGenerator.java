package ch.bfh.red.app.controller;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Medication.DosisUnitEnum;
import ch.bfh.red.app.model.assignment.Medicine;
import ch.bfh.red.app.model.profile.Patient;

public class DemoDataGenerator {

	final static String[] medis = { "Riatlin", "Morphium", "UML4ever", "IT", "Quality Assurance", "Research and Development",
			"Production" };

	final static String[] fnames = { "Erich", "Pierre", "Olivier", "Mario", "Jan", "Guido" };
	final static String[] lnames = { "Badertscher", "Fierz", "Büchel", "Super", "Locher", "Bucher" };
	final static String cities[] = { "Bern", "Burgdorf", "Biel", "Neverland" };

	private static EntityManager em = null;

	public static void initDemoData() {

		em = Persistence.createEntityManagerFactory("redapp").createEntityManager();

		// Test: filling bean with the id of a patient
		createPatient();

		createMedicine();
		createMedicaiton();

	}

	private static void createPatient() {
		Patient patient = new Patient();
		patient.setFirstname("Bluberio");
		patient.setName("Bluber");
		patient.setCity("Bern");
		patient.setIndependenceLevel(2);

		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}

	private static void createMedicine() {
		em.getTransaction().begin();
		Random r = new Random(0);
		for (String cM : medis) {
			// Medicine
			Medicine m = new Medicine();

			m.setName(cM);
			m.setDescription(cM + " Beschreibung");

			em.persist(m);
		}

		em.getTransaction().commit();

	}

	private static void createMedicaiton() {
		em.getTransaction().begin();

		Medication m = new Medication();

		Medicine medicine = em.find(Medicine.class, new Long(2));

		m.setMedicine(medicine);
		m.setDosis(10L);
		m.setDosisUnit(DosisUnitEnum.miligramm);
		m.setIntervalInHours(6L);
		m.setStock(10L);

		em.persist(m);
		
		em.getTransaction().commit();
	}
}
