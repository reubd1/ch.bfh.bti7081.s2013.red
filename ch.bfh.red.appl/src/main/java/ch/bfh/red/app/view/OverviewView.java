package ch.bfh.red.app.view;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.model.assignment.Event;


import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.GreaterOrEqual;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
/**
 * Main View for today's Assignments
 * 
 * @author reubd1
 */
public class OverviewView extends NavigationView {
	

	Calendar today;
	Date todayDate;
	GreaterOrEqual filter;
	
	public void attach() {
		super.attach();

		buildView();
	}

	private void buildView() {
		
		//get today's date
		today = Calendar.getInstance();
		today.set(Calendar.AM_PM, Calendar.AM);
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		todayDate = today.getTime();
		
		java.sql.Timestamp t = new Timestamp(todayDate.getTime());
		//create a Filter for todays Assignments
		filter = new Compare.GreaterOrEqual("createdDate", t);
		
		CssLayout content = new CssLayout();
		content.setWidth("100%");

		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		VerticalComponentGroup componentGroupMedi = new VerticalComponentGroup();
		VerticalComponentGroup componentGroupEvent = new VerticalComponentGroup();
		this.setCaption("Tagesübersicht");
		
		
		//get today's assignments
		JPAContainer<DiaryEntry> entryBean = getDiaryEntry();
		JPAContainer<Medication> medicationEntryBean = getMedicineEntry();
		JPAContainer<ch.bfh.red.app.model.assignment.Event> eventEntrytBean = getEventEntry();
		
		DiaryEntry entry = entryBean.getItem(entryBean.firstItemId()).getEntity();
		Medication medi = medicationEntryBean.getItem(medicationEntryBean.firstItemId()).getEntity();
		ch.bfh.red.app.model.assignment.Event ev = eventEntrytBean.getItem(eventEntrytBean.firstItemId()).getEntity();
        
		Label diaryLabel = new Label(
				"<div style='color:#333;'><p><b>Heutiger Tagebucheintrag:</b> "+entry.getEntry()+"</p> <br></div>",
				Label.CONTENT_XHTML);	
		
		Label mediLabel = new Label( 
				"<div style='color:#333;'><p><b>Medication: </b> <br> "+medi.getMedicine().getName()+" - "+medi.getDosis()+", "+medi.getDosisUnit()+"</p> </div>",
				Label.CONTENT_XHTML);
		
		Label eventLabel = new Label( 
				"<div style='color:#333;'><p><b>Termin heute: </b> <br> "+ev.getName()+"</p> </div>",
				Label.CONTENT_XHTML);
		
		componentGroup.addComponent(diaryLabel);
		componentGroupMedi.addComponent(mediLabel);
		componentGroupEvent.addComponent(eventLabel);

		content.addComponent(componentGroup);
		content.addComponent(componentGroupMedi);
		content.addComponent(componentGroupEvent);
		
		setContent(content);
         
	}
	
	/*
	 * Method to get today's Diary Entry
	 */
	public JPAContainer<DiaryEntry> getDiaryEntry() {
		
		
		JPAContainer<DiaryEntry> diaryEntries = new JPAContainer<DiaryEntry>(DiaryEntry.class);
		
		diaryEntries = JPAContainerFactory.make(DiaryEntry.class, RedAppUI.PERSISTENCE_UNIT);

		diaryEntries.addContainerFilter(filter);

		return diaryEntries;

	}
	
	/*
	 * Method to get today's Medicine Entry
	 */
	public JPAContainer<Medication> getMedicineEntry() {
		JPAContainer<Medication> medicationEntries = new JPAContainer<Medication>(Medication.class);
		
		medicationEntries = JPAContainerFactory.make(Medication.class, RedAppUI.PERSISTENCE_UNIT);
		
		medicationEntries.addContainerFilter(filter);

		return medicationEntries;

	}
	
	/*
	 * Method to get today's Event Entry
	 */
	public JPAContainer<ch.bfh.red.app.model.assignment.Event> getEventEntry() {
		JPAContainer<ch.bfh.red.app.model.assignment.Event> eventEntries = new JPAContainer<ch.bfh.red.app.model.assignment.Event>(ch.bfh.red.app.model.assignment.Event.class);
		
		eventEntries = JPAContainerFactory.make(ch.bfh.red.app.model.assignment.Event.class, RedAppUI.PERSISTENCE_UNIT);

		eventEntries.addContainerFilter(filter);

		return eventEntries;

	}

}
