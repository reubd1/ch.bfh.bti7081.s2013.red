package ch.bfh.red.app.controller;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.profile.Patient;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.server.VaadinSession;

/**
 * @author stola
 * 
 *         This login Service handles the login, session and user object. <br>
 *         After the prototype this should be replaced or rebased to JAAS Realm or something equal.<br>
 *         TODO: Improve session handling.
 */

public class LoginService extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4147714731716671377L;

	private static final Logger LOGGER = Logger.getLogger(LoginService.class.getName());

	private static final String SES_LOGGED_IN_FLAG = "USR_LOGGED_IN";

	private static final String SES_LOGGED_IN_USER = "USR_LOGGED_IN_USR";

	private static LoginService instance;

	private JPAContainer<Patient> patientsJPA;

	/**
	 * Private constructor, please use getInstance go get the singleton instance
	 */
	private LoginService() {
		patientsJPA = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
	}

	@Override
	public void init() {
		DemoDataGenerator.doInitializeIfDataIsMissing();
	}

	/**
	 * Returns instance of singleton LoginService
	 * 
	 * @return
	 */
	public static LoginService getInstance() {
		if (instance == null) {
			// singleton pattern...
			instance = new LoginService();
			LOGGER.info("LoginService successful intialized");
		}
		return instance;
	}

	/**
	 * Execute login and put user into session, if successful
	 * 
	 * @param session
	 *            VaadinSession (e.g. getSession() in UI)
	 * @param username
	 *            String of user to login
	 * @param password
	 *            String of password to login
	 * @return true if login was successful
	 */
	public boolean doLogin(VaadinSession session, String username, String password) {
		LOGGER.info("doLogin called for user: " + username);

		Equal usrFilter = new Compare.Equal("loginName", username);
		patientsJPA.removeAllContainerFilters();
		patientsJPA.addContainerFilter(usrFilter);
		patientsJPA.applyFilters();

		if (patientsJPA.size() == 1) {
			EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();
			Patient loadedUser = em.find(Patient.class, patientsJPA.getItemIds());
			System.out.println("USER found :-) ");

			if (password != null && password.equals(loadedUser.getLoginPassword())) {
				// login was successful
				setLoggedInUser(session, loadedUser);

				// Store the current user in the service session
				session.setAttribute("user", username);

				// set logged in Flag
				session.setAttribute(SES_LOGGED_IN_FLAG, true);
				return true;
			}
		}

		LOGGER.info("Login was not possible.");
		return false;
	}

	/**
	 * Do logout and remove wipe USER + FLAG out of the session!
	 * 
	 * @param session
	 *            VaadinSession
	 */
	public void doLogout(VaadinSession session) {
		Patient p2logout = getLoggedInUser(session);
		String user2logout = "unknown";
		if (p2logout != null && p2logout.getLoginName() != null) {
			user2logout = p2logout.getLoginName();
		}
		LOGGER.info("User logged out, wipe session information for user: " + user2logout);

		// cleanup
		session.setAttribute(SES_LOGGED_IN_FLAG, false);
		session.setAttribute(SES_LOGGED_IN_USER, null);
	}

	/**
	 * Is user logged in or not - this method provides the answer
	 * 
	 * @param session
	 *            VaadinSession
	 * @return true if the user is logged in
	 */
	public boolean isLoggedIn(VaadinSession session) {
		Object o = session.getAttribute(SES_LOGGED_IN_FLAG);
		if (o != null) {
			return (Boolean) o;
		}
		return false;
	}

	/**
	 * Get logged in User (Class of type Patient) if the user is logged in
	 * 
	 * @param session
	 * @return authenticated Patient
	 */
	public Patient getLoggedInUser(VaadinSession session) {
		Object o = session.getAttribute(SES_LOGGED_IN_USER);
		if (o != null) {
			return (Patient) o;
		}

		return null;
	}

	/**
	 * @param loggedInUser
	 *            the loggedInUser to set
	 */
	private void setLoggedInUser(VaadinSession session, Patient loggedInUser) {
		session.setAttribute(SES_LOGGED_IN_USER, loggedInUser);
		LOGGER.fine("User stored in session");
	}

}
