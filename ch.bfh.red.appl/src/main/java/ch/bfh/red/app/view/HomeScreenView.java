package ch.bfh.red.app.view;

import java.util.logging.Logger;

import ch.bfh.red.app.controller.LoginService;
import ch.bfh.red.app.model.profile.Patient;

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

	private LoginService loginService = LoginService.getInstance();

	@Override
	public void attach() {
		super.attach();

		// Check if a user has logged in
		if (!loginService.isLoggedIn(getSession())) {
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

			/**
			 * generated uid
			 */
			private static final long serialVersionUID = 1066043840247023867L;

			@Override
			public void buttonClick(ClickEvent event) {
				loginService.doLogout(getSession());

				getNavigationManager().navigateTo(new RedLoginView());
			}
		});
		butCreate.setIcon(new ThemeResource("linegraphics/logout.png"));
		getNavigationBar().setRightComponent(butCreate);

		VerticalComponentGroup group = new VerticalComponentGroup();

		NavigationButton btnOverview = new NavigationButton("Übersicht", new OverviewView());
		group.addComponents(btnOverview);

		// customize, depending on logged in user!
		Patient curUser = loginService.getLoggedInUser(getSession());
		if (curUser != null && curUser.getIndependenceLevel() >= 2) {
			// TODO implement checks and profiles correctly in sprint 4+
			NavigationButton btnDiary = new NavigationButton("Tagebuch", new DiarySummaryView());
			group.addComponents(btnDiary);
		}

		NavigationButton btnMedi = new NavigationButton("Medikamente", new MedicineMainView());
		group.addComponents(btnMedi);

		this.setContent(group);

	}

}
