package ch.bfh.red.app.view;

import java.util.logging.Logger;

import ch.bfh.red.app.controller.DemoDataGenerator;
import ch.bfh.red.app.controller.LoginService;

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

	private static final Logger LOGGER = Logger.getLogger(HomeScreenView.class.getName());

	LoginService loginService = LoginService.getInstance();

	@Override
	public void attach() {
		super.attach();

		// Check if a user has logged in
		if (!loginService.isLoggedIn()) {
			// Redirect to login view always if a user has not yet logged in
			LOGGER.info("User unknown, redirect to login page");

			getNavigationManager().navigateTo(new RedLoginView());
		}
		// user logged in byPass

		buildView();
	}

	private void buildView() {

		this.setCaption("Home");

		Button butCreate = new Button();

		butCreate.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				DemoDataGenerator.initDemoData();

			}
		});
		butCreate.setIcon(new ThemeResource("linegraphics/init.png"));
		getNavigationBar().setRightComponent(butCreate);

		VerticalComponentGroup group = new VerticalComponentGroup();

		NavigationButton btnDiary = new NavigationButton("Tagebuch", new DiarySummaryView());
		group.addComponents(btnDiary);

		NavigationButton btnMedi = new NavigationButton("Medikamente", new MedicineMainView());
		group.addComponents(btnMedi);

		this.setContent(group);

	}

}
