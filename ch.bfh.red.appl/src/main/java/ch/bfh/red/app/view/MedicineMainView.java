package ch.bfh.red.app.view;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

/**
 * Shows a List of medications which the patient has to take <br>
 * The user is able to record his medication consumation
 * 
 * @author stola
 */
public class MedicineMainView extends NavigationView {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 1276328592221877100L;

	private static final String ALREADY_TAKEN_TXT = "bereits eingenommen";
	private static final String NOW_TAKEN_TXT = "jetzt eingenommen";

	private JPAContainer<Medication> medication;

	private Table mediEntriesTable;

	public MedicineMainView() {
		medication = JPAContainerFactory.make(Medication.class, RedAppUI.PERSISTENCE_UNIT);
	}

	public void attach() {
		super.attach();

		buildView();
	}

	private void buildView() {
		this.setCaption("Medikamente");

		mediEntriesTable = new Table();
		mediEntriesTable.setSelectable(true);
		mediEntriesTable.setImmediate(true);
		mediEntriesTable.setSizeFull();

		mediEntriesTable.addContainerProperty("Name", String.class, null);
		mediEntriesTable.addContainerProperty("Dosis", String.class, null);
		mediEntriesTable.addContainerProperty("Lager", Long.class, null);
		mediEntriesTable.addContainerProperty("Interval", Long.class, null);
		mediEntriesTable.addContainerProperty("Eingenommen", Button.class, null);

		for (Object oid : medication.getItemIds()) {
			Long id = (Long) oid;

			// load from DB
			EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();
			Medication curMed = em.find(Medication.class, id);

			final Button isTaken = new Button();
			isTaken.setData(oid);
			isTaken.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					if (!event.getButton().isEnabled()) {
						return;
					}

					isTaken.setCaption(ALREADY_TAKEN_TXT);
					isTaken.setEnabled(false);

					// Update last Intake Date of Medication
					EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();
					Medication clickedMedi = em.find(Medication.class, event.getButton().getData());

					if (clickedMedi == null) {
						return;
					}
					clickedMedi.setLastIntake(Calendar.getInstance());

					em.getTransaction().begin();
					em.persist(clickedMedi);
					em.getTransaction().commit();
				}
			});

			if (curMed.isTimeForNextIntakeNow()) {
				isTaken.setCaption(NOW_TAKEN_TXT);
			} else {
				isTaken.setCaption(ALREADY_TAKEN_TXT);
				isTaken.setEnabled(false);
			}

			// prepare table row
			ArrayList<Object> tableRow = new ArrayList<Object>();
			tableRow.add(curMed.getMedicine().getName());
			tableRow.add(curMed.getDosis() + " " + curMed.getDosisUnit());
			tableRow.add(curMed.getIntervalInHours());
			tableRow.add(curMed.getStock());

			tableRow.add(isTaken);

			mediEntriesTable.addItem(tableRow.toArray(), null);
		}
		
		setContent(mediEntriesTable);
	}
}