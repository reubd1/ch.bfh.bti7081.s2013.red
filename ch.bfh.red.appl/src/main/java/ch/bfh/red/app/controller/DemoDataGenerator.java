package ch.bfh.red.app.controller;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Medication.DosisUnitEnum;

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

		em.getTransaction().begin();
		Random r = new Random(0);
		int i = 0;
		for (String cM : medis) {
				Medication m = new Medication();
				m.setEntry(cM);
				m.setDosisUnit(DosisUnitEnum.values()[r.nextInt(DosisUnitEnum.values().length)]);
				m.setDosis((i * i) + 1L);
				
				em.persist(m);
				i++;
		}

		em.getTransaction().commit();
	}

}
