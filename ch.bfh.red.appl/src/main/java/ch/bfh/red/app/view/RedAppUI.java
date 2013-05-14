/**
 * 
 */
package ch.bfh.red.app.view;

/**
 * @author team red
 *
 */

import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.controller.DiaryEditor.EditorSavedEvent;
import ch.bfh.red.app.controller.DiaryEditor.EditorSavedListener;
import ch.bfh.red.app.model.assignment.Diary;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RedAppUI extends UI {

	private JPAContainer<Diary> diaries;

	private static final long serialVersionUID = -3919212212063135503L;

	@Override
	protected void init(VaadinRequest request) {

		diaries = JPAContainerFactory.make(Diary.class,
				JpaAddressbookUI.PERSISTENCE_UNIT);
		
		// Set the window or tab title
		getPage().setTitle("Welcome RedApp");

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		
		final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(new Diary());
		
		DiaryEditor diaryEditor = new DiaryEditor(newDiaryItem);
		diaryEditor.setWidth(800, Unit.PIXELS);
		diaryEditor.setHeight(500, Unit.PIXELS);
		


		diaryEditor.addListener(new EditorSavedListener() {
			@Override
			public void editorSaved(EditorSavedEvent event) {
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				diaries.addEntity(newDiaryItem.getBean());

			}
		});

		layout.addComponent(diaryEditor);

	}
}