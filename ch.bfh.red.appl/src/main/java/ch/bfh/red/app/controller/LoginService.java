/**
 * 
 */
package ch.bfh.red.app.controller;

import java.util.logging.Logger;

import com.vaadin.server.VaadinSession;

/**
 * @author stola
 * 
 */

public class LoginService {

	private final static Logger LOGGER = Logger.getLogger(LoginService.class.getName());
	
	private boolean loggedIn = false;
	
	public static LoginService instance;
	

	public LoginService() {

	}

	public static LoginService getInstance() {

		if (instance == null) {
			instance = new LoginService();
			LOGGER.info("LoginService instance successfully initialized");
		}
		return instance;
	}

	public boolean doLogin(VaadinSession session, String username, String password) {

		//
		// Validate username and password with database here. For examples sake
		// I use a dummy username and password.
		//
		boolean isValid = username.equals("mario") && password.equals("mario");
		
		if(isValid) {
			// Store the current user in the service session
			session.setAttribute("user", username);
			
			// set logged in Flag
			loggedIn = true;
		} 
		
		return isValid;
	}
	
	public boolean isLoggedIn(){
		return loggedIn;
	}
	
	
}
