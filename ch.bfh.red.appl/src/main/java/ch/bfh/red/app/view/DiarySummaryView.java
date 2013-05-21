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

import ch.bfh.red.app.HierarchicalDepartmentContainer;
import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.controller.PersonEditor;
import ch.bfh.red.app.controller.PersonEditor.EditorSavedEvent;
import ch.bfh.red.app.controller.PersonEditor.EditorSavedListener;
import ch.bfh.red.app.model.assignment.Department;
import ch.bfh.red.app.model.assignment.Diary;
import ch.bfh.red.app.model.assignment.Person;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DiarySummaryView extends HorizontalSplitPanel implements
		ComponentContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1276328592221877100L;

	private Table diaryEntriesTable;

	private TextField searchField;

	private JPAContainer<Diary> diaries;

	private Department departmentFilter;
	private String textFilter;

	private final Item diaryItem;
	
	public DiarySummaryView(Item diaryItem) {
		this.diaryItem = diaryItem;	
		diaries = JPAContainerFactory.make(Diary.class,
				JpaAddressbookUI.PERSISTENCE_UNIT);
		buildMainArea();

		setSplitPosition(0);
	}

	private void buildMainArea() {
		VerticalLayout verticalLayout = new VerticalLayout();
		setSecondComponent(verticalLayout);

		diaryEntriesTable = new Table(null, diaries);
		diaryEntriesTable.setSelectable(true);
		diaryEntriesTable.setImmediate(true);


		diaryEntriesTable.setSizeFull();
		// personTable.setSelectable(true);
		diaryEntriesTable.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					diaryEntriesTable.select(event.getItemId());
				}
			}
		});

		diaryEntriesTable.setVisibleColumns(new Object[] { "id", "entry", "feeling"});


		searchField = new TextField();
		searchField.setInputPrompt("Search by name");
		searchField.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				textFilter = event.getText();
				updateFilters();
			}
		});

		verticalLayout.addComponent(diaryEntriesTable);
		verticalLayout.setExpandRatio(diaryEntriesTable, 1);
		verticalLayout.setSizeFull();

	}


	private void updateFilters() {
		diaries.setApplyFiltersImmediately(false);
		diaries.removeAllContainerFilters();
		if (textFilter != null && !textFilter.equals("")) {
			Or or = new Or(new Like("entry", textFilter + "%", false));
			diaries.addContainerFilter(or);
		}
		diaries.applyFilters();
	}
}