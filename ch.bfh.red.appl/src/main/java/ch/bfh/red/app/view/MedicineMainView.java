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

import ch.bfh.red.app.model.assignment.Medication;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class MedicineMainView extends VerticalSplitPanel implements
		ComponentContainer {

	/**
	 * 
	 */

	private Label lbTitle;
	private Button bConsumeTracking;
	private Button bStockMgmt;

	private JPAContainer<Medication> medication;

	private final Item medicationItem;
	
	public MedicineMainView(Item medicationItem) {
		this.medicationItem = medicationItem;	
		medication = JPAContainerFactory.make(Medication.class,
				JpaAddressbookUI.PERSISTENCE_UNIT);
		buildMainArea();

		setSplitPosition(30);
	}

	private void buildMainArea() {
		VerticalLayout verticalLayoutTop = new VerticalLayout();
		VerticalLayout verticalLayoutBottom = new VerticalLayout();
		setFirstComponent(verticalLayoutTop);
		setSecondComponent(verticalLayoutBottom);

		lbTitle = new Label("Medikamente");
		lbTitle.setImmediate(true);
		lbTitle.setSizeFull();

		bConsumeTracking = new Button("Einnahme Einsicht");
	    bConsumeTracking.addClickListener(new Button.ClickListener() {

	            public void buttonClick(ClickEvent event) {

//	                UI.getCurrent().setNavigator(Navigator)
	            }
	    });
		
	    bStockMgmt = new Button("Vorrat Verwalten");
	    bStockMgmt.addClickListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {

//                  UI.getCurrent().setNavigator(Navigator)
                }
        });

		verticalLayoutTop.addComponent(lbTitle);
		verticalLayoutTop.setExpandRatio(lbTitle, 1);
		verticalLayoutBottom.addComponent(bConsumeTracking);
		verticalLayoutBottom.addComponent(bStockMgmt);

	}
}