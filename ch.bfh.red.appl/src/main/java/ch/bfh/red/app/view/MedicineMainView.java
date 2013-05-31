package ch.bfh.red.app.view;

import ch.bfh.red.app.model.assignment.Medication;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicineMainView extends NavigationView implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1276328592221877100L;
	
	private Label lbTitle;
	private Button bConsumeTracking;
	private Button bStockMgmt;

	private JPAContainer<Medication> medication;
	
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
		
		VerticalLayout verticalLayoutGround = new VerticalLayout();
		VerticalLayout verticalLayoutTop = new VerticalLayout();
		VerticalLayout verticalLayoutBottom = new VerticalLayout();


		mediEntriesTable = new Table(null, medication);
		mediEntriesTable.setSelectable(true);
		mediEntriesTable.setImmediate(true);

		mediEntriesTable.setSizeFull();

		mediEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "dosis" });

		
		setContent(mediEntriesTable);
		

		bConsumeTracking = new Button("Einnahme Einsicht");
		
	    bStockMgmt = new Button("Vorrat Verwalten");

	}
	
	public void buttonClick(ClickEvent event) {
//		if (bConsumeTracking == event.getButton()) {
//            Popover popover = new Popover();
//            popover.setSizeFull();
//            popover.setModal(false);
//            popover.setClosable(true);
//            popover.setContent();
//            UI.getCurrent().addWindow(popover);
	} 
}