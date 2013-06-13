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

/**
 * pre-fill database with demo data
 * 
 * @author stola1
 *
 */
public class DemoDataGenerator {

	final static String[] medis = { "Riatlin", "Morphium", "UML4ever", "Wundersalbe", "Smartis", "Placebo", "JavaChip" };

	final static String[] fnames = { "Erich", "Pierre", "Olivier", "Mario", "Jan", "Guido" };
	final static String[] lnames = { "Badertscher", "Fierz", "Büchel", "Super", "Locher", "Bucher" };
	final static String cities[] = { "Bern", "Burgdorf", "Biel", "Neverland" };

	private static EntityManager em = null;
	
	private static Patient bluber;

	private static Patient  mario, dominik, george;
	
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

	//create all neccessary patients
	private static void createPatient() {
		em.getTransaction().begin();

		bluber = new Patient();
		bluber.setFirstname("Bluberio");
		bluber.setName("Bluber");
		bluber.setLoginName("bluber");
		bluber.setLoginPassword("bluber");
		bluber.setCity(cities[0]);
		bluber.setIndependenceLevel(2);
		em.persist(bluber);
		
		mario = new Patient();
		mario.setFirstname("Mario");
		mario.setName("Super");
		mario.setLoginName("mario");
		mario.setLoginPassword("mario");
		mario.setCity(cities[1]);
		mario.setIndependenceLevel(4);
		em.persist(mario);
		
		george = new Patient();
		george.setFirstname("George");
		george.setName("Clooney");
		george.setLoginName("george");
		george.setLoginPassword("george");
		george.setCity(cities[1]);
		george.setIndependenceLevel(1);
		em.persist(george);
		
		dominik = new Patient();
		dominik.setFirstname("Dominik");
		dominik.setName("Reubi");
		dominik.setLoginName("dominik");
		dominik.setLoginPassword("dominik");
		dominik.setCity(cities[1]);
		dominik.setIndependenceLevel(2);
		em.persist(dominik);

		em.getTransaction().commit();
	}
	
	//create some diaryEntries
	private static void createDiary() {
		
		Calendar createdDate = Calendar.getInstance();
		createdDate.set(Calendar.YEAR, 2013);
		createdDate.set(Calendar.MONTH, 6);
		createdDate.set(Calendar.DAY_OF_MONTH, 9);
		
		DiaryEntry diary = new DiaryEntry();
		diary.setCreatedDate(createdDate);	
		diary.setFeeling(FeelingEnum.SCHLECHT);
		diary.setEntry("nicht so toll, heute");
		diary.setPatient(mario);
		
		em.getTransaction().begin();
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 10);
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.NAJA);
		diary.setEntry("Heute geht es mir schon besser");
		diary.setPatient(mario);
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 11);		
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.SUPER);
		diary.setEntry("Endlich wieder schönes Wetter, TOP");
		diary.setPatient(mario);
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 12);		
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.SUPER);
		diary.setEntry("Auch heute alles wieder wunderbar, TOP");
		diary.setPatient(mario);
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 10);
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.NAJA);
		diary.setEntry("Heute geht es mir schon besser");
		diary.setPatient(dominik);
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 11);		
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.SUPER);
		diary.setEntry("Endlich wieder schönes Wetter, TOP");
		diary.setPatient(dominik);
		em.persist(diary);
		
		diary = new DiaryEntry();
		createdDate.set(Calendar.DAY_OF_MONTH, 12);		
		diary.setCreatedDate(createdDate);		
		diary.setFeeling(FeelingEnum.SUPER);
		diary.setEntry("Auch heute alles wieder wunderbar, TOP");
		diary.setPatient(dominik);
		em.persist(diary);

		em.getTransaction().commit();
	}

	//create all neccessary medicines
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

	//create some medications
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
			m.setDosis(Long.valueOf(r.nextInt(100)));
			m.setDosisUnit(DosisUnitEnum.miligramm);
			m.setIntervalInHours(Long.valueOf(r.nextInt(3)));
			m.setStock(Long.valueOf(r.nextInt(100)));
			
			m.setStartDate(before);
			m.setEndDate(after);
			JPAContainer<Patient> pat = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
			
			m.setPatient(pat.getItem(pat.firstItemId()).getEntity());		

			em.persist(m);
		}

		em.getTransaction().commit();
	}
	
	//create some events
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
			ev.setPatient(bluber);

			em.getTransaction().begin();
			em.persist(ev);
			em.getTransaction().commit();
	}
}
