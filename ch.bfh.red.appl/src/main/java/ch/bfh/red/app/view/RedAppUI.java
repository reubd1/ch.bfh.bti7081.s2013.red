package ch.bfh.red.app.view;

/**
 * @author team #F00
 *
 */

import ch.bfh.red.app.controller.notification.NotificationChecker;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Widgetset("ch.bfh.red.app.widgetset.RedappWidgetset")
@Title("My RedApp")
@Theme("redapp")
@Push
public class RedAppUI extends UI {

	private static final long serialVersionUID = -3919212212063135503L;

	@Override
	protected void init(VaadinRequest request) {
		// Set the window or tab title
		getPage().setTitle("Welcome RedApp");

		NavigationManager navigationManager = new NavigationManager(new HomeScreenView());
		setContent(navigationManager);
		
		NotificationChecker checker = NotificationChecker.getInstance();
		checker.setMainPage(this);
		checker.start();

	}
}