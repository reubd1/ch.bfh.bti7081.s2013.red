/**
 * 
 */
package ch.bfh.red.app.view;

/**
 * @author team #F00
 *
 */

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Widgetset("ch.bfh.red.app.widgetset.RedappWidgetset")
@Title("My RedApp")
@Theme("redapp")
public class RedAppUI extends UI {

//	private JPAContainer<Diary> diaries;
	
	private MainTabsheet mainTab;

	private static final long serialVersionUID = -3919212212063135503L;

	@Override
	protected void init(VaadinRequest request) {
		// Set the window or tab title
		getPage().setTitle("Welcome RedApp");
		

		mainTab = new MainTabsheet();
		setContent(mainTab);
		

//		diaries = JPAContainerFactory.make(Diary.class,
//				JpaAddressbookUI.PERSISTENCE_UNIT);
//
//		// Set the window or tab title
//		getPage().setTitle("Welcome RedApp");
//
//		final VerticalLayout layout = new VerticalLayout();
//		layout.setMargin(true);
//		setContent(layout);
//
//		final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(new Diary());
//
//		DiaryEditor diaryEditor = new DiaryEditor(newDiaryItem);
//		diaryEditor.setWidth(800, Unit.PIXELS);
//		diaryEditor.setHeight(500, Unit.PIXELS);
//
//		diaryEditor.addListener(new EditorSavedListener() {
//			private static final long serialVersionUID = -4810596568407523252L;
//
//			@Override
//			public void editorSaved(EditorSavedEvent event) {
//				// get and set current DateTime
//				newDiaryItem.getBean().setDateTime(Calendar.getInstance());
//
//				// write some "logs"
//				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//				System.out.println("------------" + newDiaryItem.getBean().getEntry());
//				System.out.println("------------" + newDiaryItem.getBean().getFeeling());
//
//				diaries.addEntity(newDiaryItem.getBean());
//
//			}
//		});
//
//		layout.addComponent(diaryEditor);
//		
//		DiarySummaryView diarySummaryView = new DiarySummaryView(newDiaryItem);
//		diarySummaryView.setWidth(800, Unit.PIXELS);
//		diarySummaryView.setHeight(500, Unit.PIXELS);
//		layout.addComponent(diarySummaryView);

	}
}