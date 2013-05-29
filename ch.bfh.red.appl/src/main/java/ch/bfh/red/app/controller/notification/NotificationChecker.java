package ch.bfh.red.app.controller.notification;

/**
 * Notification Checker implemented as singleton
 * 
 * @author barta3
 */
import java.util.Calendar;

import ch.bfh.red.app.model.assignment.Diary;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class NotificationChecker {

	/**
	 * Check every 10 seconds
	 */
	private static final int REFRESH_INTERVAL_MS = 10000;

	private static NotificationChecker instance;

	private UI mainPage;

	private JPAContainer<Diary> diaries;

	private boolean active = false;

	public static NotificationChecker getInstance() {

		if (instance == null) {
			instance = new NotificationChecker();
		}
		return instance;
	}

	private NotificationChecker() {
	}

	public void setMainPage(UI mainPage) {
		this.mainPage = mainPage;
	}

	/**
	 * Start the Checker
	 */
	public void start() {
		
		active = true;

		if (mainPage == null) {
			throw new IllegalStateException("mainPage is not specified");
		}

		// TODO: data access
		diaries = JPAContainerFactory.makeNonCached(Diary.class, RedAppUI.PERSISTENCE_UNIT);

		new CheckerThread().start();
	}
	
	
	/**
	 * if false, don't send push messages
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	private class CheckerThread extends Thread {

		@Override
		public void run() {
			while (active) {

				checkDiary();
				checkMedication();
				
				timeOut();
			
			}
			timeOut();
			run();
		}
		
		private void timeOut() {
			try {
				Thread.sleep(REFRESH_INTERVAL_MS);
			} catch (InterruptedException e) {
			}
		}

		/**
		 * Check if there is an diary entry for today
		 */
		private void checkDiary() {
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.set(Calendar.MILLISECOND, 0);

			Greater todayFilter = new Compare.Greater("dateTime", today);

			diaries.addContainerFilter(todayFilter);
			diaries.applyFilters();

			if (diaries.size() < 1) {
				mainPage.access(new Runnable() {

					@Override
					public void run() {
						new Notification("Tagebuch führen!").show(mainPage.getPage());
					}
				});
			}

			diaries.removeAllContainerFilters();
		}

		private void checkMedication() {
			//TODO

		}

	}
}
