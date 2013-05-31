package ch.bfh.red.app.view;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.assignment.Medication;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

/**
 * @author stola
 */
public class MedicineMainView extends NavigationView implements ClickListener {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 1276328592221877100L;

	private JPAContainer<Medication> medication;

	BeanItemContainer<Medication> beans = new BeanItemContainer<Medication>(Medication.class);

	private Table mediEntriesTable;

	final BeanItem<Medication> newMedicaionItem = new BeanItem<Medication>(new Medication());

	public MedicineMainView() {
		medication = JPAContainerFactory.make(Medication.class, RedAppUI.PERSISTENCE_UNIT);
	}

	public void attach() {
		super.attach();

		buildView();
	}

	private void buildView() {
		this.setCaption("Medikamente");

		// mediEntriesTable = new Table(null, medication);
		mediEntriesTable = new Table();
		mediEntriesTable.setSelectable(true);
		mediEntriesTable.setImmediate(true);

		mediEntriesTable.setSizeFull();

		EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();
		mediEntriesTable.addContainerProperty("Name", String.class, null);
		mediEntriesTable.addContainerProperty("Dosis", String.class, null);
		mediEntriesTable.addContainerProperty("Lager", Long.class, null);
		mediEntriesTable.addContainerProperty("Interval", Long.class, null);
		mediEntriesTable.addContainerProperty("Eingenommen", CheckBox.class, null);
		//
		int j = 0;
		for (Object oid : medication.getItemIds()) {
			Long id = (Long) oid;

			// load from DB
			Medication curMed = em.find(Medication.class, id);

			boolean isTimeToTakeMed = curMed.isTimeForNextIntakeNow();
			CheckBox isTaken = null;
			if (isTimeToTakeMed) {
				isTaken = new CheckBox("jetzt geschluckt");
				// isTaken.setValue(curMed.isTimeForNextIntakeNow());
			} else {
				isTaken = new CheckBox("bereits geschluckt");
				isTaken.setValue(true);
				isTaken.setEnabled(false);
			}

			// prepare table row
			ArrayList<Object> tableRow = new ArrayList<Object>();
			tableRow.add(curMed.getMedicine().getName());
			tableRow.add(curMed.getDosis() + " " + curMed.getDosisUnit());
			tableRow.add(curMed.getIntervalInHours());
			tableRow.add(curMed.getStock());

			tableRow.add(isTaken);

			mediEntriesTable.addItem(tableRow.toArray(), new Integer(j++));
		}

		setContent(mediEntriesTable);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}
}