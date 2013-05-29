/**
 * View with a list of all diary entries
 * @author barta3
 */
package ch.bfh.red.app.view;

import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.controller.notification.NotificationChecker;
import ch.bfh.red.app.model.assignment.Diary;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class DiarySummaryView extends NavigationView {

	private static final long serialVersionUID = 1276328592221877100L;

	private Table diaryEntriesTable;

	private JPAContainer<Diary> diaries;

	final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(new Diary());

	public DiarySummaryView() {
		diaries = JPAContainerFactory.make(Diary.class, RedAppUI.PERSISTENCE_UNIT);
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

		diaryEntriesTable = new Table(null, diaries);
		diaryEntriesTable.setSelectable(true);
		diaryEntriesTable.setImmediate(true);

		diaryEntriesTable.setSizeFull();

		diaryEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "feeling" });

		Button addDiary = new Button();

		addDiary.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getNavigationManager().navigateTo(new DiaryEditor(newDiaryItem));

			}
		});

		setContent(diaryEntriesTable);
		addDiary.setIcon(new ThemeResource("linegraphics/plus.png"));
		getNavigationBar().setRightComponent(addDiary);

	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		diaries.refresh();
	}
}