package ch.bfh.red.app.view;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.util.GMap;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
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
		

		final GMap map = new GMap();
		componentGroupEvent.addComponent(map);

		componentGroup.addComponent(drawFeelingChart(entryBean));
		
		content.addComponent(componentGroup);
		content.addComponent(componentGroupMedi);
		content.addComponent(componentGroupEvent);
		
		setContent(content);
         
	}
	
	/*
	 * Draw a Chart with Chart Addon for display the Feeling of all Diary Entries
	 */
	public Chart drawFeelingChart(JPAContainer<DiaryEntry> entryBean){
		

		Collection itemIds = entryBean.getItemIds();
		
		
		Chart chart = new Chart(ChartType.LINE);
		chart.setWidth("400px");
		chart.setHeight("300px");
		        
		// Modify the default configuration a bit
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Gefühlslage über alle Tage");
		conf.getLegend().setEnabled(false); // Disable legend

		// The data
		ListSeries series = new ListSeries("Date");
		
		for (Object obj : itemIds){
			series.addData(entryBean.getItem(obj).getEntity().getFeeling().getNumericValue());
		}
		
		conf.addSeries(series);

		// Set the category labels on the axis correspondingly
		XAxis xaxis = new XAxis();
		xaxis.setCategories("09.06.13", "10.06.13", "11.06.13");
		xaxis.setTitle("Datum");
		conf.addxAxis(xaxis);

		// Set the Y axis title
		YAxis yaxis = new YAxis();
		yaxis.setTitle("Gefühl");
		yaxis.setCategories("", "Schlecht", "", "Naja", "", "Super");
		conf.addyAxis(yaxis);
		
		return chart;
		
	}
	
	/*
	 * Method to get today's Diary Entry
	 */
	public JPAContainer<DiaryEntry> getDiaryEntry() {
		
		
		JPAContainer<DiaryEntry> diaryEntries = new JPAContainer<DiaryEntry>(DiaryEntry.class);
		
		diaryEntries = JPAContainerFactory.make(DiaryEntry.class, RedAppUI.PERSISTENCE_UNIT);


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
