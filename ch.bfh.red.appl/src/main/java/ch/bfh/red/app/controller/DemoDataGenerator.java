package ch.bfh.red.app.controller;

import java.util.Calendar;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Medication.DosisUnitEnum;
import ch.bfh.red.app.model.assignment.Medicine;
import ch.bfh.red.app.model.profile.Patient;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class DemoDataGenerator {

	final static String[] medis = { "Riatlin", "Morphium", "UML4ever", "Wundersalbe", "Smartis", "Placebo", "JavaChip" };

	final static String[] fnames = { "Erich", "Pierre", "Olivier", "Mario", "Jan", "Guido" };
	final static String[] lnames = { "Badertscher", "Fierz", "Büchel", "Super", "Locher", "Bucher" };
	final static String cities[] = { "Bern", "Burgdorf", "Biel", "Neverland" };

	private static EntityManager em = null;

//	@RolesAllowed
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
		patient.setCity(cities[0]);
		patient.setIndependenceLevel(2);

		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}

	private static void createMedicine() {
		em.getTransaction().begin();
		for (String cM : medis) {
			Medicine m = new Medicine();

			m.setName(cM);
			m.setDescription(cM + " Beschreibung");

			em.persist(m);
		}

		em.getTransaction().commit();
	}

	private static void createMedicaiton() {
		em.getTransaction().begin();

		JPAContainer<Medicine> medicine = JPAContainerFactory.make(Medicine.class, RedAppUI.PERSISTENCE_UNIT);

		Medication m = null;

		Random r = new Random(0);
		r.nextBoolean();

		for (Object oid : medicine.getItemIds()) {
			if (r.nextBoolean()) {
				// skip, every second...
				continue;
			}
			m = new Medication();
			
			Calendar before = Calendar.getInstance();
			before.set(Calendar.YEAR, 2012);
			before.set(Calendar.MONTH, 7);
			before.set(Calendar.DAY_OF_MONTH, r.nextInt(27)+1);
			
			
			Calendar after = Calendar.getInstance();
			after.set(Calendar.YEAR, 2020);
			after.set(Calendar.MONTH, 7);
			after.set(Calendar.DAY_OF_MONTH, r.nextInt(27)+1);

			Long id = (Long) oid;

			// load from DB
			Medicine curMed = em.find(Medicine.class, id);
			m.setMedicine(curMed);
			m.setDosis(new Long(r.nextInt(100)));
			m.setDosisUnit(DosisUnitEnum.miligramm);
			m.setIntervalInHours(new Long(r.nextInt(3)));
			m.setStock(new Long(r.nextInt(100)));
			
			m.setStartDate(before);
			m.setEndDate(after);
		

			em.persist(m);
		}

		em.getTransaction().commit();
	}
}
