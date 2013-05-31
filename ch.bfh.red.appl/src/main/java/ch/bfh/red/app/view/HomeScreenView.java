package ch.bfh.red.app.view;

import ch.bfh.red.app.controller.DemoDataGenerator;
import ch.bfh.red.app.controller.DiaryEditor;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Start screen with menu items
 * 
 * @author barta3
 */
public class HomeScreenView extends NavigationView {

	/**
	 * generated uid
	 */
	private static final long serialVersionUID = -1942941235972403710L;

	@Override
	public void attach() {
		super.attach();
		buildView();
	}

	private void buildView() {

		this.setCaption("Home");
		
		Button butCreate = new Button();

		butCreate.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				DemoDataGenerator.create();

			}
		});
		butCreate.setIcon(new ThemeResource("linegraphics/init.png"));
		getNavigationBar().setRightComponent(butCreate);
		

		VerticalComponentGroup group = new VerticalComponentGroup();

		NavigationButton btnDiary = new NavigationButton("Tagebuch", new DiarySummaryView());
		group.addComponents(btnDiary);
		
		NavigationButton btnMedi= new NavigationButton("Medikamente", new MedicineMainView());
		group.addComponents(btnMedi);

		this.setContent(group);
	}

}
