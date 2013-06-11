/**
 * View with a list of all diary entries
 * @author barta3
 */
package ch.bfh.red.app.view;

import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.controller.LoginService;
import ch.bfh.red.app.controller.notification.NotificationChecker;
import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.profile.Patient;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class DiarySummaryView extends NavigationView {

	private static final long serialVersionUID = 1276328592221877100L;

	private Table diaryEntriesTable;

	private JPAContainer<DiaryEntry> diaryEntries;

	public DiarySummaryView() {
		diaryEntries = JPAContainerFactory.make(DiaryEntry.class, RedAppUI.PERSISTENCE_UNIT);
	}

	@Override
	public void attach() {
		super.attach();
		buildView();

		// Turn on notifications
		NotificationChecker.getInstance().setActive(true);
	}

	private void buildView() {

		this.setCaption("Tagebuch Übersicht");

		// only show diary entries of current user, if there are any entries
		if (diaryEntries != null && diaryEntries.size() > 0) {
			// load current user from session
			Patient curUser = LoginService.getInstance().getLoggedInUser(getSession());
			if (curUser != null && curUser.getId() != null) {
				// set filter to JPA Object
				Equal filter = new Compare.Equal("patient", curUser);
				diaryEntries.addContainerFilter(filter);
				diaryEntries.applyFilters();
			}
		}

		diaryEntriesTable = new Table(null, diaryEntries);
		diaryEntriesTable.setSelectable(true);
		diaryEntriesTable.setImmediate(true);

		diaryEntriesTable.setSizeFull();

		diaryEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "feeling" });

		Button addDiary = new Button();

		addDiary.addClickListener(new ClickListener() {

			/**
			 * generated UID
			 */
			private static final long serialVersionUID = -5952633399481673479L;

			@Override
			public void buttonClick(ClickEvent event) {
				getNavigationManager().navigateTo(new DiaryEditor(new BeanItem<DiaryEntry>(new DiaryEntry())));

			}
		});

		setContent(diaryEntriesTable);
		addDiary.setIcon(new ThemeResource("linegraphics/plus.png"));
		getNavigationBar().setRightComponent(addDiary);

	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();

		diaryEntries.refresh();
	}
}