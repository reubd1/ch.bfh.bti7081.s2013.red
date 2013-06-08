package ch.bfh.red.app.view;

import java.util.logging.Logger;

import ch.bfh.red.app.controller.LoginService;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author stola
 */
public class RedLoginView extends NavigationView implements ClickListener {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = -839547777023437763L;

	private final static Logger LOGGER = Logger.getLogger(RedLoginView.class.getName());

	private LoginService loginService = LoginService.getInstance();

	private TextField user;

	private PasswordField password;

	private Button loginButton;

	public RedLoginView() {
	}

	public void attach() {
		super.attach();

		buildView();
	}

	private void buildView() {
		this.setCaption("Login to REDapp");
		
		// Create the user input field
		user = new TextField("User:");
		user.setWidth("300px");
		user.setRequired(true);
		user.setInputPrompt("Your username (eg. mario/mario)");
		// user.addValidator(new EmailValidator("Username must be an email address"));
		user.setInvalidAllowed(false);

		// Create the password input field
		password = new PasswordField("Password:");
		password.setWidth("300px");
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		// Create login button
		loginButton = new Button("Login", this);

		// Add both to a panel
		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Please login to access the application. (mario/mario)");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		setContent(fields);

	}

	@Override
	public void buttonClick(ClickEvent event) {

		LOGGER.info("login Buttion was clicked");

		//
		// Validate the fields using the navigator. By using validors for the
		// fields we reduce the amount of queries we have to use to the database
		// for wrongly entered passwords
		//
		if (!user.isValid() || !password.isValid()) {
			return;
		}

		// call loginService to perform the login
		if (loginService.doLogin(getSession(), user.getValue(), password.getValue() )) {
			LOGGER.info("User successfully logged in");
			// Navigate to home view
			getNavigationManager().navigateTo(new HomeScreenView());

		} else {
			LOGGER.info("User / password not valid! access denied");
			
			// Wrong password clear the password field and refocuses it
			this.password.setValue(null);
			this.password.focus();
		}

	}
}