package ch.bfh.red.app.controller.notification;

/**
 * Notification Checker implemented as singleton
 * 
 * @author barta3
 */
import java.text.MessageFormat;
import java.util.Calendar;

import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.Medication;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class NotificationChecker {

	/**
	 * Check every 30 seconds
	 */
	private static final int REFRESH_INTERVAL_MS = 30000;

	private static NotificationChecker instance;

	private UI mainPage;

	private JPAContainer<DiaryEntry> diaryEntries;

	private JPAContainer<Medication> medications;

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

		diaryEntries = JPAContainerFactory.make(DiaryEntry.class, RedAppUI.PERSISTENCE_UNIT);
		medications = JPAContainerFactory.make(Medication.class, RedAppUI.PERSISTENCE_UNIT);

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

		private void showMessage(final String message) {
			mainPage.access(new Runnable() {

				@Override
				public void run() {
					Notification notification = new Notification(message);
					notification.setHtmlContentAllowed(true);
					notification.show(mainPage.getPage());
				}
			});
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

			Greater todayFilter = new Compare.Greater("createdDate", today);

			diaryEntries.addContainerFilter(todayFilter);
			diaryEntries.applyFilters();

			if (diaryEntries.size() < 1) {
				showMessage("Tagebuch führen!");
			}

			diaryEntries.removeAllContainerFilters();
		}

		private void checkMedication() {

			String medisToTake = "";

			for (Object oId : medications.getItemIds()) {
				Medication medication = medications.getItem(oId).getEntity();

				if (medication.isTimeForNextIntakeNow()) {

					medisToTake += MessageFormat.format("{0} {1} {2} <br>", medication.getDosis(), medication.getDosisUnit()
							.getNumericValue(), medication.getMedicine().getName());

				}
			}
			if (!medisToTake.isEmpty()) {
				showMessage("Folgende Medikamente jetzt einnehmen: <br> " + medisToTake);
			}
		}

	}
}
