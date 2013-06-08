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

public class LoginService extends Application  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4147714731716671377L;

	private static final Logger LOGGER = Logger.getLogger(LoginService.class.getName());

	private static final  String SES_LOGGED_IN_FLAG = "USR_LOGGED_IN";
	
	private static final  String SES_LOGGED_IN_USER = "USR_LOGGED_IN_USR";

	public static LoginService instance;

	private JPAContainer<Patient> patientsJPA;

	public LoginService() {
		patientsJPA = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
	}

	@Override
	public void init() {
		
		// initialize mainWindow and other stuff

	}
	
	public static LoginService getInstance() {

		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}

	public boolean doLogin(VaadinSession session, String username, String password) {
		LOGGER.warning("doLogin called for user: " + username);

		//
		// Validate username and password with database here. For examples sake
		// I use a dummy username and password.
		//
		// boolean isValid = username.equals("mario") && password.equals("mario");

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
	
	public void doLogout(VaadinSession session){
		session.setAttribute(SES_LOGGED_IN_FLAG, false);
		session.setAttribute(SES_LOGGED_IN_USER, null);
	}

	public boolean isLoggedIn(VaadinSession session) {
		Object o = session.getAttribute(SES_LOGGED_IN_FLAG);
		if(o != null ){
			return (Boolean) o;
		}
		return false;
	}

	public Patient getLoggedInUser(VaadinSession session) {
		Object o = session.getAttribute(SES_LOGGED_IN_USER);
		if(o != null ){
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
	}

}
