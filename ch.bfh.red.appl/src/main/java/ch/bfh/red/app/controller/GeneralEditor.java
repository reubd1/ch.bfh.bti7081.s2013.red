package ch.bfh.red.app.controller;
/**
 * General Superclass for all Editors
 */
import ch.bfh.red.app.controller.notification.NotificationChecker;

import com.vaadin.addon.touchkit.ui.NavigationView;

public class GeneralEditor extends NavigationView {
	
	@Override
	public void attach() {
		super.attach();
		
		// Don't show Notifications while editing
		NotificationChecker.getInstance().setActive(false);
	}
}
