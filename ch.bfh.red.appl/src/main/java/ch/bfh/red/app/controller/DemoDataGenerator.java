package ch.bfh.red.app.controller;

import java.util.Calendar;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.DiaryEntry.FeelingEnum;
import ch.bfh.red.app.model.assignment.Event;
import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Medication.DosisUnitEnum;
import ch.bfh.red.app.model.assignment.Medicine;
import ch.bfh.red.app.model.profile.Patient;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class DemoDataGenerator {

	final static String[] medis = { "testRiatlin", "Morphium", "UML4ever", "Wundersalbe", "Smartis", "Placebo", "JavaChip" };

	final static String[] fnames = { "Erich", "Pierre", "Olivier", "Mario", "Jan", "Guido" };
	final static String[] lnames = { "Badertscher", "Fierz", "Büchel", "Super", "Locher", "Bucher" };
	final static String cities[] = { "Bern", "Burgdorf", "Biel", "Neverland" };

	private static EntityManager em = null;

	public static void doInitializeIfDataIsMissing(){
		JPAContainer<Patient> patients = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
		
		if(patients.size() >= 1) {
			System.out.println("######################### Patients found... continue");
			return;
		} else {
			System.out.println("######################### do load patients...");
			initDemoData();
		}
	}

//	@RolesAllowed
	public static void initDemoData() {

		em = Persistence.createEntityManagerFactory("redapp").createEntityManager();

		// Test: filling bean with the id of a patient
		createPatient();

		createMedicine();
		createMedicaiton();
		
		createEvent();

		createDiary();
	}

	private static void createPatient() {
		em.getTransaction().begin();
		Patient bluber = new Patient();
		bluber.setFirstname("Bluberio");
		bluber.setName("Bluber");
		bluber.setLoginName("bluber");
		bluber.setLoginPassword("bluber");
		bluber.setCity(cities[0]);
		bluber.setIndependenceLevel(2);
		em.persist(bluber);
		
		Patient mario = new Patient();
		mario.setFirstname("Mario");
		mario.setName("Super");
		mario.setLoginName("mario");
		mario.setLoginPassword("mario");
		mario.setCity(cities[1]);
		mario.setIndependenceLevel(4);

		em.persist(mario);
		em.getTransaction().commit();
	}
	
	private static void createDiary() {
		DiaryEntry diary = new DiaryEntry();
		
		Calendar createdDate = Calendar.getInstance();
		createdDate.set(Calendar.YEAR, 2013);
		createdDate.set(Calendar.MONTH, 6);
		createdDate.set(Calendar.DAY_OF_MONTH, 9);
		
		diary.setCreatedDate(createdDate);
		diary.setFeeling(FeelingEnum.SCHLECHT);
		diary.setEntry("testeintrag");
		JPAContainer<Patient> pat = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
		diary.setPatient(pat.getItem(pat.firstItemId()).getEntity());
		em.getTransaction().begin();
		em.persist(diary);
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
			m.setIntervalInHours(Long.valueOf(r.nextInt(3)));
			m.setStock(new Long(r.nextInt(100)));
			
			m.setStartDate(before);
			m.setEndDate(after);
		

			em.persist(m);
		}

		em.getTransaction().commit();
	}
	
	private static void createEvent() {

		Event ev = new Event();
			
			Calendar before = Calendar.getInstance();
			before.set(Calendar.YEAR, 2012);
			before.set(Calendar.MONTH, 7);
			before.set(Calendar.DAY_OF_MONTH, 20);
			
			
			Calendar after = Calendar.getInstance();
			after.set(Calendar.YEAR, 2020);
			after.set(Calendar.MONTH, 7);
			after.set(Calendar.DAY_OF_MONTH, 20);
			
			Calendar created = Calendar.getInstance();
			created.set(Calendar.YEAR, 2013);
			created.set(Calendar.MONTH, 6);
			created.set(Calendar.DAY_OF_MONTH, 9);

			ev.setCreatedDate(created);
			ev.setStartDate(before);
			ev.setEndDate(after);
			ev.setLocation("Bern");
			ev.setName("Therapiesitzung mit Dr. X");
			

			em.getTransaction().begin();
			em.persist(ev);
			em.getTransaction().commit();
	}
}
