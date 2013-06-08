package ch.bfh.red.app.controller;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.model.profile.Patient;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.server.VaadinSession;

/**
 * @author stola
 * 
 *         This login Service handles the login, session and user object. <br>
 *         After the prototype this should be replaced or rebased to JAAS Realm or something equal.
 */

public class LoginService {

	private final static Logger LOGGER = Logger.getLogger(LoginService.class.getName());

	private boolean loggedIn = false;
	
	private Patient loggedInUser = null;

	public static LoginService instance;
	
	private JPAContainer<Patient> patientsJPA;

	public LoginService() {
		patientsJPA = JPAContainerFactory.make(Patient.class, RedAppUI.PERSISTENCE_UNIT);
	}

	public static LoginService getInstance() {

		if (instance == null) {
			instance = new LoginService();
			LOGGER.info("LoginService instance successfully initialized");
		}
		return instance;
	}

	public boolean doLogin(VaadinSession session, String username, String password) {
		LOGGER.warning("doLogin called for user: " + username); 
 
		//
		// Validate username and password with database here. For examples sake
		// I use a dummy username and password.
		//
//		boolean isValid = username.equals("mario") && password.equals("mario");
		
//		Filter filter = 
		Equal usrFilter = new Compare.Equal("loginName", username);
		patientsJPA.addContainerFilter(usrFilter);
		patientsJPA.applyFilters();
		
		if(patientsJPA.size() == 1) {
			EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();
			Patient loadedUser = em.find(Patient.class, patientsJPA.getItemIds());
			System.out.println("USER found :-) ");
			
			if(password != null && password.equals(loadedUser.getLoginPassword())) {
				//login was successful 
				setLoggedInUser(loadedUser);
				
				
				// Store the current user in the service session
				session.setAttribute("user", username);

				// set logged in Flag
				loggedIn = true;
				return true;
			}
		}

		LOGGER.info("Login was not possible.");
		return false;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public Patient getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * @param loggedInUser the loggedInUser to set
	 */
	private void setLoggedInUser(Patient loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	

}
