package ch.bfh.red.app.view;

/**
 * @author team #F00
 *
 */

import ch.bfh.red.app.controller.DemoDataGenerator;
import ch.bfh.red.app.controller.notification.NotificationChecker;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Widgetset("ch.bfh.red.app.widgetset.RedappWidgetset")
@Title("The RedApp")
@Theme("redapp")
@Push
public class RedAppUI extends UI {
	
	public static final String PERSISTENCE_UNIT = "redapp"; // TODO irgendwo im data access Bereich definieren

	public static final String FACELOGIN = "redapp";
	
	private static final long serialVersionUID = -3919212212063135503L;
	

	@Override
	protected void init(VaadinRequest request) {
		// create demo data, if not already initialized....
		DemoDataGenerator.doInitializeIfDataIsMissing();
		
		// Set the window or tab title
		getPage().setTitle("Welcome RedApp");

		NotificationChecker checker = NotificationChecker.getInstance();
		checker.setMainPage(this);
		checker.start();
		
		NavigationManager navigationManager = new NavigationManager(new HomeScreenView());
		setContent(navigationManager);
		

	}
}