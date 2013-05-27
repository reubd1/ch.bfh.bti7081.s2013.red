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
import ch.bfh.red.app.model.assignment.Medication;

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
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MedicineMainView extends NavigationView implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1276328592221877100L;
	
	private Label lbTitle;
	private Button bConsumeTracking;
	private Button bStockMgmt;

	private JPAContainer<Medication> medication;

	private final Item medicationItem;
	
	public MedicineMainView(Item medicationItem) {
		this.medicationItem = medicationItem;	
		medication = JPAContainerFactory.make(Medication.class, JpaAddressbookUI.PERSISTENCE_UNIT);


	}
	
    public void attach() {
        super.attach();
        
            buildView();
    }

	private void buildView() {
		VerticalLayout verticalLayoutGround = new VerticalLayout();
		VerticalLayout verticalLayoutTop = new VerticalLayout();
		VerticalLayout verticalLayoutBottom = new VerticalLayout();


		lbTitle = new Label("Medikamente");
		lbTitle.setImmediate(true);
		lbTitle.setSizeFull();

		bConsumeTracking = new Button("Einnahme Einsicht");
		
	    bStockMgmt = new Button("Vorrat Verwalten");


		verticalLayoutTop.addComponent(lbTitle);
		verticalLayoutTop.setExpandRatio(lbTitle, 1);
		verticalLayoutBottom.addComponent(bConsumeTracking);
		verticalLayoutBottom.addComponent(bStockMgmt);
		
		verticalLayoutGround.addComponent(verticalLayoutTop);
		verticalLayoutGround.addComponent(verticalLayoutBottom);

	}
	
	public void buttonClick(ClickEvent event) {
//		if (bConsumeTracking == event.getButton()) {
//            Popover popover = new Popover();
//            popover.setSizeFull();
//            popover.setModal(false);
//            popover.setClosable(true);
//            popover.setContent();
//            UI.getCurrent().addWindow(popover);
	} 
}