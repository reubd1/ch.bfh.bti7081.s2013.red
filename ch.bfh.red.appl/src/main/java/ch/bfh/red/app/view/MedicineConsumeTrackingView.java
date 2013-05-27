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
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.Item;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicineConsumeTrackingView extends NavigationView implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1276328592221877100L;
	
	private Label lbTitle;
	
	private Table medConsumeTable;

	private JPAContainer<Medication> medication;

	private final Item medicationItem;
	
	public MedicineConsumeTrackingView(Item medicationItem) {
		this.medicationItem = medicationItem;	
		medication = JPAContainerFactory.make(Medication.class, JpaAddressbookUI.PERSISTENCE_UNIT);


	}
	
    public void attach() {
        super.attach();
        
            buildView();
    }

	private void buildView() {
		VerticalLayout verticalLayoutGround = new VerticalLayout();

		medConsumeTable = new Table(null, medication);
		medConsumeTable.setSelectable(true);
		medConsumeTable.setImmediate(true);

		medConsumeTable.setSizeFull();

		medConsumeTable.setVisibleColumns(new Object[] { "id", "dosis", "intervalInHours" });

		lbTitle = new Label("Consume Tracking");
		lbTitle.setImmediate(true);
		lbTitle.setSizeFull();


		verticalLayoutGround.addComponent(lbTitle);
		verticalLayoutGround.addComponent(medConsumeTable);

	}

	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}