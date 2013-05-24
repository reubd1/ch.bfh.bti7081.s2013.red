/**
 * Copyright 2009-2013 Oy Vaadin Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.bfh.red.app.view;

import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.model.assignment.Diary;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

public class DiarySummaryView extends NavigationView implements ClickListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1276328592221877100L;

	private Table diaryEntriesTable;

//	private TextField searchField;

	private JPAContainer<Diary> diaries;

//	private Department departmentFilter;
	private String textFilter;


	private Item diaryItem;
	
	final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(
			new Diary());
	
	private Button addDiary = new Button(null, this);
	


	
	public DiarySummaryView(Item diaryItem) {
		this.diaryItem = diaryItem;	
		diaries = JPAContainerFactory.make(Diary.class,
				JpaAddressbookUI.PERSISTENCE_UNIT);

	}
	
	 @Override
	    public void attach() {
	        super.attach();
	        
	            buildView();
	    }

	private void buildView() {

		diaryEntriesTable = new Table(null, diaries);
		diaryEntriesTable.setSelectable(true);
		diaryEntriesTable.setImmediate(true);

		diaryEntriesTable.setSizeFull();

		// diaryEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "feeling",
		// "createdDate"});
		diaryEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "feeling" });

//		searchField = new TextField();
//		searchField.setInputPrompt("Search by name");
//		searchField.addTextChangeListener(new TextChangeListener() {
//
//			@Override
//			public void textChange(TextChangeEvent event) {
//				textFilter = event.getText();
//				updateFilters();
//			}
//		});
//
//		verticalLayout.addComponent(diaryEntriesTable);
//		verticalLayout.setExpandRatio(diaryEntriesTable, 1);
//		verticalLayout.setSizeFull();

		setContent(diaryEntriesTable);
		addDiary.setIcon(new ThemeResource("linegraphics/plus.png"));
        setRightComponent(addDiary);
	}

//	private void updateFilters() {
//		diaries.setApplyFiltersImmediately(false);
//		diaries.removeAllContainerFilters();
//		if (textFilter != null && !textFilter.equals("")) {
//			Or or = new Or(new Like("entry", textFilter + "%", false));
//			diaries.addContainerFilter(or);
//		}
//		diaries.applyFilters();
//	}



	@Override
	public void buttonClick(ClickEvent event) {
		if (addDiary == event.getButton()) {
            Popover popover = new Popover();
            popover.setSizeFull();
            popover.setModal(false);
            popover.setClosable(true);
            popover.setContent(new DiaryEditor(newDiaryItem, null));
            UI.getCurrent().addWindow(popover);
        } 
    }
}